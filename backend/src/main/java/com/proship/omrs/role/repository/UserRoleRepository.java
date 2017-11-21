package com.proship.omrs.role.repository;

import com.proship.omrs.role.entity.UserRole;
import com.proship.omrs.role.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,UserRoleId>{

}
