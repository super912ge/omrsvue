package com.proship.omrs.candidate.candidate.service;

import com.proship.omrs.candidate.candidate.entity.Participant;
import com.proship.omrs.candidate.candidate.param.*;
import com.proship.omrs.candidate.repository.*;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.certificate.repository.CertificateRepository;
import com.proship.omrs.document.medical.repository.MedicalRepository;
import com.proship.omrs.document.passport.repository.PassportRepository;
import com.proship.omrs.document.seamanBook.repository.SeamansBookRepository;
import com.proship.omrs.document.visa.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    VisaRepository visaRepository;
    @Autowired
    SeamansBookRepository seamansBookRepository;
    @Autowired
    MedicalRepository medicalRepository;
    @Autowired
    PassportRepository passportRepository;

    @Autowired
    ParticipantResidencyRepository participantResidencyRepository;

    @Autowired
    ParticipantActNameRepository participantActNameRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantCitizenshipRepository participantCitizenshipRepository;

    @Autowired
    ParticipantAvailabilityRepository participantAvailabilityRepository;

    @Autowired
    ParticipantNameTtsRepository participantNameTtsRepository;

    @Override
    public Set<Long> findParticipantByDocuments(Map<String, List<DocumentSearchTerm>>searchMap) {


    Set<Long> participantId = new HashSet<>();
    boolean firstResultSet = true;

        for (String document: searchMap.keySet()) {

            switch (document) {
                case "certificate": {
                    List<DocumentSearchTerm> list = searchMap.get("certificate");

                    if (!list.isEmpty()) {

                        searchByEachDocument(participantId,"certificate",list,certificateRepository,firstResultSet);
                        firstResultSet = false;
                        if (participantId.isEmpty()) return participantId;
                    }

                    break;
                }
                case "visa": {

                    List<DocumentSearchTerm> list = searchMap.get("visa");

                    if (!list.isEmpty()) {

                        searchByEachDocument(participantId,"visa",list,visaRepository,firstResultSet);
                        firstResultSet = false;
                        if (participantId.isEmpty()) return participantId;
                    }
                    break;

                }
                case "medical": {
                    List<DocumentSearchTerm> list = searchMap.get("medical");

                    if (list!=null&&!list.isEmpty()) {

                        searchByEachDocument(participantId,"medical",list,medicalRepository,firstResultSet);
                        firstResultSet = false;
                        if (participantId.isEmpty()) return participantId;
                    }


                    break;
                }
                case "passport": {

                    List<DocumentSearchTerm> list = searchMap.get("passport");

                    if (!list.isEmpty()) {

                        searchByEachDocument(participantId,"passport",list,passportRepository,firstResultSet);
                        firstResultSet = false;
                        if (participantId.isEmpty()) return participantId;
                    }
                    break;
                }
                case "seamansBook": {
                    List<DocumentSearchTerm> list = searchMap.get("seamansBook");

                    if (!list.isEmpty()) {

                        searchByEachDocument(participantId,"seamansBook",list,seamansBookRepository,firstResultSet);
                        firstResultSet = false;
                        if (participantId.isEmpty()) return participantId;
                    }
                    break;
                }
                default:
                    break;
            }
        }

        return participantId;
    }

    @Override
    public Set<Long> findParticipantByEvaluation(Set<Long> evaluationIds) {
        return participantRepository.findParticipantIdByEvaluationId(evaluationIds) ;
    }

    @Override
    public Set<Long> findParticipantByName(SearchByNameParam param) {

        String searchPattern = null;


        if(param.getSearchType().equals("FirstName")){

            return participantNameTtsRepository.findParticipantIdByFirstName(param.getName()+"%");
        }else if (param.getSearchType().equals("LastName")){
            return participantNameTtsRepository.findParticipantIdByLastName(param.getName()+"%");
        }
        else {

            String[] strs= param.getName().split(" ");

            searchPattern = "%";
            for (String str : strs){
                searchPattern = searchPattern + str + "%";
            }
            return participantNameTtsRepository.findParticipantIdByAnyName(searchPattern);
        }
    }

    @Override
    public Set<Long> findParticipantByActName(String param) {

           String searchPattern = "%"+param+"%";

        return participantActNameRepository.findCandidateIdByName(searchPattern);
    }


    @Override
    public Set<Long> findParticipantByCountry(SearchByResidencyCitizenshipParam param) {

        Set<Long> residency = null;
        Set<Long> citizenship = null;

        if (param.getCitizenship()!=null){

            citizenship = participantCitizenshipRepository.findCandidateIdByCitizenship(param.getCitizenship());

            if(param.getResidency()!=null){

                residency = participantResidencyRepository.findCandidateIdByResidency(param.getResidency());

                 citizenship.retainAll(residency);

            }

            return citizenship;

        }
        else if (param.getResidency()!=null) return participantResidencyRepository.findCandidateIdByResidency(param.getResidency());

        return null;
    }

    @Override
    public Set<Long> findParticipantByAvailability(SearchByAvailabilityParam param) {
        if(param.getStartDate()==null) param.setStartDate(new Date());
        if (param.getEndDate()==null) param.setEndDate(new Date());
        if (param.getInterestLevel()==null)
        return participantAvailabilityRepository.findCandidateIdByAvailability(param.getStartDate(),param.getEndDate());

        else return participantAvailabilityRepository.findCandidateIdByAvailabilityAndLevel(param.getStartDate(),param.getEndDate(),param.getInterestLevel());
    }

    @Override
    public DisplayCandidateResultParam displayCandidate(Set<Long> ids, Pageable pageable) {

       Page<Participant> resultSet =  participantRepository.findParticipantByIdIn(ids,pageable);

        DisplayCandidateResultParam result = new DisplayCandidateResultParam();


       List<CandidateBrief> resultList = resultSet.getContent().stream().map(CandidateBrief::new).collect(Collectors.toList());

        result.setResultList(resultList);
        result.setTotalPage(resultSet.getTotalPages());
       return result;
    }

    void searchByEachDocument(Set<Long> participantIds, String type, List<DocumentSearchTerm> searchTerms,
                              DocumentRepository repository, boolean firstResult){
        Set<Long> result = new HashSet<>();
        for (DocumentSearchTerm condition : searchTerms) {
            result.addAll(repository.findParticipant(condition.getExpireDate(),
                    condition.getIds(), (long) condition.getIds().size()));
        }
        if (firstResult==true){
            participantIds.addAll(result);
        }
        else if (!participantIds.isEmpty()) {
            participantIds.retainAll(result);
        }
    }


}
