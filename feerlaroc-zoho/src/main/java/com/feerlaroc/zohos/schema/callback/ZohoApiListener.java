package com.feerlaroc.zohos.schema.callback;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.FrameworkListener;

import java.util.List;

/**
 * Created by root on 2016/02/18.
 */
public interface ZohoApiListener extends FrameworkListener {

    void onSuccess();

    void onFailure();

}
