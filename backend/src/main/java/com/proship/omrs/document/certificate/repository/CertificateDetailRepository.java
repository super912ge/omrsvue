package com.proship.omrs.document.certificate.repository;

import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.document.certificate.entity.CertificateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface CertificateDetailRepository extends CustomizedRepository<CertificateDetail,Long> {
}
