package com.feerlaroc.firebase.command;

import com.feerlaroc.core.entity.EntityInterface;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * Created by root on 2016/02/13.
 */
public class UpdateFirebaseEntityCommand<T extends EntityInterface> extends FirebaseServiceCommand {

    @Override
    public <T> void execute(T entity) {
        //remove(entity);
    }
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {

    }
}
