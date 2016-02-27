package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.controller.ZohoApi;
import com.feerlaroc.zohos.response.PreparedObservable;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class ZohoEntityArray {

    List<String> mSnapshots;
    OnChangedListener mListener;
    Subscription subscription;

    ZohoApiService mService = ZohoApi.getInstance().get();

    public ZohoEntityArray(final String key) {
        mSnapshots = new ArrayList<>();

        Observable<Object> observable = (Observable<Object>)
                new PreparedObservable().getPreparedObservable(mService.get(key, "api", "v3", "26977862edbdfe8dc4b6d1c3f0d545a6", "163411778"),
                        Object.class, true, true);

        subscription = observable.subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {
                String x = "";
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Object response) {

                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<Object> adapter = moshi.adapter(Object.class);
                List<Object> result = (List<Object>) ((Map<String, Object>) response).get(key);

                Iterator<Object> iterator = result.iterator();
                while (iterator.hasNext()) {
                    String str = adapter.toJson(iterator.next());
                    mSnapshots.add(str);
                }
                notifyChangedListeners();
            }
        });
    }

    public int getArraySize(){
        return mSnapshots.size();
    }

    public String getItem(int index) {
        return mSnapshots.get(index);
    }

    public void setOnChangedListener(OnChangedListener listener) {
        mListener = listener;
    }

    protected void notifyChangedListeners() {
        if (mListener != null) {
            mListener.onChanged();
        }
    }

    public interface OnChangedListener {
        void onChanged();
    }

}

