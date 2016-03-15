package com.feerlaroc.zohos.listener;

import com.feerlaroc.core.listeners.FrameworkCompletionListener;

/**
 * Created by root on 2016/03/14.
 */
public interface ZohoCallCompletionListener extends FrameworkCompletionListener {

    void onSuccess(Object o);
    void onError();

}
