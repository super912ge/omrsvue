package com.proship.omrs.document.certificate.service;

import com.proship.omrs.candidate.participant.repository.ParticipantRepository;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.base.service.DocumentServiceImpl;
import com.proship.omrs.document.certificate.entity.Certificate;
import com.proship.omrs.document.certificate.entity.CertificateComment;
import com.proship.omrs.document.certificate.entity.CertificateDetail;
import com.proship.omrs.document.certificate.entity.CertificateFormDate;
import com.proship.omrs.document.certificate.repository.CertificateCommentRepository;
import com.proship.omrs.document.certificate.repository.CertificateDetailRepository;
import com.proship.omrs.document.certificate.repository.CertificateFormDateRepository;
import com.proship.omrs.document.certificate.repository.CertificateRepository;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Service("certificateService")
public class CertificateServiceImpl extends
        DocumentServiceImpl<Certificate, CertificateDetail,CertificateFormDate,CertificateComment> implements DocumentService {

    @Autowired
    CertificateRepository repository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    CertificateDetailRepository certificateDetailRepository;

    @Autowired
    CertificateFormDateRepository certificateFormDateRepository;

    @Autowired
    CertificateCommentRepository certificateCommentRepository;

//    @Override
//    public Set<Long> searchCandidate(DocumentSearchTerm term) {
//
//    return repository.findParticipant(term.getExpireDate(),term.getIds(),(long)term.getIds().size());
//
//    }

    @Override
    public Long create(Long id, CreateEditDocumentParam param) {


        Certificate certificate = new Certificate();

        certificate = getDocument(certificate,id,param);

        certificate.setCountryId(param.getCountry());

        certificate.setCertificateTypeId(param.getType());

        certificate = repository.save(certificate);

        CertificateDetail certificateDetail = new CertificateDetail();

        certificateDetail = getDetail(certificateDetail,param);

        certificateDetail.setCertificate(certificate);

        certificateDetailRepository.save(certificateDetail);

        CertificateComment certificateComment = new CertificateComment();

        certificateComment = getComment(certificateComment,param);

        certificateComment.setCertificate(certificate);

        certificateCommentRepository.save(certificateComment);

        CertificateFormDate certificateFormDate = new CertificateFormDate();

        certificateFormDate = getFormDate(certificateFormDate,param);

        certificateFormDate.setCertificate(certificate);

        certificateFormDateRepository.save(certificateFormDate);

        return certificate.getId();
    }

    public Long update(CreateEditDocumentParam param){

        Long candidateId = delete(param.getId());

        return create(candidateId,param);

    }
    public Long delete(Long id){

        Certificate certificate = repository.getOne(id);

        return certificate.getParticipantId();


    }
}
