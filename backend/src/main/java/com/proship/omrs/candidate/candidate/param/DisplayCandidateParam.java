package com.proship.omrs.candidate.candidate.param;

import java.util.List;
import java.util.Set;

public class DisplayCandidateParam {

    List<Long> ids;

    Integer page;

    Integer size;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
