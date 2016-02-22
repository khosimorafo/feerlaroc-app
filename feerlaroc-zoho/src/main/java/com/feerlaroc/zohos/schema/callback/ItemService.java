package com.feerlaroc.zohos.schema.callback;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.zohos.schema.pojo.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 2016/02/18.
 */
public interface ItemService {

    @GET("/items")
    Call<List<Item>> getItems();

}
