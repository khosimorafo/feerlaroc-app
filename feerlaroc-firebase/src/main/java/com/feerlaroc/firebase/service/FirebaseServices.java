package com.feerlaroc.firebase.service;

import com.feerlaroc.core.service.CoreServices;
import com.feerlaroc.core.service.InitializationCallback;
import com.feerlaroc.core.service.Service;
import com.feerlaroc.firebase.core.FirebaseReference;

/**
 *
 */
public interface FirebaseServices extends CoreServices {


    FirebaseReference getFirebaseBaseReference();


}
