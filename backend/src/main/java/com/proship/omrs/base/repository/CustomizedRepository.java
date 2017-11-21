package com.proship.omrs.base.repository;

import com.proship.omrs.user.entity.User;
import org.springframework.data.domain.Page;

public interface CustomizedRepository<T,ID> {
    Page<T> findByUser(User user);

}
