package com.proship.omrs.base.entity;


import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntityTts<T> extends BaseEntityWithCreator {

    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
