package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import com.proship.omrs.contract.entity.Comment;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class CommentDataShard extends BaseEntityWithCreator{

    @Id
    private Long id;

    private String text;

    @ManyToOne
    @JsonIgnore
    private Comment comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
