package com.feerlaroc.zohos.command;

import com.feerlaroc.zohos.schema.callback.ZohoApiListener;

/**
 * Created by root on 2016/02/17.
 */
public interface ZohoCommandInterface {

    public <T> void add(T t, ZohoApiListener listener);
    public <T> void update(T t, ZohoApiListener listener);
    public <T> void remove(T t, ZohoApiListener listener);

}
