package org.feerlaroc.force.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2016/03/17.
 */
public abstract class ForceValueObject {

    private Map<String, Object> mMap = new HashMap<>();

    public void remove(String key){
        mMap.remove(key);
    }

    public void add(String key, Object value){

        mMap.put(key,value);

    }

    public Object getItem(String key){
        return mMap.get(key);
    }

    public int getItemCount(){
        return  mMap.size();
    }

    public Map<String, Object> getForceObjectValue(){

        return mMap;

    }

}
