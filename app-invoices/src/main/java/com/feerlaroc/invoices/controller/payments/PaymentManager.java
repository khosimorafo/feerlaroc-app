package com.feerlaroc.invoices.controller.payments;

import android.content.Context;

import com.feerlaroc.zohos.model.CustomerPaymentRequest;
import com.feerlaroc.zohos.schema.types.AppliedAmount;
import com.feerlaroc.zohos.schema.types.InvoiceID;

/**
 * Created by root on 2016/03/10.
 */
public class PaymentManager {

    CustomerPaymentRequest mCustomerPaymentRequest;
    private InvoiceID mInvoiceID;
    private AppliedAmount mAppliedAmount;


    public PaymentManager(Context context){

        mCustomerPaymentRequest = CustomerPaymentRequest
                .getInstance(context, "invoice_request.json");

    }


    public void process(InvoiceID invoice_id, Double amount){

        mInvoiceID = invoice_id;
        mAppliedAmount = new AppliedAmount(amount);

        PaymentRequestBuilder builder
                = new PaymentRequestBuilder(mCustomerPaymentRequest);

        builder.build(mInvoiceID, mAppliedAmount);

    }

    private void init() {


    }



    public CustomerPaymentRequest getObject(){

        return mCustomerPaymentRequest;

    }
}
