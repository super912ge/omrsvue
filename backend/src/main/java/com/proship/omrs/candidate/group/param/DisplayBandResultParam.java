package com.proship.omrs.candidate.group.param;

import java.util.List;

public class DisplayBandResultParam {

    private List<BandBrief> resultList;

    private Integer totalPage;

    public List<BandBrief> getResultList() {
        return resultList;
    }

    public void setResultList(List<BandBrief> resultList) {
        this.resultList = resultList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
