package com.proship.omrs.base.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class CustomizedRepositoryImpl <T,ID extends Serializable>
        extends SimpleJpaRepository<T,ID> implements CustomizedRepository<T,ID> {

           private final EntityManager entityManager;

           public CustomizedRepositoryImpl(JpaEntityInformation  entityInformation, EntityManager entityManager) {
               super(entityInformation, entityManager);
               this.entityManager = entityManager;
           }


           @Transactional
           @Override
            public void refresh(T o) {
               entityManager.refresh(o);
            }
}
