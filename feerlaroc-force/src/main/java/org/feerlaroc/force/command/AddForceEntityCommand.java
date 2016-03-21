package org.feerlaroc.force.command;

import com.feerlaroc.core.entity.EntityInterface;

import org.feerlaroc.force.listener.ForceCompletionListener;
import org.feerlaroc.force.model.ForceRequestObject;
import org.feerlaroc.force.rest.FeerlarocRestRequest;
import org.feerlaroc.force.schema.helper.Constants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by root on 2016/03/17.
 */
public class AddForceEntityCommand<T extends EntityInterface> extends ForceServiceCommand {

    ForceRequestObject mForceEntity;

    @Override
    public <T> void execute(T entity) {
        execute(entity, null);
    }

    @Override
    public <T, U> void execute(T entity, U listener) {

        mForceEntity = (ForceRequestObject) entity;
        mForceEntity.setRequest(getRestRequest());
        ForceCompletionListener l = (ForceCompletionListener) listener;

        if(mForceEntity.getRequest() == null){
            l.onError();
            return;
        }
        add(mForceEntity, l);
    }


    @Override
    FeerlarocRestRequest getRestRequest() {

        FeerlarocRestRequest rest_request = null;
        try {

            return FeerlarocRestRequest.getRequestForCreate(Constants.SALESFORCE.API,
                    mForceEntity.getEntityType(), mForceEntity.getObjectMap());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rest_request;
    }
}
