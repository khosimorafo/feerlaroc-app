package com.feerlaroc.zohos.command;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;
import com.feerlaroc.zohos.service.ZohoService;


/**
 * Created by root on 2016/02/17.
 */
public abstract class ZohoServiceCommand<T extends EntityInterface> extends Command implements ZohoCommandInterface {

    ZohoApiService mZohoApiService;

    @Override
    public <T> void add(T t, final FrameworkCompletionListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE); //ZohoApi.getInstance().get();

        //Call<EntityInterface> call = mZohoApiService.create(_entity.DBKey(), _entity);


    }



    public <T> void get(T t, final FrameworkCompletionListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE); //ZohoApi.getInstance().get();

        //Call<EntityInterface> call = mZohoApiService.get(_entity.DBKey(), _entity.id());

    }

    @Override
    public <T> void update(T t, final FrameworkCompletionListener listener) {

    }

    @Override
    public <T> void remove(T t, final FrameworkCompletionListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        remove(_entity.DBKey(), _entity.id(), listener);

    }

    @Override
    public void remove(String key, String id, final FrameworkCompletionListener listener) {

        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE);


    }

    @Override
    public ZohoApiService getApiService(){

        ZohoApiService service = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE);
        return service;

    }

    @Override
    public Class getServiceClass() {
        return ZohoService.class;
    }

}
