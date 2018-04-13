package com.proship.omrs.candidate.contact.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateContactParam extends CreateContactParam{

    @NotNull
    @Min(1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
