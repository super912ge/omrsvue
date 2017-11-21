package com.proship.omrs.evaluation.param;

public class InstrumentRatingSearchParam {


    private Long instrumentTypeId;

    private Long ratingTypeId;

    private Long ratingId;

    public Long getInstrumentTypeId() {
        return instrumentTypeId;
    }

    public void setInstrumentTypeId(Long instrumentTypeId) {
        this.instrumentTypeId = instrumentTypeId;
    }

    public Long getRatingTypeId() {
        return ratingTypeId;
    }

    public void setRatingTypeId(Long ratingTypeId) {
        this.ratingTypeId = ratingTypeId;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }
}
