package com.proship.omrs.evaluation.param;

import java.util.Date;

public class EvaluationParam {

    private Date auditionDate;

    private Date evaluationDate;

    private Long recruiter;

    private String comment;

    public Date getAuditionDate() {
        return auditionDate;
    }

    public void setAuditionDate(Date auditionDate) {
        this.auditionDate = auditionDate;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Long getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Long recruiter) {
        this.recruiter = recruiter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
