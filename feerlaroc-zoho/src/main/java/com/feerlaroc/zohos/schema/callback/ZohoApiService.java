package com.feerlaroc.zohos.schema.callback;

import com.feerlaroc.core.entity.EntityInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by root on 2016/02/18.
 */
public interface ZohoApiService {

    @GET("/{key}")
    Observable<List<EntityInterface>> get(@Path("key") String key);

    @GET("/{key}/{id}")
    Call<EntityInterface> get(@Path("key") String key, @Path("id") String id);

    @POST("/{key}")
    Call<EntityInterface> create(@Path("key") String key, @Body EntityInterface entity);

    @DELETE("/{key}/{id}")
    Call<EntityInterface> delete(@Path("key") String key, @Path("id") String id);

}
