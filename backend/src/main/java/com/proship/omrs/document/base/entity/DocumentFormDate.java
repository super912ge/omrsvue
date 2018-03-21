package com.proship.omrs.document.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import com.proship.omrs.document.certificate.entity.Certificate;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Date;
@MappedSuperclass
public class DocumentFormDate extends BaseEntityWithCreator{

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date value;


    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }


}
