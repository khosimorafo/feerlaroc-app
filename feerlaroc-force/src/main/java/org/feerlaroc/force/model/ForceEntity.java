package org.feerlaroc.force.model;

import com.feerlaroc.core.entity.EntityInterface;

import org.feerlaroc.force.rest.FeerlarocRestRequest;

/**
 * Created by root on 2016/03/17.
 */
public interface ForceEntity extends EntityInterface {

    void setRequest(FeerlarocRestRequest restRequest);
    FeerlarocRestRequest getRequest();
    String getSQOL();
    String getEntityType();

}
