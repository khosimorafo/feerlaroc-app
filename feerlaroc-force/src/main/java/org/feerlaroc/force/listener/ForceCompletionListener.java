package org.feerlaroc.force.listener;

import com.feerlaroc.core.listeners.FrameworkCompletionListener;

import org.json.JSONObject;

/**
 * Created by root on 2016/03/17.
 */
public interface ForceCompletionListener extends FrameworkCompletionListener{

    void onSuccess(JSONObject result);
    void onError();

}
