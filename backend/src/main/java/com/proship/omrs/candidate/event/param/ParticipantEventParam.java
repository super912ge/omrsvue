package com.proship.omrs.candidate.event.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ParticipantEventParam {

    private Long id;

    @NotNull
    private String text;

    @NotNull
    private Long type;

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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
