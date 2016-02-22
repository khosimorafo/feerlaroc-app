/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.moonmonkeylabs.quantum;

import android.app.Application;

import com.feerlaroc.core.Services;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.service.Service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import co.moonmonkeylabs.quantum.common.dagger.ObjectGraphService;
import dagger.ObjectGraph;
import dalvik.system.DexFile;
import mortar.MortarScope;

public class FlowMortarExampleApplication extends Application {

    private MortarScope rootScope;

    Set<String> serviceClasses  = new LinkedHashSet<>();
    Set<Class> entityClasses   = new LinkedHashSet<>();


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

    @Override
    public void onCreate()
    {

        super.onCreate();
        // Initialize the singletons so their instances
        // are bound to the application process.
        initSingletons();

    }

    protected void initSingletons()
    {
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
                if(s.startsWith("com.feerlaroc")){
                    if(Service.class.isAssignableFrom(Class.forName(s))){
                        serviceClasses.add(s);
                    }

                }
                if(s.startsWith("co.moonmonkeylabs.flowmortarexampleapp.model")) {
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
