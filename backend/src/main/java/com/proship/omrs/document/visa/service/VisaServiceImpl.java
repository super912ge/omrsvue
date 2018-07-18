package com.proship.omrs.document.visa.service;

import com.proship.omrs.candidate.participant.repository.ParticipantRepository;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.base.service.DocumentServiceImpl;
import com.proship.omrs.document.visa.entity.Visa;
import com.proship.omrs.document.visa.entity.VisaComment;
import com.proship.omrs.document.visa.entity.VisaDetail;
import com.proship.omrs.document.visa.entity.VisaFormDate;
import com.proship.omrs.document.visa.repository.VisaCommentRepository;
import com.proship.omrs.document.visa.repository.VisaDetailRepository;
import com.proship.omrs.document.visa.repository.VisaFormDateRepository;
import com.proship.omrs.document.visa.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("visaService")
public class VisaServiceImpl extends DocumentServiceImpl<Visa, VisaDetail,VisaFormDate,VisaComment>
        implements DocumentService{
    @Autowired
    VisaRepository repository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    VisaDetailRepository visaDetailRepository;

    @Autowired
    VisaFormDateRepository visaFormDateRepository;

    @Autowired
    VisaCommentRepository visaCommentRepository;

    @Override
    public Long create(Long id, CreateEditDocumentParam param) {


        Visa visa = new Visa();
        visa = getDocument(visa,id, param);
        visa.setVisaTypeId(param.getType());
        visa.setMultiplicity(param.getMultiplicity());
        visa = repository.save(visa);

        VisaDetail visaDetail = new VisaDetail();
        visaDetail = getDetail(visaDetail,param);
        visaDetail.setVisa(visa);
        visaDetailRepository.save(visaDetail);

        VisaComment visaComment = new VisaComment();
        visaComment.setVisa(visa);
        visaComment = getComment(visaComment,param);
        visaCommentRepository.save(visaComment);

        VisaFormDate visaFormDate = new VisaFormDate();
        visaFormDate = getFormDate(visaFormDate,param);
        visaFormDate.setVisa(visa);
        visaFormDateRepository.save(visaFormDate);


        return visa.getId();
    }
    @Override
    public Long update(CreateEditDocumentParam param){

        Long candidateId = delete(param.getId());

        return create(candidateId,param);

    }
    @Override
    public Long delete(Long id){

        Visa visa = repository.getOne(id);

        return visa.getParticipantId();
    }
}
