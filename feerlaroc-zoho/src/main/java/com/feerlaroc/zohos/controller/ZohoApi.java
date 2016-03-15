package com.feerlaroc.zohos.controller;

import com.feerlaroc.core.service.NetworkService;
import com.feerlaroc.zohos.core.InvoiceService;
import com.feerlaroc.zohos.core.ZohoApiService;
import com.feerlaroc.zohos.schema.helper.Constants;

/**
 * Created by root on 2016/02/18.
 */
public class ZohoApi {

    private static final ZohoApi holder = new ZohoApi();
    public static ZohoApi getInstance() {return holder;}

    public ZohoApi(){

    }

    public ZohoApiService get() {

        return new NetworkService(Constants.ZOHO.BASE_URL).get().create(ZohoApiService.class);

    }

    public InvoiceService getInvoiceService(){
        return  new NetworkService(Constants.ZOHO.BASE_URL).get().create(InvoiceService.class);
    }

}
