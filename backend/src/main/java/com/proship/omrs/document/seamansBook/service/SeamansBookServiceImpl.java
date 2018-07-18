package com.proship.omrs.document.seamansBook.service;

import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.base.service.DocumentServiceImpl;
import com.proship.omrs.document.seamansBook.entity.SeamansBook;
import com.proship.omrs.document.seamansBook.entity.SeamansBookComment;
import com.proship.omrs.document.seamansBook.entity.SeamansBookDetail;
import com.proship.omrs.document.seamansBook.entity.SeamansBookFormDate;
import com.proship.omrs.document.seamansBook.repository.SeamansBookCommentRepository;
import com.proship.omrs.document.seamansBook.repository.SeamansBookDetailRepository;
import com.proship.omrs.document.seamansBook.repository.SeamansBookFormDateRepository;
import com.proship.omrs.document.seamansBook.repository.SeamansBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("seamansBookService")
public class SeamansBookServiceImpl extends DocumentServiceImpl<SeamansBook, SeamansBookDetail,SeamansBookFormDate,SeamansBookComment> implements DocumentService{

    @Autowired
    SeamansBookRepository repository;

    @Autowired
    SeamansBookCommentRepository seamansBookCommentRepository;

    @Autowired
    SeamansBookDetailRepository seamansBookDetailRepository;

    @Autowired
    SeamansBookFormDateRepository seamansBookFormDateRepository;

    @Override
    public Long create(Long id, CreateEditDocumentParam param) {


        SeamansBook seamansBook = new SeamansBook();

        seamansBook = getDocument(seamansBook,id, param);
        seamansBook.setCountryId(param.getCountry());
        seamansBook = repository.save(seamansBook);


        SeamansBookDetail seamansBookDetail = new SeamansBookDetail();
        seamansBookDetail.setSeamansBook(seamansBook);
        seamansBookDetail = getDetail(seamansBookDetail,param);
        seamansBookDetailRepository.save(seamansBookDetail);

        SeamansBookComment seamansBookComment = new SeamansBookComment();
        seamansBookComment.setSeamansBook(seamansBook);
        seamansBookComment = getComment(seamansBookComment,param);
        seamansBookCommentRepository.save(seamansBookComment);

        SeamansBookFormDate seamansBookFormDate = new SeamansBookFormDate();

        seamansBookFormDate = getFormDate(seamansBookFormDate,param);
        seamansBookFormDate.setSeamansBook(seamansBook);
        seamansBookFormDateRepository.save(seamansBookFormDate);

        return seamansBook.getId();
    }
    @Override
    public Long update(CreateEditDocumentParam param){

        Long candidateId = delete(param.getId());

        return create(candidateId,param);

    }
    @Override
    public Long delete(Long id){

        SeamansBook seamansBook = repository.getOne(id);

        return seamansBook.getParticipantId();
    }
}
