package com.proship.omrs.candidate.event.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "participant_event_type")
public class ParticipantEventType {

    @Id
    private Long id ;

    private String description ;

    private String styleclass ;

    private Integer ordering ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyleclass() {
        return styleclass;
    }

    public void setStyleclass(String styleclass) {
        this.styleclass = styleclass;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }
}
