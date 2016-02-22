package com.feerlaroc.core.service;

/**
 * Created by root on 2016/02/17.
 */
public interface CoreServices {

    void registerInitializationCallback(final InitializationCallback callback);

    <T extends Service> T getService(final Class<T> serviceClass);

}
