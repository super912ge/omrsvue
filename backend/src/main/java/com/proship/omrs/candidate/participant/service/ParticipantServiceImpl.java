package com.proship.omrs.candidate.participant.service;

import com.proship.omrs.candidate.availibility.param.SearchByAvailabilityParam;
import com.proship.omrs.candidate.availibility.repository.ParticipantAvailabilityRepository;
import com.proship.omrs.candidate.name.repository.ParticipantActNameRepository;
import com.proship.omrs.candidate.name.repository.ParticipantNameTtsRepository;
import com.proship.omrs.candidate.participant.entity.Participant;
import com.proship.omrs.candidate.participant.entity.ParticipantAct;
import com.proship.omrs.candidate.citizenship.param.SearchByResidencyCitizenshipParam;
import com.proship.omrs.candidate.citizenship.repository.ParticipantCitizenshipOverrideRepository;
import com.proship.omrs.candidate.name.entity.ParticipantActName;
import com.proship.omrs.candidate.name.entity.ParticipantNameTts;
import com.proship.omrs.candidate.participant.param.*;
import com.proship.omrs.candidate.participant.repository.*;
import com.proship.omrs.candidate.name.param.SearchByNameParam;
import com.proship.omrs.candidate.residency.repository.ParticipantResidencyOverrideRepository;
import com.proship.omrs.contract.entity.ContractEvent;
import com.proship.omrs.contract.entity.ContractMainShard;
import com.proship.omrs.contract.repository.ContractShardRepository;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.certificate.repository.CertificateRepository;
import com.proship.omrs.document.medical.repository.MedicalRepository;
import com.proship.omrs.document.passport.repository.PassportRepository;
import com.proship.omrs.document.seamanBook.repository.SeamansBookRepository;
import com.proship.omrs.document.visa.repository.VisaRepository;
import com.proship.omrs.evaluation.entity.EvalTag;
import com.proship.omrs.evaluation.repository.EvalTagRepository;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    VisaRepository visaRepository;
    @Autowired
    SeamansBookRepository seamansBookRepository;
    @Autowired
    MedicalRepository medicalRepository;
    @Autowired
    PassportRepository passportRepository;

    @Autowired
    ParticipantResidencyOverrideRepository participantResidencyOverrideRepository;

    @Autowired
    ParticipantActNameRepository participantActNameRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantCitizenshipOverrideRepository participantCitizenshipOverrideRepository;

    @Autowired
    ParticipantAvailabilityRepository participantAvailabilityRepository;

    @Autowired
    ParticipantNameTtsRepository participantNameTtsRepository;

    @Autowired
    ContractShardRepository contractShardRepository;

    @Resource(name = "contractEventCase")
    private Properties contractEventCase;

    @Autowired
    EvalTagRepository evalTagRepository;
    @Autowired
    private ParticipantActRepository participantActRepository;
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
    public Long findParticipantByActId(Long actId) {
        return participantRepository.findParticipantByActId(actId);
    }

    @Override
    public CandidateComplete displayCandidateDetail(Long id) {


        Participant participant = participantRepository.findOne(id);

        for (ContractMainShard shard : participant.getParticipantAct().getContractShards()){
            List<ContractEvent> events = shard.getContract().getContractEvents();
            for (ContractEvent event : events){
                event.setContractEventCase(this.contractEventCase);
                event.setContractShardRepository(this.contractShardRepository);
            }
        }

        return new CandidateComplete(participant);
    }

    @Override
    public Map<String, Object> createParticipant(CreateParticipantParam param) {

        Map<String,Object> result = new HashMap<>();
        String username =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CustomUser user = (CustomUser)userDetailsService.loadUserByUsername(username);

        Participant participant = new Participant();

        participant.setUuid(UUID.randomUUID().getMostSignificantBits());

        participant.setSpecialtyType(0);

        EvalTag evalTag = new EvalTag();

        evalTag.setNexttransactiontime(Utils.getInfiniteTimestamp());

        evalTag.setEvalTagTypeId(1l);

        evalTag.setCreatorId(user.getUserId());

        evalTag.setDestroyerId(user.getUserId());

        evalTag.setTransactiontime(new Timestamp(System.currentTimeMillis()));

        evalTag.setUuid(UUID.randomUUID().getMostSignificantBits());

        evalTag = evalTagRepository.save(evalTag);

        participant.setEvaluation(evalTag);

        participant =  participantRepository.save(participant);

        ParticipantAct act = new ParticipantAct();

        act.setParticipant(participant);

        act.setSpecialtyType(0l);

        act.setActive(true);

        act.setUuid(UUID.randomUUID().getMostSignificantBits());

        act = participantActRepository.save(act);

        ParticipantNameTts participantNameTts = setParticipantName(param, participant,user.getUserId());

        participantNameTtsRepository.save(participantNameTts);

        if (param.getActName()!=null&&!param.getActName().trim().equals("")){

            ParticipantActName participantActName = setParticipantActName(param,act,user.getUserId());

            participantActNameRepository.save(participantActName);
        }

        result.put("id", participant.getId());
        result.put("actId",act.getId());
        result.put("evaluationId",participant.getEvaluation().getId());
        return result;
    }

    @Override
    public void updateName(Long id, CreateParticipantParam param) {

        Participant participant = participantRepository.findOne(id);

        ParticipantNameTts nameTts = participant.getNameTts();

        String username =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CustomUser user = (CustomUser)userDetailsService.loadUserByUsername(username);

        if(nameTts.getFirstName().equals(param.getFirstName().trim())
                && nameTts.getOtherName().equals(param.getOtherName())
                &&nameTts.getLastName().equals(param.getLastName())
                && nameTts.getPronunciation().equals(param.getPronunciation())){

        }else {

            nameTts.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

            participantNameTtsRepository.save(nameTts);

            ParticipantNameTts participantNameTts = setParticipantName(param,participant,user.getUserId());
        }
        ParticipantActName actName = participant.getParticipantAct().getParticipantActName();
        if (actName!=null){

            if (param.getActName()==null||param.getActName().trim().equals("")){

                actName.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

                participantActNameRepository.save(actName);
            }else {
                if (!actName.getName().equals(param.getActName().trim())) {

                    actName.setNexttransactiontime(new Timestamp(System.currentTimeMillis()));

                    participantActNameRepository.save(actName);

                    ParticipantActName participantActName = setParticipantActName(param,
                            participant.getParticipantAct(),user.getUserId());

                    participantActNameRepository.save(participantActName);
                }
            }
        }else if (!param.getActName().trim().equals("")){

            ParticipantActName participantActName =
                    setParticipantActName(param,participant.getParticipantAct(),user.getUserId());

            participantActNameRepository.save(participantActName);
        }
    }


    @Override
    public Set<Long> findParticipantByCountry(SearchByResidencyCitizenshipParam param) {

        Set<Long> residency ;

        Set<Long> citizenship ;

        if (param.getCitizenship()!=null){

            citizenship = participantCitizenshipOverrideRepository.findCandidateIdByCitizenship(param.getCitizenship());

            if(param.getResidency()!=null){

                residency = participantResidencyOverrideRepository.findCandidateIdByResidency(param.getResidency());

                 citizenship.retainAll(residency);
            }
            return citizenship;
        }
        else if (param.getResidency()!=null) return participantResidencyOverrideRepository.findCandidateIdByResidency(param.getResidency());

        return null;
    }

    @Override
    public Set<Long> findParticipantByAvailability(SearchByAvailabilityParam param) {

        if(param.getStartDate()==null) param.setStartDate(new Date());

        if (param.getEndDate()==null) param.setEndDate(new Date());

        if (param.getInterestLevel()==null)

        return participantAvailabilityRepository.findCandidateIdByAvailability(param.getStartDate(),param.getEndDate());

        else
            return participantAvailabilityRepository
                    .findCandidateIdByAvailabilityAndLevel(
                            param.getStartDate(),param.getEndDate(),param.getInterestLevel());
    }

    @Override
    public DisplayCandidateResultParam displayCandidate(List<Long> ids, Pageable pageable) {

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
        if (firstResult){
            participantIds.addAll(result);
        }
        else if (!participantIds.isEmpty()) {
            participantIds.retainAll(result);
        }
    }

    private ParticipantNameTts setParticipantName(CreateParticipantParam param, Participant participant, Long userId){

        ParticipantNameTts participantNameTts = new ParticipantNameTts();

        participantNameTts.setFirstName(param.getFirstName().trim());

        participantNameTts.setLastName(param.getLastName().trim());

        participantNameTts.setOtherName(param.getOtherName().trim());

        participantNameTts.setPronunciation(param.getPronunciation().trim());

        participantNameTts.setTransactiontime(new Timestamp(System.currentTimeMillis()));

        participantNameTts.setNexttransactiontime(Utils.getInfiniteTimestamp());

        participantNameTts.setParticipant(participant);

        participantNameTts.setCreatorId(userId);

        return participantNameTts;
    }

    private ParticipantActName setParticipantActName(CreateParticipantParam param, ParticipantAct act, Long userId){

        ParticipantActName participantActName = new ParticipantActName();

        participantActName.setName(param.getActName());

        participantActName.setParticipantAct(act);

        participantActName.setCreatorId(userId);
        participantActName.setNexttransactiontime(Utils.getInfiniteTimestamp());

        participantActName.setTransactiontime(new Timestamp(System.currentTimeMillis()));

        return participantActName;
    }
}
