package com.feerlaroc.invoices.model;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 2016/03/15.
 */
public class TransactionSnapshot extends DataSnapshot {

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @NonNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        return null;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Object put(String key, Object value) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ?> map) {

    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @NonNull
    @Override
    public Collection<Object> values() {
        return null;
    }
}
