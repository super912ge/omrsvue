package com.proship.omrs.document.certificate.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.base.param.DocumentSearchTerm;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.certificate.entity.Certificate;
import com.proship.omrs.document.certificate.repository.CertificateRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Log4j
@RestController
@RequestMapping(value="/certificate/")
@CrossOrigin
public class CertificateController extends BaseController<Certificate,Long>{


    @Autowired

    public CertificateController(CertificateRepository repo) {
        super(repo);
    }
    @Autowired
    @Qualifier("certificateService")
    DocumentService certificateService;

    @RequestMapping(value = "/search/candidate",method= RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Set<Long>> searchCandidate(@RequestBody DocumentSearchTerm term){

        return new ResponseEntity<>(
                certificateService.searchCandidate(term), HttpStatus.OK);
    }
}
