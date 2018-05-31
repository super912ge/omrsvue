package com.proship.omrs.opening.service;

import com.proship.omrs.opening.param.Opening;
import com.proship.omrs.opening.param.OpeningSearchParam;

import java.util.List;

public interface OpeningService {

    List<Opening>findOpeningsByCondition(OpeningSearchParam in);

}
