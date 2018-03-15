package com.proship.omrs.candidate.contact.service;

import com.proship.omrs.candidate.contact.param.CreateContactParam;
import com.proship.omrs.candidate.contact.param.UpdateContactParam;


public interface ContactService {

    Long create(Long candidateId, CreateContactParam param);

    Long update(UpdateContactParam param);

    Long delete(Long id);
}
