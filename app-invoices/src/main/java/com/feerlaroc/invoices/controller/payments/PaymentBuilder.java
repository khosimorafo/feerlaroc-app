package com.feerlaroc.invoices.controller.payments;

import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.quiry.CustomerTransactionsArray;
import com.feerlaroc.zohos.schema.types.AppliedAmount;
import com.feerlaroc.zohos.schema.types.CustomerID;
import com.feerlaroc.zohos.schema.types.InvoiceID;

/**
 * Created by root on 2016/03/14.
 */
public class PaymentBuilder {


    PaymentWrapper mPaymentWrapper;
    CustomerID mCustomerID;

    public PaymentBuilder (InvoiceID invoiceID, AppliedAmount appliedAmount){

        mCustomerID = getCustomerID(invoiceID);

        mPaymentWrapper = new PaymentWrapper(mCustomerID);
    }

    private CustomerID getCustomerID(InvoiceID invoiceID) {

        CustomerTransactionsArray customerTransactionsArray
                = (CustomerTransactionsArray) AppDataHolder.getInstance().getEntity(CustomerTransactionsArray.class);


        return customerTransactionsArray.getCustomerID(invoiceID);
    }

    private PaymentWrapper.InvoiceToPayWrapper generateInvoice(InvoiceID invoiceID, AppliedAmount appliedAmount){

        return new PaymentWrapper.InvoiceToPayWrapper(invoiceID, appliedAmount);

    }



}
