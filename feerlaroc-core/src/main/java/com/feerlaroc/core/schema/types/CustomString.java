package com.feerlaroc.core.schema.types;

/**
 * Created by root on 2016/03/14.
 *
 * Deligate class for strings
 */
public abstract class CustomString {

    String mValue;

    public CustomString(String value){

        mValue = value;

    }

    public String getValue() {
        return mValue;
    }

}
