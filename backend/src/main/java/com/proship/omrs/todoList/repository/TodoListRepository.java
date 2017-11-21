package com.proship.omrs.todoList.repository;

import com.proship.omrs.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.proship.omrs.todoList.entity.TodoList;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList,Long>,QueryDslPredicateExecutor<TodoList>{

	List<TodoList> findByUser(User user);


}
