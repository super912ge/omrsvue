package com.proship.omrs.document.visa.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.visa.entity.VisaType;
import com.proship.omrs.document.visa.repository.VisaTypeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/visaType/")
public class VisaTypeController extends BaseController<VisaType,Long>{
    public VisaTypeController(VisaTypeRepository repo) {
        super(repo);
    }
}
