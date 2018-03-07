package com.proship.omrs.candidate.citizenship.service;


import java.util.List;

public interface CitizenshipService {


    List<Long> addCitizenship(Long id, List<Long> countryId);

}
