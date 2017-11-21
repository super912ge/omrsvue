package com.proship.omrs.system.country.entity;

import com.google.common.collect.HashBiMap;
import com.proship.omrs.role.entity.RoleMap;
import com.proship.omrs.system.country.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

public class CountryMap implements InitializingBean {

    static final Logger logger = LoggerFactory.getLogger(RoleMap.class);
    private static Map<Long,Country> countryMap;

    @Autowired
    CountryRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[RoleMap][afterPropertiesSet]start");

        List<Country> list = repo.findAll();

        countryMap = HashBiMap.create();

        for (Country country: list){
            countryMap.put(country.getId(),country);
        }

        logger.info("[CountryMap][afterPropertiesSet]finish setting "+ countryMap.size()+" countries. ");
    }
    public static Map getcountryMap(){
        return  countryMap;
    };

    public static Country getCountry(Long id){
        return countryMap.get(id);
    }
}
