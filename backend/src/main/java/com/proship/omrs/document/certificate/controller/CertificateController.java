package com.proship.omrs.document.certificate.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.certificate.entity.Certificate;
import com.proship.omrs.document.certificate.repository.CertificateRepository;
import com.proship.omrs.utils.util.Utils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j
@RestController
@RequestMapping(value="/certificate/")
@CrossOrigin
public class CertificateController {


    @Autowired
    @Qualifier("certificateService")
    DocumentService certificateService;

//    @RequestMapping(value = "/search/candidate",method= RequestMethod.POST, produces = {"application/json"})
//    public ResponseEntity<Set<Long>> searchCandidate(@RequestBody DocumentSearchTerm term){
//
//        return new ResponseEntity<>(
//                certificateService.searchCandidate(term), HttpStatus.OK);
//    }

    @RequestMapping(value = "create/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>>createDocument(@PathVariable("id")Long id,
            @RequestBody CreateEditDocumentParam param){

        Long certificateId = certificateService.create(id,param);

        return Utils.getResponseEntityWithResultMap(certificateId);
        
    }
    
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long certificateId = certificateService.update(param);

        return Utils.getResponseEntityWithResultMap(certificateId);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

        certificateService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }

}
