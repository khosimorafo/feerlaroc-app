package com.feerlaroc.invoices.controller.payments;

import com.feerlaroc.core.model.JsonAble;
import com.feerlaroc.zohos.schema.helper.Constants;
import com.feerlaroc.zohos.schema.types.AppliedAmount;
import com.feerlaroc.zohos.schema.types.CustomerID;
import com.feerlaroc.zohos.schema.types.GenericAmount;
import com.feerlaroc.zohos.schema.types.InvoiceID;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2016/03/14.
 */
public class PaymentRequestWrapper {


    private final CustomerID mCustomerId;
    List<InvoiceToPayWrapper> mInvoices = new ArrayList<>();


    public PaymentRequestWrapper(CustomerID customer_id) {
        mCustomerId = customer_id;
    }

    public void addInvoice(InvoiceToPayWrapper invoice){
        mInvoices.add(invoice);
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

    public static class InvoiceToPayWrapper implements JsonAble{

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

        public JsonObject getJSONObject() {
            JsonObject obj = new JsonObject();
            try {
                obj.addProperty(Constants.ZOHO.INVOICE_ID, mInvoiceId.getValue());
                obj.addProperty(Constants.ZOHO.APPLIED_AMOUNT, mInvoiceAmount.getValue());
            } catch (Exception e) {
//                trace("DefaultListItem.toString JSONException: "+e.getMessage());
            }
            return obj;
        }

    }

}
