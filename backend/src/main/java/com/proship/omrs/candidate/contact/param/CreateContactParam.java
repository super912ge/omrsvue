package com.proship.omrs.candidate.contact.param;

public class CreateContactParam {

    private Long type;

    private Long method;

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
