package com.proship.omrs.document.certificate.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.document.certificate.entity.CertificateType;
import com.proship.omrs.document.certificate.repository.CertificateTypeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate-type/")
public class CertificateTypeController extends BaseController<CertificateType,Long>{
    public CertificateTypeController(CertificateTypeRepository repo) {
        super(repo);
    }
}
