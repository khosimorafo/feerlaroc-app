package com.feerlaroc.zohos.model;

import com.feerlaroc.core.entity.EntityInterface;
import com.google.gson.JsonObject;

/**
 * Created by root on 2016/03/14.
 */
public interface ZohoRequestObject extends EntityInterface {

    JsonObject getTemplate();
    JsonObject get();

}
