package com.proship.omrs.document.base.service;

import com.proship.omrs.document.base.entity.Document;
import com.proship.omrs.document.base.entity.DocumentComment;
import com.proship.omrs.document.base.entity.DocumentDetail;
import com.proship.omrs.document.base.entity.DocumentFormDate;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.Timestamp;
import java.util.UUID;

public class DocumentServiceImpl<T extends Document, D extends DocumentDetail, F extends DocumentFormDate, C extends DocumentComment> {

    @Autowired
    UserDetailsService userDetailsService;
    
    private Long userId;

    public void setUserId(){

        String username =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        CustomUser user = (CustomUser)userDetailsService.loadUserByUsername(username);

        this.userId = user.getUserId();
    }

    public Long getUserId(){

        if (userId==null)setUserId();

        return this.userId;
    }

    
    
    public T getDocument(T t, Long id,CreateEditDocumentParam param){

        t.setParticipantId(id);
        t.setNumber(param.getNumber());
        t.setUuid(UUID.randomUUID().getMostSignificantBits());

        return t;
    }

    public D getDetail(D d, CreateEditDocumentParam param){

        d.setExpiryDate(param.getExpiryDate());
        d.setIssuingDate(param.getIssuingDate());
        d.setIssuingPost(param.getIssuingPost());
        d.setTransactiontime(new Timestamp(System.currentTimeMillis()));
        d.setNexttransactiontime(Utils.getInfiniteTimestamp());
        d.setCreatorId(getUserId());
        return d;
    }
    
    public C getComment(C c, CreateEditDocumentParam param){
        c.setText(param.getComment());
        c.setCreatorId(getUserId());
        c.setNexttransactiontime(Utils.getInfiniteTimestamp());
        c.setTransactiontime(new Timestamp(System.currentTimeMillis()));

        return c;
    }
    
    public F getFormDate(F f, CreateEditDocumentParam param){
        f.setValue(param.getFormDate());
        f.setTransactiontime(new Timestamp(System.currentTimeMillis()));
        f.setNexttransactiontime(Utils.getInfiniteTimestamp());
        f.setCreatorId(getUserId());
        return f;
    }
}
