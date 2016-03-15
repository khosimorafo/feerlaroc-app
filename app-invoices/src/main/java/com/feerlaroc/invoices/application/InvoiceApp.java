package com.feerlaroc.invoices.application;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.Services;
import com.feerlaroc.core.app.App;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.zohos.command.AddZohoEntityCommand;
import com.feerlaroc.zohos.core.ZohoApiService;

import java.io.IOException;

/**
 * Created by root on 2016/02/23.
 */
public class InvoiceApp implements App {

    @Override
    public <T extends EntityInterface> void create(Class<T> type, T entity, FrameworkCompletionListener listener)
            throws FrameworkException {

        AddZohoEntityCommand command = command(AddZohoEntityCommand.class);
        command.addNetworkCompletionListener(listener);
        command.execute(entity, listener);
    }

    @Override
    public <T extends EntityInterface> void remove(Class<T> type, T entity, FrameworkCompletionListener listener)
            throws FrameworkException {

    }

    @Override
    public <T extends EntityInterface> void remove(Class<T> type, String id, FrameworkCompletionListener listener)
            throws FrameworkException {

    }

    @Override
    public <T extends EntityInterface> T retrieve(Class<T> type, String id, FrameworkCompletionListener listener)
            throws FrameworkException {
        return null;
    }

    @Override
    public <T extends Command> T command(Class<T> commandType) {
        return Services.getInstance().command(commandType);
    }

    @Override
    public ZohoApiService getService() {

        return  null;

    }

    @Override
    public void close() throws IOException {

    }

}
