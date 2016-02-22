package com.feerlaroc.zohos.schema.callback;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.zohos.schema.pojo.Invoice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 2016/02/18.
 */
public interface InvoiceService {

    @GET("/invoices")
    Call<List<Invoice>> getInvoices();

}
