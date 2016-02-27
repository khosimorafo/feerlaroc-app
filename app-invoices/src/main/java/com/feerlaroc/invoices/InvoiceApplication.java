package com.feerlaroc.invoices;

import android.app.Application;

import com.feerlaroc.core.Services;
import com.feerlaroc.core.app.App;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.service.Service;
import com.feerlaroc.invoices.application.InvoiceApp;
import com.feerlaroc.invoices.common.dagger.ObjectGraphService;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import dagger.ObjectGraph;
import dalvik.system.DexFile;
import mortar.MortarScope;

/**
 * Created by root on 2016/02/22.
 */
public class InvoiceApplication extends Application {

    static InvoiceApplication singleton;

    private MortarScope rootScope;

    Set<String> serviceClasses  = new LinkedHashSet<>();
    Set<Class> entityClasses = new LinkedHashSet<>();

    ZohoApiService mService;


    @Override public Object getSystemService(String name) {
        if (rootScope == null) {
            rootScope = MortarScope.buildRootScope()
                    .withService(
                            ObjectGraphService.SERVICE_NAME,
                            ObjectGraph.create(new ApplicationModule(this)))
                    .build("Root");
        }

        if (rootScope.hasService(name)) return rootScope.getService(name);

        return super.getSystemService(name);
    }

    public static InvoiceApplication getInstance(){
        return singleton;
    }

    @Override
    public void onCreate()
    {

        super.onCreate();
        // Initialize the singletons so their instances
        // are bound to the application process.
        initSingletons();
        App app = new InvoiceApp();
        mService = app.getService();

    }

    public ZohoApiService getAPIService(){
        return mService;
    }

    protected void initSingletons()
    {
        singleton = this;
        setApplicationClasses();
        // Initialize the instance of Services
        Services.initialize(serviceClasses, entityClasses);
    }

    private void setApplicationClasses() {

        try {

            String codePath = getApplicationContext().getPackageCodePath();
            String packageName = getPackageName();
            DexFile df = new DexFile(codePath);
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements();) {
                String s = iter.nextElement();
                if(s.startsWith("com.feerlaroc.zohos")){
                    if(Service.class.isAssignableFrom(Class.forName(s))){
                        serviceClasses.add(s);
                    }

                }
                if(s.startsWith("com.feerlaroc.model")) {
                    if(EntityInterface.class.isAssignableFrom(Class.forName(s))){
                        entityClasses.add(Class.forName(s));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
