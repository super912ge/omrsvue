package com.proship.omrs.candidate.candidate.service;

import com.proship.omrs.candidate.repository.ParticipantRepository;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.document.base.repository.DocumentRepository;
import com.proship.omrs.document.certificate.repository.CertificateRepository;
import com.proship.omrs.document.medical.repository.MedicalRepository;
import com.proship.omrs.document.passport.repository.PassportRepository;
import com.proship.omrs.document.seamanBook.repository.SeamansBookRepository;
import com.proship.omrs.document.visa.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    ParticipantRepository participantRepository;


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
