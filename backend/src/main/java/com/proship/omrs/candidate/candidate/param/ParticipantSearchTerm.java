package com.proship.omrs.candidate.candidate.param;

import com.proship.omrs.document.base.param.DocumentSearchTerm;

import java.util.List;
import java.util.Map;

public class ParticipantSearchTerm {


    private Map<String,List<DocumentSearchTerm>> documentSearchMap;

    private SearchByGigParam param;

    public Map<String, List<DocumentSearchTerm>> getDocumentSearchMap() {
        return documentSearchMap;
    }

    public void setDocumentSearchMap(Map<String, List<DocumentSearchTerm>> documentSearchMap) {
        this.documentSearchMap = documentSearchMap;
    }

    public SearchByGigParam getParam() {
        return param;
    }

    public void setParam(SearchByGigParam param) {
        this.param = param;
    }
}
