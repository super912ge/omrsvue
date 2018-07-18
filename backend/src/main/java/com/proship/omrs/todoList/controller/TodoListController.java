package com.proship.omrs.todoList.controller;

import com.proship.omrs.base.controller.BaseController;
import com.proship.omrs.todoList.entity.TodoList;
import com.proship.omrs.todoList.repository.TodoListRepository;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
	UserRepository userRepository;

	@Override
	public ResponseEntity<List<TodoList>> listAll() {


		String username =
				(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = userRepository.findByName(username);

		List<TodoList>list = repo.findByUser(user);

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
