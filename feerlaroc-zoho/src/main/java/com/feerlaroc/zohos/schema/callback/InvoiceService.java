package com.feerlaroc.zohos.schema.callback;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 2016/02/18.
 */
public interface InvoiceService {

    @GET("/{api}/{version}/{key}")
    Observable<Object> get(@Path("key") String key,
                           @Path("api") String api,
                           @Path("version") String version,
                           @Query("customer_id") String customer_id,
                           @Query("authtoken") String authtoken,
                           @Query("organization_id") String organization_id);


}
