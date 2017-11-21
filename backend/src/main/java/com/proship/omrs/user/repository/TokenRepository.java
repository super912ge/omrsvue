package com.proship.omrs.user.repository;

import com.proship.omrs.user.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<VerificationToken,Long>{

    VerificationToken findByToken(String token);
}
