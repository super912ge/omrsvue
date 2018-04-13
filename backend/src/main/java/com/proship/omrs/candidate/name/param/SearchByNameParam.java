package com.proship.omrs.candidate.name.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SearchByNameParam {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    private String searchType;

    public String getName() {

        this.name = this.name.toLowerCase();
        this.name.replaceAll("\\s+"," ");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
