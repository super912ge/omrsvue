package com.proship.omrs.base.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value="/api/")
public abstract class BaseController<T, ID extends Serializable> {



	    protected CrudRepository<T, ID> repo;


	    public BaseController(CrudRepository<T, ID> repo) {
	        this.repo = repo;
	    }

	    @RequestMapping
	    public ResponseEntity<List<T>> listAll() {
	        Iterable<T> all = this.repo.findAll();
	        return new ResponseEntity<List<T>>(Lists.newArrayList(all),HttpStatus.OK);
	    }

	    @RequestMapping(value="create",method=RequestMethod.POST,consumes="application/json")
	    public ResponseEntity<Map<String, Object>> create(@RequestBody T json) {
	    //    Log.debug("create() with body {} of type {}", json, json.getClass());

	        T created = this.repo.save(json);
	        
	        

	        Map<String, Object> m = Maps.newHashMap();
	        m.put("success", true);
	        m.put("created", created);
	        return new ResponseEntity<Map<String, Object>>(m,HttpStatus.OK);
	    }

	    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {"application/json"})
	   
	    public ResponseEntity<T> get(@PathVariable ID id) {
	         T t = this.repo.findOne(id);
	      
	         return new ResponseEntity<T>(t,HttpStatus.OK);
	    }

	    @SuppressWarnings("deprecation")
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	    public ResponseEntity<Map<String, Object>>  update(@PathVariable ID id, @RequestBody T json) {
	        //logger.debug("update() of id#{} with body {}", id, json);
	       // logger.debug("T json is of type {}", json.getClass());

	        T entity = this.repo.findOne(id);
	        try {

	            BeanUtils.copyProperties(entity, json);
	        }
	        catch (Exception e) {
	      //      log.warn("while copying properties", e);
	            throw Throwables.propagate(e);
	        }

	      //  logger.debug("merged entity: {}", entity);

	        T updated = this.repo.save(entity);
	    //    logger.debug("updated enitity: {}", updated);

	        Map<String, Object> m = Maps.newHashMap();
	        m.put("success", true);
	        m.put("id", id);
	        m.put("updated", updated);
	        return new ResponseEntity<Map<String, Object>>(m,HttpStatus.OK);
	    }

	    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	    public ResponseEntity<Map<String, Object>> delete(@PathVariable ID id) {
	        this.repo.delete(id);
	        Map<String, Object> m = Maps.newHashMap();
	        m.put("success", true);
	        m.put("id", id);
	        return new ResponseEntity<Map<String, Object>>(m,HttpStatus.OK);
	    }
	

}
