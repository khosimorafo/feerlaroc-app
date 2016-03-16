package com.feerlaroc.invoices.quiry;

import com.feerlaroc.invoices.adapters.ZohoEntityArray;
import com.feerlaroc.zohos.schema.helper.Constants;
import com.feerlaroc.zohos.schema.types.CustomerID;
import com.feerlaroc.zohos.schema.types.InvoiceID;

import org.feerlaroc.utils.json.FeerlarocJsonQuiries;

/**
 * Created by root on 2016/03/03.
 */
public class CustomerTransactionsArray extends ZohoEntityArray {

    public CustomerTransactionsArray(String key){

        super(key);

    }

    public CustomerTransactionsArray(String key, String customer_id) {
        super(key, customer_id);
    }

    public CustomerID getCustomerID(InvoiceID invoiceID){

        CustomerID customer_id = null;

        for(int i = 0; i < getArraySize(); i++){

            String str_inv = FeerlarocJsonQuiries
                    .getCustomString(getItem(i), Constants.ZOHO.INVOICE_ID).getValue().toString();

            InvoiceID inv_id = new InvoiceID (String.valueOf(str_inv));

            if(invoiceID.compare(inv_id)) {

                customer_id = new CustomerID( FeerlarocJsonQuiries
                        .getCustomString(getItem(i), Constants.ZOHO.CUSTOMER_ID).getValue());

                return customer_id;

            }

        }

        return customer_id;
    }

}
