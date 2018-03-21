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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/medical/")
public class MedicalController extends BaseController<Medical,Long>{

    @Autowired
    @Qualifier("medicalService")
    DocumentService medicalService;

    public MedicalController(CrudRepository<Medical, Long> repo) {
        super(repo);
    }

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String,Object>> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long medicalId = medicalService.create(id,param);

        return Utils.getResponseEntityWithResultMap(medicalId);
    }

    @RequestMapping("update")
    public ResponseEntity<Map<String,Object>> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long medicalId = medicalService.update(param);

        return Utils.getResponseEntityWithResultMap(medicalId);
    }

    @RequestMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

        medicalService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }

}
