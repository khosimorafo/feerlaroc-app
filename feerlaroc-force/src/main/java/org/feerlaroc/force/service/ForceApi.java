package org.feerlaroc.force.service;

import com.salesforce.androidsdk.rest.RestClient;

/**
 * Created by root on 2016/03/17.
 */
public class ForceApi {

    RestClient mRestClient;

    private static final ForceApi holder = new ForceApi();

    public static ForceApi getInstance() {return holder;}

    public ForceApi(){}

    public RestClient get() {

        return mRestClient;

    }

    public void set(RestClient client) {
        mRestClient = client;
    }

}
