package com.proship.omrs.todoList.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.todoList.entity.TodoList;
import com.proship.omrs.todoList.repository.TodoListRepository;
import com.proship.omrs.user.entity.CustomUser;
import com.proship.omrs.user.entity.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j
@RestController
@RequestMapping(value="/todo/")
@CrossOrigin
public class TodoListController extends BaseController<TodoList,Long>{


	@Autowired
	public TodoListController( TodoListRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}
	@Autowired
	TodoListRepository repo;

	@Autowired
    UserDetailsService userDetailsService;

	@Override
	public ResponseEntity<List<TodoList>> listAll() {
		//CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication();

		//UserDetails userDetails = ((Authentication)principal).getPrincipal();

		String username =
				(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		CustomUser user = (CustomUser)userDetailsService.loadUserByUsername(username);

		List<TodoList>list = repo.findByUser(new User(user.getUserId()));

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

//    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
//        this.repo.(id);
//        Map<String, Object> m = Maps.newHashMap();
//        m.put("success", true);
//        m.put("id", id);
//        return new ResponseEntity<Map<String, Object>>(m,HttpStatus.OK);
//    }

}
