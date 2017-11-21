package com.proship.omrs.document.base.service;

import com.proship.omrs.document.base.param.DocumentSearchTerm;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DocumentService {

    Set<Long> searchCandidate(DocumentSearchTerm term);

}
