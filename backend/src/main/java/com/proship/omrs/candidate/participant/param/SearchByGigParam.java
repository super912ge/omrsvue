package com.proship.omrs.candidate.participant.param;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SearchByGigParam extends com.proship.omrs.candidate.group.param.SearchByGigParam{


    private Boolean nonPs;

    @NotNull
    @Min(1)
    private Long rank;

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Boolean getNonPs() {
        return nonPs;
    }

    public void setNonPs(Boolean nonPs) {
        this.nonPs = nonPs;
    }
}
