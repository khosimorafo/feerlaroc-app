package com.feerlaroc.core.controller;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by root on 2016/02/18.
 */
public class RestManager {

    Retrofit retrofit;

    public RestManager(String baseURL){

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Retrofit get() {
        return retrofit;
    }
}
