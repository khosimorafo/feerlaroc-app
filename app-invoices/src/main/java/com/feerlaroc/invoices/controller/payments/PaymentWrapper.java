package com.feerlaroc.invoices.controller.payments;

import com.feerlaroc.zohos.schema.types.AppliedAmount;
import com.feerlaroc.zohos.schema.types.CustomerID;
import com.feerlaroc.zohos.schema.types.GenericAmount;
import com.feerlaroc.zohos.schema.types.InvoiceID;

import java.util.List;

/**
 * Created by root on 2016/03/14.
 */
public class PaymentWrapper {


    private final CustomerID mCustomerId;
    List<InvoiceToPayWrapper> mInvoices;


    public PaymentWrapper(CustomerID customer_id) {
        mCustomerId = customer_id;
    }

    public void addInvoices(List<InvoiceToPayWrapper> invoices){
        mInvoices = invoices;
    }

    public GenericAmount getTotalPaymentAmount(){

        GenericAmount amount = new GenericAmount(0.00);

        for (InvoiceToPayWrapper invoice : mInvoices) {
            amount.increment(invoice.mInvoiceAmount);
        }

        return amount;
    }

    public static class InvoiceToPayWrapper{

        final InvoiceID mInvoiceId;
        final AppliedAmount mInvoiceAmount;

        static InvoiceToPayWrapper mInvoiceToPayWrapper;

        public static InvoiceToPayWrapper getInstance(InvoiceID invoiceID, AppliedAmount appliedAmount){
            mInvoiceToPayWrapper = new InvoiceToPayWrapper(invoiceID, appliedAmount);
            return mInvoiceToPayWrapper;
        }

        public InvoiceToPayWrapper(InvoiceID invoice_id, AppliedAmount invoice_amount){
            mInvoiceId = invoice_id;
            mInvoiceAmount = invoice_amount;
        }

    }

}
