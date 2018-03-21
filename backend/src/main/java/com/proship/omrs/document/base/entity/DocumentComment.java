package com.proship.omrs.document.base.entity;

import com.proship.omrs.base.entity.BaseEntity;
import com.proship.omrs.base.entity.BaseEntityWithCreator;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DocumentComment extends BaseEntityWithCreator{

    private String text;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
