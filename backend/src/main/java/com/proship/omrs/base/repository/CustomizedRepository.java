package com.proship.omrs.base.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomizedRepository<T,ID extends Serializable> extends JpaRepository<T,ID>{

    void refresh(T t);


}
