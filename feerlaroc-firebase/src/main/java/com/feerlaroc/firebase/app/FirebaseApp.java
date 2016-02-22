package com.feerlaroc.firebase.app;

import android.support.v7.widget.RecyclerView;

import com.feerlaroc.firebase.command.FirebaseServiceCommand;

/**
 * Created by root on 2016/02/17.
 */
public interface FirebaseApp {

    public FirebaseServiceCommand adapter(RecyclerView.Adapter adapter);

}
