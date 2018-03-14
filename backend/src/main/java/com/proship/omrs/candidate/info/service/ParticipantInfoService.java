package com.proship.omrs.candidate.info.service;

import com.proship.omrs.candidate.info.param.CreateInfoParam;
import com.proship.omrs.candidate.info.param.UpdateInfoParam;

public interface ParticipantInfoService {

    Long addNewInfo(Long id, CreateInfoParam param);

    Long update(UpdateInfoParam param);

    Long delete(Long id);
}
