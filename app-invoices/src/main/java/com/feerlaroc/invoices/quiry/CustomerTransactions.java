package com.feerlaroc.invoices.quiry;

import com.feerlaroc.invoices.adapters.ZohoEntityArray;
import com.feerlaroc.zohos.schema.helper.Constants;

/**
 * Created by root on 2016/03/03.
 */
public class CustomerTransactions implements ZohoEntityArray.OnDataChangedListener {

    CustomerTransactionsArray mDataArray;
    String mInvoicesKey = Constants.ZOHO.INVOICES;


    public CustomerTransactions(String customer_id) {

        mDataArray = new CustomerTransactionsArray(mInvoicesKey, customer_id);

    }

    public CustomerTransactionsArray getDataArray() {
        return mDataArray;
    }

    @Override
    public void onChanged() {

    }
}
