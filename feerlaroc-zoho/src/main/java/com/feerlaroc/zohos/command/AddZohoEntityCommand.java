package com.feerlaroc.zohos.command;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.zohos.listener.ZohoCallCompletionListener;

/**
 * Created by root on 2016/02/18.
 */

public class AddZohoEntityCommand<T extends EntityInterface> extends ZohoServiceCommand {

    @Override
    public <T> void execute(T entity) {}

    @Override
    public <T, U> void execute(T entity, U listener) {
        ZohoCallCompletionListener l = (ZohoCallCompletionListener) listener;
        add(entity, l);
    }


}
