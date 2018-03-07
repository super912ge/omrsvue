package com.proship.omrs.candidate.info.service;

import com.proship.omrs.candidate.info.param.CreateInfoParam;

public interface ParticipantInfoService {

    Long addNewInfo(Long id, CreateInfoParam param);
}
