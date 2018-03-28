package com.proship.omrs.document.visa.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.visa.entity.Visa;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/visa/")
public class VisaController  {


    @Autowired
    @Qualifier("visaService")
    DocumentService visaService;

    @RequestMapping(value = "create/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long visaId = visaService.create(id,param);

        return Utils.getResponseEntityWithResultMap(visaId);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long visaId = visaService.update(param);

        return Utils.getResponseEntityWithResultMap(visaId);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

        visaService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }
}
