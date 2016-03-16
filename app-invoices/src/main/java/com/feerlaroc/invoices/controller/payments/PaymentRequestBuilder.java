package com.feerlaroc.invoices.controller.payments;

import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.quiry.CustomerTransactionsArray;
import com.feerlaroc.zohos.model.CustomerPaymentRequest;
import com.feerlaroc.zohos.schema.helper.Constants;
import com.feerlaroc.zohos.schema.types.AppliedAmount;
import com.feerlaroc.zohos.schema.types.CustomerID;
import com.feerlaroc.zohos.schema.types.InvoiceID;

/**
 * Created by root on 2016/03/14.
 */
public class PaymentRequestBuilder {

    PaymentRequestWrapper mPaymentWrapper;
    CustomerID mCustomerID;
    CustomerPaymentRequest mCustomerPaymentRequest;


    public PaymentRequestBuilder(CustomerPaymentRequest paymentRequest){

        mCustomerPaymentRequest = paymentRequest;

    }

    public void  build(InvoiceID invoiceID, AppliedAmount appliedAmount){

        mCustomerID = getCustomerID(invoiceID);

        mPaymentWrapper = new PaymentRequestWrapper(mCustomerID);

        mPaymentWrapper.addInvoice(generateInvoice(invoiceID, appliedAmount));

        mCustomerPaymentRequest.addProperty(Constants.ZOHO.CUSTOMER_ID, mCustomerID.getValue());
        mCustomerPaymentRequest.add(Constants.ZOHO.INVOICES, mPaymentWrapper.mInvoices);

    }

    private CustomerID getCustomerID(InvoiceID invoiceID) {

        CustomerTransactionsArray customerTransactionsArray
                = (CustomerTransactionsArray) AppDataHolder.getInstance().getEntity(CustomerTransactionsArray.class);


        return customerTransactionsArray.getCustomerID(invoiceID);
    }

    private PaymentRequestWrapper.InvoiceToPayWrapper generateInvoice(InvoiceID invoiceID, AppliedAmount appliedAmount){

        return new PaymentRequestWrapper.InvoiceToPayWrapper(invoiceID, appliedAmount);

    }



}
