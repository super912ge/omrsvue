package com.proship.omrs.document.passport.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.passport.entity.Passport;
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

@RequestMapping("/passport/")
@RestController
public class PassportController extends BaseController<Passport,Long>{
    public PassportController(CrudRepository<Passport, Long> repo) {
        super(repo);
    }
    
    @Autowired
    @Qualifier("passportService")
    DocumentService passportService;

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String,Object>> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long passportId = passportService.create(id,param);

       return Utils.getResponseEntityWithResultMap(passportId);
    }

    @RequestMapping("update")
    public ResponseEntity<Map<String,Object>> updateDocument(
                                                             @RequestBody CreateEditDocumentParam param){

        Long passportId = passportService.update(param);

      return Utils.getResponseEntityWithResultMap(passportId);
    }

    @RequestMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

         passportService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }


}
