package com.proship.omrs.candidate.residency.service;


import java.util.List;

public interface ResidencyService {

    List<Long> addResidency(Long id, List<Long> countryIds);
}
