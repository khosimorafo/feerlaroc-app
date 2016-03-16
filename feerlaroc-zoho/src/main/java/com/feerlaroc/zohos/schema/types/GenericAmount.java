package com.feerlaroc.zohos.schema.types;

import com.feerlaroc.zohos.schema.helper.Constants;

import org.feerlaroc.types.Amount;

/**
 * Created by root on 2016/03/14.
 */
public class GenericAmount extends Amount implements ZohoProperty {

    public GenericAmount(Double value) {
        super(value);
    }

    @Override
    public String getZohoName() {
        return Constants.ZOHO.GENERIC_AMOUNT;
    }

}