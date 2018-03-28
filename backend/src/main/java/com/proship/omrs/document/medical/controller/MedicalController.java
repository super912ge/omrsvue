package com.proship.omrs.document.medical.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.medical.entity.Medical;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/medical/")
public class MedicalController {

    @Autowired
    @Qualifier("medicalService")
    DocumentService medicalService;


    @RequestMapping(value = "create/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long medicalId = medicalService.create(id,param);

        return Utils.getResponseEntityWithResultMap(medicalId);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long medicalId = medicalService.update(param);

        return Utils.getResponseEntityWithResultMap(medicalId);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

        medicalService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }

}
