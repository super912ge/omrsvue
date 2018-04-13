package com.proship.omrs.candidate.event.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class ParticipantEventCommentTts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="participant_event_comment_tts_id_seq")
    @SequenceGenerator(
            name="participant_event_comment_tts_id_seq",
            sequenceName="participant_event_comment_tts_id_sequence"
    )
    private Long id;

    private String text;

    @JsonIgnore
    private Date nexttransactiontime ;

    @JsonIgnore
    private Long creatorId;

    @JsonIgnore
    private Date transactiontime ;

    public Date getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Date transactiontime) {
        this.transactiontime = transactiontime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

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

    public Date getNexttransactiontime() {
        return nexttransactiontime;
    }

    public void setNexttransactiontime(Date nexttransactiontime) {
        this.nexttransactiontime = nexttransactiontime;
    }
}
