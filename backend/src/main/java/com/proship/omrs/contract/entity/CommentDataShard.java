package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proship.omrs.base.entity.BaseEntityWithCreator;
import com.proship.omrs.contract.entity.Comment;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "nexttransactiontime > now()")
public class CommentDataShard extends BaseEntityWithCreator{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="comment_data_shard_id_seq")
    @SequenceGenerator(
            name="comment_data_shard_id_seq",
            sequenceName="comment_data_shard_id_sequence"
    )
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
