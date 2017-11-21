package com.proship.omrs.evaluation.param;

import java.util.Set;

public class EvaluationSearchParam {

    Set<InstrumentRatingSearchParam> instrumentCriteria;

    Set<GeneralRatingParam> languageCriteria;

    Set<GeneralRatingParam> presentationCriteria;

    Set<GeneralRatingParam> classificationCriteria;

    Set<Long> nonInstrumentCriteria;

    public Set<InstrumentRatingSearchParam> getInstrumentCriteria() {
        return instrumentCriteria;
    }

    public void setInstrumentCriteria(Set<InstrumentRatingSearchParam> instrumentCriteria) {
        this.instrumentCriteria = instrumentCriteria;
    }

    public Set<GeneralRatingParam> getLanguageCriteria() {
        return languageCriteria;
    }

    public void setLanguageCriteria(Set<GeneralRatingParam> languageCriteria) {
        this.languageCriteria = languageCriteria;
    }

    public Set<GeneralRatingParam> getPresentationCriteria() {
        return presentationCriteria;
    }

    public void setPresentationCriteria(Set<GeneralRatingParam> presentationCriteria) {
        this.presentationCriteria = presentationCriteria;
    }

    public Set<GeneralRatingParam> getClassificationCriteria() {
        return classificationCriteria;
    }

    public void setClassificationCriteria(Set<GeneralRatingParam> classificationCriteria) {
        this.classificationCriteria = classificationCriteria;
    }

    public Set<Long> getNonInstrumentCriteria() {
        return nonInstrumentCriteria;
    }

    public void setNonInstrumentCriteria(Set<Long> nonInstrumentCriteria) {
        this.nonInstrumentCriteria = nonInstrumentCriteria;
    }
}
