package com.proship.omrs.candidate.candidate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime > current_date")
public class ParticipantEventCommentTts extends BaseEntityWithCreator{

    @Id
    private Long id;

    private String text;

    @OneToOne
    @JsonIgnore
    private ParticipantEvent comment;

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

    public ParticipantEvent getComment() {
        return comment;
    }

    public void setComment(ParticipantEvent comment) {
        this.comment = comment;
    }
}
