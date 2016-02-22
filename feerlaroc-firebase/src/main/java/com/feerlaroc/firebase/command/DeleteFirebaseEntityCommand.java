package com.feerlaroc.firebase.command;

import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.error.FrameworkException;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * Created by root on 2016/02/13.
 */
public class DeleteFirebaseEntityCommand<T extends EntityInterface> extends FirebaseServiceCommand {
    
    public void execute(String id, String db_key) throws FrameworkException {
        remove(id,db_key);
    }

    @Override
    public <T> void execute(T entity) {
        remove(entity);
    }

    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {

    }


}
