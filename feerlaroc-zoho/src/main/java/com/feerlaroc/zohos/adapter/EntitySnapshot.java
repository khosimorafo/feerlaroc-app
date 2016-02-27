package com.feerlaroc.zohos.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2016/02/24.
 */
public class EntitySnapshot {

    String key;
    Map<String, Object> data = new HashMap<>();

    EntitySnapshot(String key){

    }

    protected void setValue(Map<String, Object> value){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
