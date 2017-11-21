package com.proship.omrs.document.certificate.repository;

import com.proship.omrs.document.certificate.entity.CertificateType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateTypeRepository extends JpaRepository<CertificateType,Long> {
}
