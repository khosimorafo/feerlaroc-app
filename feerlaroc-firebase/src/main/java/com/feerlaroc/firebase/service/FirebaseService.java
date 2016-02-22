package com.feerlaroc.firebase.service;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.service.CoreServices;
import com.feerlaroc.core.service.SingletonService;
import com.feerlaroc.firebase.core.FirebaseReference;
import com.feerlaroc.core.Services;

import java.util.Properties;

/**
 * Created by root on 2016/02/14.
 */
public class FirebaseService implements SingletonService {

    public static final String FIREBASE_BASE_REFERENCE      = "firebaseBaseReference";
    public static final String DRIVERS                      = "drivers";
    public static final String CARS                         = "cars";
    public static final String RECORDS                      = "records";




    @Override
    public void injectArguments(Command command) {

        if(command != null) {

            command.setArgument(FIREBASE_BASE_REFERENCE, getFirebaseBaseReference() );

        }

    }

    public static FirebaseReference getFirebaseBaseReference() {
        return FirebaseReference.getInstance(Services.getBaseConfiguration().getProperty(Services.FIREBASE_URL));
    }

    @Override
    public void initialize(CoreServices services, Properties config) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

    }


    @Override
    public void shutdown() {

    }

    @Override
    public void initialized() {

    }

    @Override
    public String getName() {
        return FirebaseService.class.getSimpleName();
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isVital() {
        return true;
    }

}
