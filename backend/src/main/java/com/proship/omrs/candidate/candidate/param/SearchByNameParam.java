package com.proship.omrs.candidate.candidate.param;

public class SearchByNameParam {

    private String name;

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
