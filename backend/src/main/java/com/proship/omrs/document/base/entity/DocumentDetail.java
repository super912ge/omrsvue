package com.proship.omrs.document.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.base.entity.BaseEntity;

import javax.persistence.MappedSuperclass;
import java.util.Date;
@MappedSuperclass
public class DocumentDetail extends BaseEntity{

    @JsonFormat(pattern="yyyy-MM-dd")
    private String issuingPost;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date issuingDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expiryDate;

    public String getIssuingPost() {
        return issuingPost;
    }

    public void setIssuingPost(String issuingPost) {
        this.issuingPost = issuingPost;
    }

    public Date getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(Date issuingDate) {
        this.issuingDate = issuingDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
