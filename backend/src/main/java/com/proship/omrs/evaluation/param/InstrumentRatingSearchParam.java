package com.proship.omrs.evaluation.param;

import java.util.List;

public class InstrumentRatingSearchParam {


    private Long instrumentTypeId;

    private List<InstrumentRating> ratings;

    public Long getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public void setInstrumentTypeId(Long instrumentTypeId) {
        this.instrumentTypeId = instrumentTypeId;
    }

    public List<InstrumentRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<InstrumentRating> ratings) {
        this.ratings = ratings;
    }
}
