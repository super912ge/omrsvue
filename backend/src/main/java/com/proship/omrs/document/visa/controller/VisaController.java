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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/visa/")
public class VisaController extends BaseController<Visa,Long> {


    @Autowired
    @Qualifier("visaService")
    DocumentService visaService;

    public VisaController(CrudRepository<Visa, Long> repo) {
        super(repo);
    }

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String,Object>> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long visaId = visaService.create(id,param);

        return Utils.getResponseEntityWithResultMap(visaId);
    }

    @RequestMapping("update")
    public ResponseEntity<Map<String,Object>> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long visaId = visaService.update(param);

        return Utils.getResponseEntityWithResultMap(visaId);
    }

    @RequestMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

        visaService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }
}
