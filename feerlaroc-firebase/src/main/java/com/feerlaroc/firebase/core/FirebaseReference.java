package com.feerlaroc.firebase.core;

import com.firebase.client.Firebase;

/**
 * Created by root on 2016/01/27.
 */
public class FirebaseReference {

    static Firebase ref = null;

    private static final FirebaseReference holder = new FirebaseReference();

    public static FirebaseReference getInstance(String url) {

        ref = new Firebase(url);
        return holder;

    }

    public Firebase get(String... children){

        Firebase _ref = ref;
        for(String str_child : children){
            _ref = _ref.child(str_child);
        }
        return _ref;

    }

}
