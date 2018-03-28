package com.proship.omrs.document.seamansBook.controller;


import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.utils.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/seamansBook/")
public class SeamanBookController {


    @Autowired
    @Qualifier("seamansBookService")
    DocumentService seamanBookService;

    @RequestMapping(value = "create/{id}",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long seamanBookId = seamanBookService.create(id,param);

        return Utils.getResponseEntityWithResultMap(seamanBookId);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long seamanBookId = seamanBookService.update(param);

        return Utils.getResponseEntityWithResultMap(seamanBookId);
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

        seamanBookService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }

}
