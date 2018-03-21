package com.proship.omrs.document.medical.service;

import com.proship.omrs.candidate.participant.repository.ParticipantRepository;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.base.service.DocumentServiceImpl;
import com.proship.omrs.document.medical.entity.Medical;
import com.proship.omrs.document.medical.entity.MedicalComment;
import com.proship.omrs.document.medical.entity.MedicalDetail;
import com.proship.omrs.document.medical.entity.MedicalFormDate;
import com.proship.omrs.document.medical.repository.MedicalCommentRepository;
import com.proship.omrs.document.medical.repository.MedicalDetailRepository;
import com.proship.omrs.document.medical.repository.MedicalFormDateRepository;
import com.proship.omrs.document.medical.repository.MedicalRepository;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service("medicalService")
public class MedicalServiceImpl extends DocumentServiceImpl<Medical, MedicalDetail,MedicalFormDate,MedicalComment>
        implements DocumentService{
    @Autowired
    MedicalRepository repository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    MedicalDetailRepository medicalDetailRepository;

    @Autowired
    MedicalFormDateRepository medicalFormDateRepository;

    @Autowired
    MedicalCommentRepository medicalCommentRepository;

    @Override
    public Long create(Long id, CreateEditDocumentParam param) {


        Medical medical = new Medical();
        medical = getDocument(medical,id, param);
        medical.setMedicalTypeId(param.getType());
        medical.setFitForDuty(param.getFitForDuty());
        medical = repository.save(medical);

        MedicalDetail medicalDetail = new MedicalDetail();
        medicalDetail = getDetail(medicalDetail,param);
        medicalDetail.setMedical(medical);
        medicalDetailRepository.save(medicalDetail);

        MedicalComment medicalComment = new MedicalComment();
        medicalComment.setMedical(medical);
        medicalComment = getComment(medicalComment,param);
        medicalCommentRepository.save(medicalComment);

        MedicalFormDate medicalFormDate = new MedicalFormDate();
        medicalFormDate = getFormDate(medicalFormDate,param);
        medicalFormDate.setMedical(medical);
        medicalFormDateRepository.save(medicalFormDate);


        return medical.getId();
    }
    @Override
    public Long update(CreateEditDocumentParam param){

        Long candidateId = delete(param.getId());

        return create(candidateId,param);

    }
    @Override
    public Long delete(Long id){

        Medical medical = repository.findOne(id);

        return medical.getParticipantId();
    }
}
