package com.feerlaroc.firebase.command;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.firebase.core.FirebaseReference;
import com.feerlaroc.firebase.service.FirebaseService;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public abstract class FirebaseServiceCommand extends Command implements Firebase.CompletionListener {


    protected FirebaseReference firebaseReference;


    protected <T> void add(T t){

        firebaseReference = (FirebaseReference) getArgument(FirebaseService.FIREBASE_BASE_REFERENCE);
        EntityInterface _entity = (EntityInterface) t;
        firebaseReference.get(_entity.DBKey()).child(_entity.id()).setValue(_entity, this);

    }

    protected <T> void remove(T t) {

        EntityInterface _entity = (EntityInterface) t;
        remove(_entity.id(), _entity.DBKey());

    }

    protected <T> void remove(String id, String db_key) {

        firebaseReference = (FirebaseReference) getArgument(FirebaseService.FIREBASE_BASE_REFERENCE);
        firebaseReference.get(db_key).child(id).removeValue(this);

    }


    @Override
    public <T> void execute(T entity) {

    }

    @Override
    public <T> void execute(T entity, FrameworkCompletionListener listener) {

    }

    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {

    }

    @Override
    public Class getServiceClass() {
        return (FirebaseService.class);
    }

}
