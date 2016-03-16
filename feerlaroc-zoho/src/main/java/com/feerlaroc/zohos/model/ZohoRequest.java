package com.feerlaroc.zohos.model;

import android.content.Context;

import com.feerlaroc.core.model.JsonAble;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by root on 2016/03/14.
 */
public abstract class ZohoRequest implements ZohoRequestObject {

    /**
     * com.google.gson.JsonObject is marked final.
     *
     * This is class declares @mJsonObject
     * and acts as a wrapper to circumvent that limitation.
     * */
    private JsonObject mJsonObject;


    static Context mContext;
    static String mAssetPath = null;

    public ZohoRequest(Context context, String file){

        mContext = context;
        mAssetPath = file;
        mJsonObject = getTemplate();

    }

    public void remove(String property){
        mJsonObject.remove(property);
    }

    public void add(String property, Object value){

        List<JsonAble> v = (List<JsonAble>) value;

        JsonArray jsonArray = new JsonArray();
        for (int i=0; i < v.size(); i++) {
            jsonArray.add(v.get(i).getJSONObject());
        }

        mJsonObject.add(property, jsonArray);
    }

    public void addProperty(String property, String value){

        mJsonObject.addProperty(property, value);

    }

    public void addProperty(String property, Number value){

        mJsonObject.addProperty(property, value);

    }

    public void addProperty(String property, Boolean value){

        mJsonObject.addProperty(property, value);

    }

    public ZohoElement getElement(String property){

        return (ZohoElement) mJsonObject.get(property);

    }

    private static String getJsonString(){

        String json = null;
        try {

            InputStream is = mContext.getAssets().open(mAssetPath);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public JsonObject getTemplate(){

        return new JsonParser().parse(getJsonString()).getAsJsonObject();

    }

    @Override
    public JsonObject get(){

        return mJsonObject;

    }

    public String getValueAsString(){
        return mJsonObject.toString();
    }

}
