package com.proship.omrs.candidate.participant.param;

import java.util.List;

public class DisplayCandidateResultParam {

    private List<CandidateBrief> resultList;

    private Integer totalPage;

    public List<CandidateBrief> getResultList() {
        return resultList;
    }

    public void setResultList(List<CandidateBrief> resultList) {
        this.resultList = resultList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
