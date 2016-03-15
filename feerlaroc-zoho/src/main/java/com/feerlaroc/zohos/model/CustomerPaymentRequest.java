package com.feerlaroc.zohos.model;

import android.content.Context;

import com.feerlaroc.zohos.schema.helper.Constants;

/**
 * Created by root on 2016/03/14.
 */
public class CustomerPaymentRequest extends ZohoRequest {

    private static CustomerPaymentRequest sInstance;

    public static CustomerPaymentRequest getInstance(Context context, String file) {
        if (sInstance == null) {
            //Always pass in the Application Context
            sInstance = new CustomerPaymentRequest(context.getApplicationContext(), file);
        }

        return sInstance;
    }

    private CustomerPaymentRequest(Context context, String file) {
        super(context,file);
    }


    public void prepareRequestObject(){

    }



    @Override
    public String id() {
        return null;
    }

    @Override
    public String DBKey() {
        return Constants.ZOHO.CUSTOMERPAYMENTS;
    }

    @Override
    public <T> T getValue(Class<T> clazz) {
        return null;
    }
}
