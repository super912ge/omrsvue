package com.proship.omrs.evaluation.param;

import java.util.Set;

public class NewRatingParam {

    private EvaluationParam evaluation;

    private Set<GeneralRatingParam> classification;

    private Set<GeneralRatingParam> language;

    private InstrumentRatingSearchParam instrument;

    public EvaluationParam getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationParam evaluation) {
        this.evaluation = evaluation;
    }

    public Set<GeneralRatingParam> getClassification() {
        return classification;
    }

    public void setClassification(Set<GeneralRatingParam> classification) {
        this.classification = classification;
    }

    public Set<GeneralRatingParam> getLanguage() {
        return language;
    }

    public void setLanguage(Set<GeneralRatingParam> language) {
        this.language = language;
    }

    public InstrumentRatingSearchParam getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentRatingSearchParam instrument) {
        this.instrument = instrument;
    }
}
