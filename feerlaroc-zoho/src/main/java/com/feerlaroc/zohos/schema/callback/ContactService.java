package com.feerlaroc.zohos.schema.callback;

import com.feerlaroc.zohos.schema.pojo.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by root on 2016/02/18.
 */
public interface ContactService {

    @GET("/contacts")
    Call<List<Contact>> getAll();

    @GET("/contacts/{id}")
    Call<Contact> get(@Path("id") int id);

    @POST("/contacts")
    Call<Contact> create(@Body Contact contant);

    @DELETE("/contacts/{id}")
    Call<Contact> delete(@Path("id") int id);

}
