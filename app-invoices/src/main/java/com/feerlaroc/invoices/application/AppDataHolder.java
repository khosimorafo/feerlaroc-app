package com.feerlaroc.invoices.application;

import com.feerlaroc.invoices.listeners.OnModelDataChangedListener;

import java.util.HashMap;
import java.util.Map;

public class AppDataHolder<T> {

    Map<String, OnModelDataChangedListener> mListeners = new HashMap<>();
    Map<String, Map<String, Object>> mEntities = new HashMap<>();

    private static final AppDataHolder holder = new AppDataHolder();
    public static AppDataHolder getInstance() {return holder;}

    public Map<String, Object> getEntity(Class<T> clazz) {
        
        for(Map.Entry entry : mEntities.entrySet()){
            String key = (String) entry.getKey();
            if(clazz.getCanonicalName().equals(key)){
                return (Map<String, Object>) entry.getValue();
            }
        }

        return null;
    }

    public void setEntity(Map<String, Object> entityMap, Class<T> clazz) {
        
        String keyToRemove = "";
        for(Map.Entry entry : mEntities.entrySet()){
            String key = (String) entry.getKey();
            if(clazz.getCanonicalName().equals(key)){
                keyToRemove = key;
            }
        }
        mEntities.remove(keyToRemove);
        mEntities.put(clazz.getCanonicalName(), entityMap);
        notifyDataListeners(clazz);

    }

    public void addDataChangedListener(OnModelDataChangedListener listener, Class<T> clazz) {
        this.mListeners.put(clazz.getCanonicalName(), listener);
    }

    public void notifyDataListeners(Class<T> clazz){

        OnModelDataChangedListener listener;

        for(Map.Entry entry : mListeners.entrySet()){
            String key = (String) entry.getKey();
            if(clazz.getCanonicalName().equals(key)){
                listener = (OnModelDataChangedListener) entry.getValue();
                listener.onModelDataChanged(mEntities.get(key));
            }
        }

    }
}
