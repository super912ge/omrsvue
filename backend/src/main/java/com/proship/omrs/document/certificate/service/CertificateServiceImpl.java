package com.proship.omrs.document.certificate.service;

import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.document.base.service.DocumentService;

import com.proship.omrs.document.certificate.repository.CertificateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("certificateService")
public class CertificateServiceImpl implements DocumentService {

    @Autowired
    CertificateRepository repository;

    @Override
    public Set<Long> searchCandidate(DocumentSearchTerm term) {

    return repository.findParticipant(term.getExpireDate(),term.getIds(),(long)term.getIds().size());

    }

}
