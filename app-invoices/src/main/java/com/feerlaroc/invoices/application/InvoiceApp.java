package com.feerlaroc.invoices.application;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.app.App;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.zohos.command.AddZohoEntityCommand;

import java.io.IOException;

/**
 * Created by root on 2016/02/23.
 */
public class InvoiceApp implements App {

    @Override
    public <T extends EntityInterface> void create(Class<T> type, T entity, FrameworkCompletionListener listener)
            throws FrameworkException {

        AddZohoEntityCommand<T> command = command(AddZohoEntityCommand.class);
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
        return null;
    }

    @Override
    public void close() throws IOException {

    }

}
