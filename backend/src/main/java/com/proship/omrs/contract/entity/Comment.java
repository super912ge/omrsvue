package com.proship.omrs.contract.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    private Long id;

    private Long uuid;

    @OneToOne(mappedBy = "comment")
    private CommentDataShard commentDataShard;

    @OneToOne
    @JsonIgnore
    private ContractEvent contractEvent;

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
}
