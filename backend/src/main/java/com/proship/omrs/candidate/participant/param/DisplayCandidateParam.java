package com.proship.omrs.candidate.participant.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DisplayCandidateParam {

    @NotNull
    @Size(min = 1)
    List<Long> ids;
    @NotNull
    @Size(min = 0)
    Integer page;

    @NotNull
    @Size(min = 2)
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
