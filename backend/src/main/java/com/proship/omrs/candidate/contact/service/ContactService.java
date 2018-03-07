package com.proship.omrs.candidate.contact.service;

import com.proship.omrs.candidate.contact.param.CreateContactParam;



public interface ContactService {

    Long create(Long candidateId, CreateContactParam param);
}
