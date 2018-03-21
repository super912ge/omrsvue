package com.proship.omrs.document.base.service;

import com.proship.omrs.document.base.param.CreateEditDocumentParam;
import com.proship.omrs.document.base.param.DocumentSearchTerm;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DocumentService {

//    Set<Long> searchCandidate(DocumentSearchTerm term);

    Long create(Long id, CreateEditDocumentParam param);

    Long delete(Long id);

    Long update(CreateEditDocumentParam param);

}
