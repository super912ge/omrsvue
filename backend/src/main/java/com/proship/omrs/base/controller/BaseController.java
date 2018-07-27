package com.proship.omrs.base.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Slf4j
@RestController
public abstract class BaseController<T, ID extends Serializable> {

	    protected CrudRepository<T, ID> repo;


	    public BaseController(CrudRepository<T, ID> repo) {
	        this.repo = repo;
	    }

	    @RequestMapping(method = RequestMethod.GET)
		@ApiOperation(value = "Return all the entities stored in database.")
	    public ResponseEntity<List<T>> listAll() {
	        Iterable<T> all = this.repo.findAll();
	        return new ResponseEntity<>(Lists.newArrayList(all),HttpStatus.OK);
	    }

		@ApiOperation(value = "Return created entity.")
	    @RequestMapping(value="create",method=RequestMethod.POST,consumes="application/json")
	    public ResponseEntity<T> create(@RequestBody T json) {

	        T created = this.repo.save(json);
	        return new ResponseEntity<>(created,HttpStatus.CREATED);
	    }

	    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces = {"application/json"})
	    @ApiOperation("Return the entity by given id.")
	    public ResponseEntity<T> get(@PathVariable ID id) {

			T t = null;

	    	try {
				t = this.repo.findById(id).orElse(null);
			}catch (Exception e){

				e.printStackTrace();
			}
	      
	         return new ResponseEntity<>(t,HttpStatus.OK);
	    }

	    @SuppressWarnings("deprecation")
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		@ApiOperation("Update entity with given id.")
	    public ResponseEntity<T>  update(@PathVariable ID id, @RequestBody T json) {

	        T entity = this.repo.findById(id).orElse(null);
	        try {

	            BeanUtils.copyProperties(entity, json);
	        }
	        catch (Exception e) {
	      //      log.warn("while copying properties", e);
	            throw Throwables.propagate(e);
	        }

	        T updated = this.repo.save(entity);


	        return new ResponseEntity<>(updated,HttpStatus.OK);
	    }

	    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		@ApiOperation("Delete entity with given id.")
	    public ResponseEntity<ID> delete(@PathVariable ID id) {
	        this.repo.deleteById(id);
	        return new ResponseEntity<>(id,HttpStatus.OK);
	    }
	

}
