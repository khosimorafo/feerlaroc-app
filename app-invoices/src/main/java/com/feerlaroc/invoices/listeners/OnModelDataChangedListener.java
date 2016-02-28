package com.feerlaroc.invoices.listeners;


import java.util.Map;

/**
 * Created by root on 2016/02/08.
 */
public interface OnModelDataChangedListener {

    void onModelDataChanged(Map<String, Object> entityMap);

}
