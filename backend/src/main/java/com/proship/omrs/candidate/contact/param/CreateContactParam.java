package com.proship.omrs.candidate.contact.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateContactParam {

    @NotNull
    @Min(1)
    private Long type;

    @NotNull
    @Min(1)
    private Long method;
    @NotNull
    @Size(min = 5)
    private String text;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getMethod() {
        return method;
    }

    public void setMethod(Long method) {
        this.method = method;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
