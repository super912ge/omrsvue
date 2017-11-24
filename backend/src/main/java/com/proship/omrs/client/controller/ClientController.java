package com.proship.omrs.client.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.client.entity.Client;
import com.proship.omrs.client.repository.ClientRepository;

public class ClientController extends BaseController<Client,Long>{
    public ClientController(ClientRepository repo) {
        super(repo);
    }
}
