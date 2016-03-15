package com.feerlaroc.invoices.quiry;

import com.feerlaroc.invoices.adapters.ZohoEntityArray;
import com.feerlaroc.zohos.schema.types.CustomerID;
import com.feerlaroc.zohos.schema.types.InvoiceID;

/**
 * Created by root on 2016/03/03.
 */
public class CustomerTransactionsArray extends ZohoEntityArray {

    public CustomerTransactionsArray(String key, String customer_id) {
        super(key, customer_id);
    }

    public CustomerID getCustomerID(InvoiceID invoiceID){

        return null;

    }

}
