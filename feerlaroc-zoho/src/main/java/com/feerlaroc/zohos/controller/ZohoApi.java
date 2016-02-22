package com.feerlaroc.zohos.controller;

import com.feerlaroc.core.service.NetworkService;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;
import com.feerlaroc.zohos.schema.helper.Constants;

/**
 * Created by root on 2016/02/18.
 */
public class ZohoApi {

    ZohoApiService mZohoApiService;


    private static final ZohoApi holder = new ZohoApi();
    public static ZohoApi getInstance() {return holder;}

    public ZohoApi(){

        mZohoApiService = new NetworkService(Constants.HTTP.BASE_URL).get().create(ZohoApiService.class);

    }

    public ZohoApiService get() {

        return mZohoApiService;

    }

}
