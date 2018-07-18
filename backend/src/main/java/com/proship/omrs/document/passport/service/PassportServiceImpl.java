package com.proship.omrs.document.passport.service;

import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.base.service.DocumentServiceImpl;
import com.proship.omrs.document.passport.entity.Passport;
import com.proship.omrs.document.passport.entity.PassportComment;
import com.proship.omrs.document.passport.entity.PassportDetail;
import com.proship.omrs.document.passport.entity.PassportFormDate;
import com.proship.omrs.document.passport.repository.PassportCommentRepository;
import com.proship.omrs.document.passport.repository.PassportDetailRepository;
import com.proship.omrs.document.passport.repository.PassportFormDateRepository;
import com.proship.omrs.document.passport.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("passportService")
public class PassportServiceImpl extends DocumentServiceImpl<Passport, PassportDetail,PassportFormDate,PassportComment> implements DocumentService{

    @Autowired
    PassportRepository repository;

    @Autowired
    PassportCommentRepository passportCommentRepository;

    @Autowired
    PassportDetailRepository passportDetailRepository;

    @Autowired
    PassportFormDateRepository passportFormDateRepository;

    @Override
    public Long create(Long id, CreateEditDocumentParam param) {


        Passport passport = new Passport();

        passport = getDocument(passport,id, param);
        passport.setCountryId(param.getCountry());
        passport = repository.save(passport);


        PassportDetail passportDetail = new PassportDetail();
        passportDetail.setPassport(passport);
        passportDetail = getDetail(passportDetail,param);
        passportDetailRepository.save(passportDetail);

        PassportComment passportComment = new PassportComment();
        passportComment.setPassport(passport);
        passportComment = getComment(passportComment,param);
        passportCommentRepository.save(passportComment);

        PassportFormDate passportFormDate = new PassportFormDate();

        passportFormDate = getFormDate(passportFormDate,param);
        passportFormDate.setPassport(passport);
        passportFormDateRepository.save(passportFormDate);

        return passport.getId();
    }
    @Override
    public Long update(CreateEditDocumentParam param){

        Long candidateId = delete(param.getId());

        return create(candidateId,param);

    }
    @Override
    public Long delete(Long id){

        Passport passport = repository.getOne(id);

        return passport.getParticipantId();
    }
}
