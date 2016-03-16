package com.feerlaroc.zohos.service;

import com.feerlaroc.core.Command;
import com.feerlaroc.core.service.CoreServices;
import com.feerlaroc.core.service.SingletonService;
import com.feerlaroc.zohos.controller.ZohoApi;
import com.feerlaroc.zohos.core.ZohoApiService;

import java.util.Properties;

/**
 * Created by root on 2016/02/18.
 */
public class ZohoService implements SingletonService {

    public static final String ZOHO_SERVICE      = "zohoService";


    @Override
    public void injectArguments(Command command) {

        if(command != null) {
            command.setArgument( ZOHO_SERVICE, getZohoApiService() );
        }

    }

    private ZohoApiService getZohoApiService() {

        return ZohoApi.getInstance().get();

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
        return null;
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
