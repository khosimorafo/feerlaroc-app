package org.feerlaroc.force.model;

import org.feerlaroc.force.rest.FeerlarocRestRequest;

import java.util.Map;

/**
 * Created by root on 2016/03/17.
 */
public abstract class ForceRequestObject extends ForceValueObject implements ForceEntity {

    protected FeerlarocRestRequest mRestRequest;
    protected Map<String, Object> mObjectMap;

    public abstract Map<String, Object> getObjectMap();

    @Override
    public void setRequest(FeerlarocRestRequest restRequest) {
        mRestRequest = restRequest;
    }

    @Override
    public FeerlarocRestRequest getRequest() {
        return mRestRequest;
    }

}
