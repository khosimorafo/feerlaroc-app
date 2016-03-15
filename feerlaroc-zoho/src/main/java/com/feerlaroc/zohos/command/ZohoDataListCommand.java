package com.feerlaroc.zohos.command;

import android.content.Context;

import com.feerlaroc.core.entity.EntityInterface;

/**
 * Created by root on 2016/02/23.
 */
public class ZohoDataListCommand<T extends EntityInterface> extends ZohoServiceCommand {


    public ZohoDataListCommand(Context context) {
        super(context);
    }

    @Override
    public <T> void execute(T entity) {

    }

    @Override
    public <T, U> void execute(T entity, U listener) {

    }


}
