package com.feerlaroc.zohos.adapter;

import com.feerlaroc.core.entity.EntityInterface;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 2016/02/23.
 */
public class ZohoEntityArray {

    List<EntityInterface> mEntities;

    public ZohoEntityArray(Observable<List<EntityInterface>> observable) {

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
                    public void onNext(List<EntityInterface> entities) {
                        mEntities = entities;
                    }
                });
    }

    public int getArraySize(){
        return mEntities.size();
    }

    public interface OnChangedListener {
        enum EventType { Added, Changed, Removed, Moved }
        void onChanged(EventType type, int index, int oldIndex);
    }

}
