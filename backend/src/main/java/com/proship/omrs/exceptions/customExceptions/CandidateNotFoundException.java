package com.proship.omrs.exceptions.customExceptions;




public class CandidateNotFoundException extends OmrsResourceNotFoundException {

    public CandidateNotFoundException(String exception, Long id) {
        super(exception);

        candidateId = id;
    }

    public CandidateNotFoundException( Long id) {


        super("Candidate with "+ id +" is not found.");

        candidateId = id;


    }

    private Long candidateId;

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
}

