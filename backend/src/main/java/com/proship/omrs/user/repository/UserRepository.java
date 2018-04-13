package com.proship.omrs.user.repository;
import com.proship.omrs.base.repository.CustomizedRepository;
import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.param.UserBrief;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

@Lazy
public interface UserRepository extends CustomizedRepository<User,Long>{

    User findByName(String username);

    @Query("select user.shortName from User user where user.id = :id")
    Long findUsernameById(Long id);


}
