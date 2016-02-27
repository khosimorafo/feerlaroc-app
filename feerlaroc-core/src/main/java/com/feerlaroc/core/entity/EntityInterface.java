package com.feerlaroc.core.entity;

/**
 * Created by root on 2016/02/13.
 */
public interface EntityInterface {

    String id();
    String DBKey();

    <T> T getValue(Class<T> clazz);

}
