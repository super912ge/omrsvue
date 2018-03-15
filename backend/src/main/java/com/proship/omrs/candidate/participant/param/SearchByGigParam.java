package com.proship.omrs.candidate.participant.param;


public class SearchByGigParam extends com.proship.omrs.candidate.group.param.SearchByGigParam{

    private Boolean nonPs;

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
