package com.proship.omrs.document.base.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DocumentRepository {

    Set<Long> findParticipant(Date expiryDate,List<Long> types, Long num);
}
