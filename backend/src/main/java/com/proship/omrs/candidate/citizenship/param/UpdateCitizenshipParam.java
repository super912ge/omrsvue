package com.proship.omrs.candidate.citizenship.param;

import java.util.List;

public class UpdateCitizenshipParam {

    List<Long> citizenshipIds;

    List<Long> countryIds;

    public List<Long> getCitizenshipIds() {
        return citizenshipIds;
    }

    public void setCitizenshipIds(List<Long> citizenshipIds) {
        this.citizenshipIds = citizenshipIds;
    }

    public List<Long> getCountryIds() {
        return countryIds;
    }

    public void setCountryIds(List<Long> countryIds) {
        this.countryIds = countryIds;
    }
}
