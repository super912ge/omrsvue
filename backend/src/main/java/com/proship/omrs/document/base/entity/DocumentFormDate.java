package com.proship.omrs.document.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proship.omrs.base.entity.BaseEntity;

import javax.persistence.MappedSuperclass;
import java.util.Date;
@MappedSuperclass
public class DocumentFormDate extends BaseEntity{

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date value;


    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }


}
