package com.proship.omrs.candidate.homeAirport.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateHomeAirportParam {

    @NotNull
    @Min(1)
    private Long airportId;

    private String client;

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
