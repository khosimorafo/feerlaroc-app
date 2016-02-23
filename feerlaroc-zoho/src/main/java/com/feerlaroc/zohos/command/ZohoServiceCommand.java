package com.feerlaroc.zohos.command;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;
import com.feerlaroc.zohos.service.ZohoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;


/**
 * Created by root on 2016/02/17.
 */
public abstract class ZohoServiceCommand<T extends EntityInterface> extends Command implements ZohoCommandInterface {

    ZohoApiService mZohoApiService;

    @Override
    public <T> void add(T t, final FrameworkCompletionListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE); //ZohoApi.getInstance().get();

        Call<EntityInterface> call = mZohoApiService.create(_entity.DBKey(), _entity);
        call.enqueue(new Callback<EntityInterface>() {
            @Override
            public void onResponse(Call<EntityInterface> call, Response<EntityInterface> response) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<EntityInterface> call, Throwable t) {
                listener.onError();
            }
        });

    }



    public <T> void get(T t, final FrameworkCompletionListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE); //ZohoApi.getInstance().get();

        Call<EntityInterface> call = mZohoApiService.get(_entity.DBKey(), _entity.id());
        call.enqueue(new Callback<EntityInterface>() {
            @Override
            public void onResponse(Call<EntityInterface> call, Response<EntityInterface> response) {

            }

            @Override
            public void onFailure(Call<EntityInterface> call, Throwable t) {

            }
        });

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
        mZohoApiService.delete(key, id)
            .enqueue(new Callback<EntityInterface>() {
                @Override
                public void onResponse(Call<EntityInterface> call, Response<EntityInterface> response) {
                    listener.onSuccess();
                }

                @Override
                public void onFailure(Call<EntityInterface> call, Throwable t) {
                    listener.onError();
                }
            });

    }

    @Override
    public <T> Observable getDataListObservable(T t){

        EntityInterface _entity = (EntityInterface) t;
        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE);
        Observable<List<EntityInterface>> observable;

        observable = mZohoApiService.get(_entity.DBKey());

        return observable;
    }

    @Override
    public Class getServiceClass() {
        return null;
    }

}
