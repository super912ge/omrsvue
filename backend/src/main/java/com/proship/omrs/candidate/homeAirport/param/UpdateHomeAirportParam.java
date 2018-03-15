package com.proship.omrs.candidate.homeAirport.param;

public class UpdateHomeAirportParam extends CreateHomeAirportParam{
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
