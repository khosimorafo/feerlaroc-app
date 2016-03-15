package com.feerlaroc.zohos.core;

import com.feerlaroc.core.entity.EntityInterface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 2016/03/10.
 */
public interface PaymentService {


    @GET("/{api}/{version}/{key}/{id}")
    Call<Object> get(@Path("key") String key,
                     @Path("api") String api,
                     @Path("version") String version,
                     @Path("id") String id,
                     @Query("authtoken") String authtoken,
                     @Query("organization_id") String organization_id);

    @POST("/{api}/{version}/{key}")
    Call<Object> create(@Path("key") String key,
                        @Path("api") String api,
                        @Path("version") String version,
                        @Query("authtoken") String authtoken,
                        @Query("organization_id") String organization_id,
                        @Body EntityInterface entity);


}
