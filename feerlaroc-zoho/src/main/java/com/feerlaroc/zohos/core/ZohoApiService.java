package com.feerlaroc.zohos.core;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.zohos.response.FriendResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 2016/02/18.
 */
public interface ZohoApiService{

    @GET("/{api}/{version}/{key}")
    Observable<Object> get(@Path("key") String key,
                           @Path("api") String api,
                           @Path("version") String version,
                           @Query("authtoken") String authtoken,
                           @Query("organization_id") String organization_id);

    @GET("/{api}/{version}/{key}/{id}")
    Call<Object> get(@Path("key") String key,
                      @Path("api") String api,
                      @Path("version") String version,
                      @Path("id") String id,
                      @Query("authtoken") String authtoken,
                      @Query("organization_id") String organization_id);

    @GET("/{api}/{version}/{key}/{id}/{object}")
    Call<Object> getInvoices(@Path("key") String key,
                             @Path("api") String api,
                             @Path("version") String version,
                             @Path("id") String id,
                             @Path("object") String object,
                             @Query("authtoken") String authtoken,
                             @Query("organization_id") String organization_id);


    @POST("/{api}/{version}/{key}")
    Call<String> create(@Path("key") String key,
                        @Path("api") String api,
                        @Path("version") String version,
                        @Query("authtoken") String authtoken,
                        @Query("organization_id") String organization_id,
                        @Query("JSONString") String JSONString);


    @DELETE("/{key}/{id}")
    Call<EntityInterface> delete(@Path("key") String key, @Path("id") String id);

    @GET("FriendLocations.json")//real endpoint
    Call<FriendResponse> getFriends();


    @GET("FriendLocations.json") //real endpoint
    Observable<Object> getFriendsObservable();


}
