package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.controller.ZohoApi;
import com.feerlaroc.zohos.response.PreparedObservable;
import com.feerlaroc.zohos.schema.callback.InvoiceService;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;
import com.feerlaroc.zohos.schema.helper.Constants;
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

    List<Map<String, Object>> mSnapshots = new ArrayList<>();
    OnChangedListener mListener;

    Observable<Object> mObservable;
    Subscription mSubscription;
    ZohoApiService mService;

    String mKey;


    public ZohoEntityArray(final String key, final String id){

        InvoiceService mInvoiceService = ZohoApi.getInstance().getInvoiceService();

        mKey = key;

        mObservable = (Observable<Object>)
                new PreparedObservable().getPreparedObservable(mInvoiceService.get(
                        key, Constants.ZOHO.API, Constants.ZOHO.VERSION, id,
                        Constants.ZOHO.AUTHTOKEN, Constants.ZOHO.ORGANIZATION_ID),
                        Object.class, true, true);

        setSubscription();


    }

    public ZohoEntityArray(final String key) {

        mService = ZohoApi.getInstance().get();
        mKey = key;

        mObservable = (Observable<Object>)
            new PreparedObservable().getPreparedObservable(mService.get(
                    key, Constants.ZOHO.API, Constants.ZOHO.VERSION,
                    Constants.ZOHO.AUTHTOKEN, Constants.ZOHO.ORGANIZATION_ID),
                    Object.class, true, true);

        setSubscription();

    }

    void setSubscription(){
        mSubscription = mObservable.subscribe(new Observer<Object>() {
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
                List<Map<String, Object>> result
                        = (List<Map<String, Object>>) ((Map<String, Object>) response).get(mKey);

                Iterator<Map<String, Object>> iterator = result.iterator();
                while (iterator.hasNext()) {
                    //String str = adapter.toJson(iterator.next());
                    mSnapshots.add(iterator.next());
                }
                notifyChangedListeners();
            }
        });
    }

    public int getArraySize(){
        return mSnapshots.size();
    }

    public Map<String, Object> getItem(int index) {
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

