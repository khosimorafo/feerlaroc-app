package com.feerlaroc.zohos.command;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.zohos.controller.ZohoApi;
import com.feerlaroc.zohos.core.ZohoApiService;
import com.feerlaroc.zohos.listener.ZohoCallCompletionListener;
import com.feerlaroc.zohos.schema.helper.Constants;
import com.feerlaroc.zohos.service.ZohoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by root on 2016/02/17.
 */
public abstract class ZohoServiceCommand<T extends EntityInterface> extends Command implements ZohoCommandInterface {

    ZohoApiService mZohoApiService;

    @Override
    public <T> void add(T t, final ZohoCallCompletionListener listener) {
        mZohoApiService = ZohoApi.getInstance().getPostService();

        EntityInterface _entity = (EntityInterface) t;
        String JSONString = _entity.getValueAsString();

        Call<String> call = mZohoApiService.create(_entity.DBKey(), Constants.ZOHO.API,
                Constants.ZOHO.VERSION, Constants.ZOHO.AUTHTOKEN, Constants.ZOHO.ORGANIZATION_ID,
                JSONString);

        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                listener.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                listener.onError();

            }

        });

    }



    public <T> void get(T t, final ZohoCallCompletionListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE);

    }

    @Override
    public <T> void update(T t, final ZohoCallCompletionListener listener) {

    }

    @Override
    public <T> void remove(T t, final ZohoCallCompletionListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        remove(_entity.DBKey(), _entity.id(), listener);

    }

    @Override
    public void remove(String key, String id, final ZohoCallCompletionListener listener) {

        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE);


    }

    @Override
    public ZohoApiService getApiService(){

        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE);
        return mZohoApiService;

    }

    @Override
    public Class getServiceClass() {
        return ZohoService.class;
    }


}
