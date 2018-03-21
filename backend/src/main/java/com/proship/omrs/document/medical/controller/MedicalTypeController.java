package com.proship.omrs.document.medical.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.medical.entity.MedicalType;
import com.proship.omrs.document.medical.repository.MedicalTypeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/medicalType/")
@RestController
public class MedicalTypeController extends BaseController<MedicalType,Long> {
    public MedicalTypeController(MedicalTypeRepository repo) {
        super(repo);
    }


}
