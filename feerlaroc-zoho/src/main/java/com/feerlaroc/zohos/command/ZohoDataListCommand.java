package com.feerlaroc.zohos.command;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;

import rx.Observable;

/**
 * Created by root on 2016/02/23.
 */
public class ZohoDataListCommand<T extends EntityInterface> extends ZohoServiceCommand {


    @Override
    public <T> void execute(T entity) {

    }

    @Override
    public <T> void execute(T entity, FrameworkCompletionListener listener) {

    }

    public Observable execute(T t){

        return getDataListObservable(t);

    }
}
