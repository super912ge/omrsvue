package com.proship.omrs.user.repository;

import com.proship.omrs.user.param.UserBrief;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBriefRepository extends JpaRepository<UserBrief,Long> {
}
