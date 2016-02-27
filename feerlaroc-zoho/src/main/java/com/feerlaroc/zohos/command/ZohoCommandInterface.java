package com.feerlaroc.zohos.command;

import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;

/**
 * Created by root on 2016/02/17.
 */
public interface ZohoCommandInterface {

    <T> void add(T t, final FrameworkCompletionListener listener);
    <T> void update(T t, final FrameworkCompletionListener listener);

    <T> void remove(T t, final FrameworkCompletionListener listener);
    <T> void remove(String key, String id, final FrameworkCompletionListener listener);

    <T> void get(T t, final FrameworkCompletionListener listener);

    ZohoApiService getApiService();

}
