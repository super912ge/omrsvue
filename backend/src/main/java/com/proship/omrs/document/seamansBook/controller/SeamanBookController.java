package com.proship.omrs.document.seamansBook.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.service.DocumentService;
import com.proship.omrs.document.seamansBook.entity.SeamansBook;
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
@RequestMapping("/seamansBook/")
public class SeamanBookController extends BaseController<SeamansBook,Long>{
    public SeamanBookController(CrudRepository<SeamansBook, Long> repo) {
        super(repo);
    }

    @Autowired
    @Qualifier("seamansBookService")
    DocumentService seamanBookService;

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String,Object>> createDocument(@PathVariable("id")Long id,
                                                             @RequestBody CreateEditDocumentParam param){

        Long seamanBookId = seamanBookService.create(id,param);

        return Utils.getResponseEntityWithResultMap(seamanBookId);
    }

    @RequestMapping("update")
    public ResponseEntity<Map<String,Object>> updateDocument(
            @RequestBody CreateEditDocumentParam param){

        Long seamanBookId = seamanBookService.update(param);

        return Utils.getResponseEntityWithResultMap(seamanBookId);
    }

    @RequestMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteDocument(@PathVariable("id")Long id){

        seamanBookService.delete(id);

        return Utils.getResponseEntityWithResultMap(null);
    }

}
