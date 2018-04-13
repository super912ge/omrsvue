package com.proship.omrs.candidate.info.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateInfoParam {

    @NotNull
    @Min(1)
    private String type;

    @NotNull
    @Min(2)
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
