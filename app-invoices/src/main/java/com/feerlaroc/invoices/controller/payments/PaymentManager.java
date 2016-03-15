package com.feerlaroc.invoices.controller.payments;

import com.feerlaroc.zohos.schema.types.CustomerID;
import com.feerlaroc.zohos.schema.types.InvoiceID;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016/03/10.
 */
public class PaymentManager {



    private CustomerID mCustomerID;
    private InvoiceID mInvoiceID;

    public void process(String invoice_id){

        mInvoiceID = new InvoiceID(invoice_id);
        mCustomerID = new CustomerID("");

        PaymentWrapper wrapper = new PaymentWrapper(mCustomerID);
        PaymentWrapper.InvoiceToPayWrapper invoiceToPayWrapper;

        List<PaymentWrapper.InvoiceToPayWrapper> invoices =
                new ArrayList<>();


        invoiceToPayWrapper = PaymentWrapper
                .InvoiceToPayWrapper.getInstance(mInvoiceID, 300.0);
        invoices.add(invoiceToPayWrapper);

        wrapper.addInvoices(invoices);

    }

    private void init() {


    }

}
