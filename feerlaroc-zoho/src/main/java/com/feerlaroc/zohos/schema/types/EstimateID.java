package com.feerlaroc.zohos.schema.types;

import com.feerlaroc.core.schema.types.CustomString;
import com.feerlaroc.zohos.schema.helper.Constants;

/**
 * Created by root on 2016/03/14.
 */
public class EstimateID extends CustomString implements ZohoProperty {

    public EstimateID(String value) {
        super(value);
    }

    @Override
    public String getZohoName() {
        return Constants.ZOHO.ESTIMATE_ID;
    }

}
