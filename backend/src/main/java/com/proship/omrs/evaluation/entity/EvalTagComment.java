package com.proship.omrs.evaluation.entity;

import com.proship.omrs.base.entity.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "eval_tag_comment_tts")
@Where(clause = "nexttransactiontime> current_date")
public class EvalTagComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="eval_tag_comment_tts_id_seq")
    @SequenceGenerator(
            name="eval_tag_comment_tts_id_seq",
            sequenceName="eval_tag_comment_tts_id_sequence"
    )
    private Long id;

    private Long creatorId;

    private String text;

    @ManyToOne
    private EvalTag evalTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EvalTag getEvalTag() {
        return evalTag;
    }

    public void setEvalTag(EvalTag evalTag) {
        this.evalTag = evalTag;
    }
}
