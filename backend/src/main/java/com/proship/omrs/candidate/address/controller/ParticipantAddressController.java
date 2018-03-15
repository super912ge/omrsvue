package com.proship.omrs.candidate.address.controller;

import com.proship.omrs.candidate.address.param.CreateAddressParam;
import com.proship.omrs.candidate.address.param.UpdateAddressParam;
import com.proship.omrs.candidate.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/address/")
public class ParticipantAddressController {


    @Autowired
    private AddressService addressService;

    @RequestMapping("create/{id}")
    public ResponseEntity<Map<String, Object>> addNewAddress(@PathVariable("id") Long id, @RequestBody CreateAddressParam param){

        Long result = addressService.addAddress(id,param);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        m.put("result",result);

        return new ResponseEntity<Map<String, Object>>(m, HttpStatus.OK);

    }
    @RequestMapping("update")
    public ResponseEntity<Map<String,Object>>update(@RequestBody UpdateAddressParam param){

        Long id = addressService.update(param);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        m.put("result",id);

        return new ResponseEntity<Map<String, Object>>(m, HttpStatus.OK);
    }


    @RequestMapping("delete/{id}")
    public ResponseEntity<Map<String,Object>>update(@PathVariable("id")Long id){

        addressService.delete(id);

        Map<String,Object> m = new HashMap<>();

        m.put("success", true);

        return new ResponseEntity<Map<String, Object>>(m, HttpStatus.OK);
    }
}
