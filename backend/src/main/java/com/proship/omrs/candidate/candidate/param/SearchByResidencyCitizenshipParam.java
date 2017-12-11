package com.proship.omrs.candidate.candidate.param;

import com.proship.omrs.system.country.entity.Country;

public class SearchByResidencyCitizenshipParam {

    private Long residency;

    private Long citizenship;

    public Long getResidency() {
        return residency;
    }

    public void setResidency(Long residency) {
        this.residency = residency;
    }

    public Long getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Long citizenship) {
        this.citizenship = citizenship;
    }
}
