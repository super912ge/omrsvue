package com.proship.omrs.client.entity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.proship.omrs.client.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class ClientMap implements InitializingBean{

    static final Logger logger = LoggerFactory.getLogger(ClientMap.class);
    private static BiMap<Long, Client > clients;

    @Autowired
    ClientRepository repo;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("[clientMap][afterPropertiesSet]start");

        List<Client > list = repo.findAll();

        clients = HashBiMap.create();

        for (Client client  : list) {

            clients.put(client .getId(), client );
        }

        logger.info("[clientMap][afterPropertiesSet]finish setting " + clients.size() + " clients.");
    }

    public static Map getClients() {
        return clients;
    }


    public static Client  getClient (Long id) {
        return clients.get(id);
    }
}
