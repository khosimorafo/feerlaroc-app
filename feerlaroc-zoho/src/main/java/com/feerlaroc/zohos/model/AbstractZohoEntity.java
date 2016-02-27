package com.feerlaroc.zohos.model;

import java.util.UUID;

/**
 * Created by root on 2016/02/24.
 */
public abstract class AbstractZohoEntity {

    private String id;

    protected AbstractZohoEntity(){
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
