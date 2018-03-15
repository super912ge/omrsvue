package com.proship.omrs.candidate.homeAirport.service;

import com.proship.omrs.candidate.homeAirport.param.CreateHomeAirportParam;
import com.proship.omrs.candidate.homeAirport.param.UpdateHomeAirportParam;

import java.util.List;

public interface HomeAirportService {

    Long create(Long id, CreateHomeAirportParam param);

    Long update(UpdateHomeAirportParam param);

    Long delete(Long id);
}
