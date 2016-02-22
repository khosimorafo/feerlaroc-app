package com.feerlaroc.core.app;


import com.feerlaroc.core.Command;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.NetworkCompletionListener;

import com.feerlaroc.core.error.FrameworkException;

import java.io.Closeable;


/**
 * Created by root on 2016/02/13.
 */
public interface App extends Closeable{

    public <T extends EntityInterface> void create(final Class<T> type, final T entity, NetworkCompletionListener listener) throws FrameworkException;

    public <T extends EntityInterface> void remove(final Class<T> type, final T entity, NetworkCompletionListener listener) throws FrameworkException;
    public <T extends EntityInterface> void remove(final Class<T> type, final String id, NetworkCompletionListener listener) throws FrameworkException;

    public <T extends EntityInterface> T retrieve(final Class<T> type, final String id, NetworkCompletionListener listener) throws FrameworkException;

    public <T extends Command> T command(final Class<T> commandType);

}
