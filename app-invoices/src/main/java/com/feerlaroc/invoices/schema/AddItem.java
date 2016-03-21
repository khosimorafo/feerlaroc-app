package com.feerlaroc.invoices.schema;

import org.feerlaroc.force.model.ForceRequestObject;
import org.feerlaroc.force.schema.helper.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2016/03/17.
 */
public class AddItem extends ForceRequestObject {

    @Override
    public Map<String, Object> getObjectMap() {

        mObjectMap = new HashMap<>();
        mObjectMap.put(DBKey(), getForceObjectValue());
        return mObjectMap;

    }

    @Override
    public String getSQOL() {
        return null;
    }

    @Override
    public String getEntityType() {
        return Constants.SALESFORCE.ITEMS;
    }


    @Override
    public String id() {
        return null;
    }

    @Override
    public String DBKey() {
        return Constants.SALESFORCE.ITEM;
    }

    @Override
    public <T> T getValue(Class<T> clazz) {
        return null;
    }

    @Override
    public String getValueAsString() {
        return null;
    }

}
