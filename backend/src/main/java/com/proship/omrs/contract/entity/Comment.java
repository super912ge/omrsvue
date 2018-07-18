package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="comment_id_seq")
    @SequenceGenerator(
            name="comment_id_seq",
            sequenceName="comment_id_sequence"
    )
    private Long id;

    private Long uuid;

    @OneToOne(mappedBy = "comment")
    private CommentDataShard commentDataShard;

    @OneToMany(mappedBy = "comment")
    @JsonIgnore
    private List<ContractEvent> contractEvents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public CommentDataShard getCommentDataShard() {
        return commentDataShard;
    }

    public void setCommentDataShard(CommentDataShard commentDataShard) {
        this.commentDataShard = commentDataShard;
    }

    public List<ContractEvent> getContractEvents() {
        return contractEvents;
    }

    public void setContractEvents(List<ContractEvent> contractEvents) {
        this.contractEvents = contractEvents;
    }
}
