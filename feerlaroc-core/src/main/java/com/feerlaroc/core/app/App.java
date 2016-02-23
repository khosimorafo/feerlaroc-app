package com.feerlaroc.core.app;


import com.feerlaroc.core.Command;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;

import java.io.Closeable;

import rx.Observable;


/**
 * Created by root on 2016/02/13.
 */
public interface App extends Closeable{

    <T extends EntityInterface> void create(final Class<T> type, final T entity, FrameworkCompletionListener listener) throws FrameworkException;

    <T extends EntityInterface> void remove(final Class<T> type, final T entity, FrameworkCompletionListener listener) throws FrameworkException;
    <T extends EntityInterface> void remove(final Class<T> type, final String id, FrameworkCompletionListener listener) throws FrameworkException;

    <T extends EntityInterface> T retrieve(final Class<T> type, final String id, FrameworkCompletionListener listener) throws FrameworkException;

    <T extends Command> T command(final Class<T> commandType);

    <T extends Observable> T get(Class clazz);

}
