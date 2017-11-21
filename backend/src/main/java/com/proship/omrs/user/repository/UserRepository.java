package com.proship.omrs.user.repository;
import com.proship.omrs.user.entity.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

@Lazy
public interface UserRepository extends JpaRepository<User,Long>,QueryDslPredicateExecutor<User>{

    User findByName(String username);


}
