package com.proship.omrs.user.repository;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.param.UserBrief;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

@Lazy
public interface UserRepository extends JpaRepository<User,Long>,QueryDslPredicateExecutor<User>{

    User findByName(String username);


}
