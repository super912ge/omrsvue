package com.proship.omrs.candidate.homeAirport.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateHomeAirportParam extends CreateHomeAirportParam{

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
