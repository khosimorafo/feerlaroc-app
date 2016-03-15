package com.feerlaroc.zohos.schema.types;

import com.feerlaroc.zohos.schema.helper.Constants;

/**
 * Created by root on 2016/03/14.
 */
public class AppliedAmount extends GenericAmount implements ZohoProperty {

    public AppliedAmount(Double value) {
        super(value);
    }

    @Override
    public String getZohoName() {
        return Constants.ZOHO.APPLIED_AMOUNT;
    }

}
