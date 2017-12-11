package com.proship.omrs.candidate.candidate.param;

import java.util.Set;

public class DisplayCandidateParam {

    Set<Long> ids;

    Integer page;

    Integer size;

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
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
