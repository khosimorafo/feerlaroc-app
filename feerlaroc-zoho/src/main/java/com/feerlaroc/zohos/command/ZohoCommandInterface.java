package com.feerlaroc.zohos.command;

import com.feerlaroc.zohos.core.ZohoApiService;
import com.feerlaroc.zohos.listener.ZohoCallCompletionListener;

/**
 * Created by root on 2016/02/17.
 */
public interface ZohoCommandInterface {

    <T> void add(T t, final ZohoCallCompletionListener listener);
    <T> void update(T t, final ZohoCallCompletionListener listener);

    <T> void remove(T t, final ZohoCallCompletionListener  listener);
    <T> void remove(String key, String id, final ZohoCallCompletionListener  listener);

    <T> void get(T t, final ZohoCallCompletionListener listener);

    ZohoApiService getApiService();

}
