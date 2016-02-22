package com.feerlaroc.firebase.command;

import com.feerlaroc.core.entity.EntityInterface;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class AddFirebaseEntityCommand<T extends EntityInterface> extends FirebaseServiceCommand {

    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        notifyListeners();
    }

    @Override
    public <T> void execute(T entity) {
        add(entity);
    }

}
