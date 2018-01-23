package com.proship.omrs.gig.param;

import java.util.List;

public class DisplayGigResultParam  {

    private List<GigBrief> resultList;

    private Integer totalPage;

    public List<GigBrief> getResultList() {
        return resultList;
    }

    public void setResultList(List<GigBrief> resultList) {
        this.resultList = resultList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
