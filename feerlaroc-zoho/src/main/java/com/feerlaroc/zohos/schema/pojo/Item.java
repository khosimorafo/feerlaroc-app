package com.feerlaroc.zohos.schema.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feerlaroc.core.entity.EntityInterface;

/**
 * Created by root on 2016/02/18.
 */
public class Item implements EntityInterface {

    @JsonIgnore
    private final String DB_KEY = "items";

    @Override
    public String id() {
        return null;
    }

    @Override
    public String DBKey() {
        return DB_KEY;
    }

    @Override
    public <T> T getValue(Class<T> clazz) {
        return null;
    }

}
