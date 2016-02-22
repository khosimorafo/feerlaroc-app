package com.feerlaroc.zohos.command;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.FrameworkListener;
import com.feerlaroc.zohos.schema.callback.ZohoApiListener;

/**
 * Created by root on 2016/02/18.
 */
public class AddZohoEntityCommand<T extends EntityInterface> extends ZohoServiceCommand {

    @Override
    public <T> void execute(T entity) {}

    @Override
    public <T> void execute(T entity, FrameworkListener listener) {
        add(entity, (ZohoApiListener) listener);
    }

}
