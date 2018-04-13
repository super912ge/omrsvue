package com.proship.omrs.candidate.address.param;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class UpdateAddressParam extends CreateAddressParam{


    @NotNull
    @Range(min = 0)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
