package com.feerlaroc.zohos.command;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.zohos.schema.callback.ZohoApiListener;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;
import com.feerlaroc.zohos.service.ZohoService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by root on 2016/02/17.
 */
public abstract class ZohoServiceCommand<T extends EntityInterface> extends Command implements ZohoCommandInterface {

    ZohoApiService mZohoApiService;

    @Override
    public <T> void add(T t, ZohoApiListener listener) {

        EntityInterface _entity = (EntityInterface) t;
        mZohoApiService = (ZohoApiService) getArgument(ZohoService.ZOHO_SERVICE); //ZohoApi.getInstance().get();

        Observable<List<EntityInterface>> observable = mZohoApiService.get(_entity.DBKey());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<EntityInterface>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<EntityInterface> dessertItemCollectionDao) {

                    }
                });

    }

    @Override
    public <T> void update(T t, ZohoApiListener listener) {

    }

    @Override
    public <T> void remove(T t, ZohoApiListener listener) {

    }

    @Override
    public Class getServiceClass() {
        return null;
    }

}
