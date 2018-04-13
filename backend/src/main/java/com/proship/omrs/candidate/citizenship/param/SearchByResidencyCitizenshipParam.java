package com.proship.omrs.candidate.citizenship.param;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

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
