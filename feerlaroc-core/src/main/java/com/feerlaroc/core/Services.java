package com.feerlaroc.core;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.module.JarConfigurationProvider;
import com.feerlaroc.core.schema.ConfigurationProvider;
import com.feerlaroc.core.service.CoreServices;
import com.feerlaroc.core.service.InitializationCallback;
import com.feerlaroc.core.service.RunnableService;
import com.feerlaroc.core.service.Service;
import com.feerlaroc.core.service.SingletonService;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by root on 2016/02/14.
 */
public class Services implements CoreServices {

    private static final Logger logger                                   = Logger.getLogger(App.class.getName());

    public static final String CONFIGURED_SERVICES              = "configured.services";
    public static final String FIREBASE_URL                     = "firebase.url";

    public static final String CONFIGURATION                    = "configuration.provider";


    private final Map<String, Object> attributes                = new ConcurrentHashMap<>(10, 0.9f, 8);
    private final Map<Class, Service> serviceCache              = new ConcurrentHashMap<>(10, 0.9f, 8);
    private final Set<Class> registeredServiceClasses           = new LinkedHashSet<>();
    private final Set<String> configuredServiceClasses          = new LinkedHashSet<>();
    private Set<String> moduleClasses                           = new LinkedHashSet<>();
    private Set<Class> entityClasses                            = new LinkedHashSet<>();


    private ConfigurationProvider configuration                 = null;
    private Properties feerlarocConf                            = new Properties();
    private boolean initializationDone                          = false;
    private boolean shutdownDone                                = false;


    private static Properties baseConf                          = null;
    private static Services singletonInstance                   = null;

    private String configuredServiceNames                       = null;
    private String configurationClass                           = null;


    public static Services getInstance() {
        return singletonInstance;
    }

    private void initialize() {
        singletonInstance.initialize(getBaseConfiguration());
    }

    public static void initialize(Set<String> _moduleServiceClasses, Set<Class> _entityClasses){

        singletonInstance = new Services();
        singletonInstance.moduleClasses = _moduleServiceClasses;
        singletonInstance.entityClasses = _entityClasses;

        singletonInstance.initialize(getBaseConfiguration());


    }

    @Override
    public void registerInitializationCallback(InitializationCallback callback) {

    }

    public <T extends Command> T command(final Class<T> commandType) {

        Class serviceClass = null;
        T command = null;

        try {

            command = commandType.newInstance();

            // inject security context first
            //command.setArgument("securityContext", securityContext);

            serviceClass = command.getServiceClass();

            if ((serviceClass != null) && configuredServiceClasses.contains(serviceClass.getSimpleName())) {

                // search for already running service..
                Service service = serviceCache.get(serviceClass);

                if (service == null) {

                    // service not cached
                    service = createService(serviceClass);

                } else {

                    // check RunnableService for isRunning()..
                    if (service instanceof RunnableService) {

                        RunnableService runnableService = (RunnableService) service;

                        if (!runnableService.isRunning()) {

                            runnableService.stopService();
                            runnableService.shutdown();
                            service = createService(serviceClass);

                        }
                    }
                }

                logger.log(Level.FINEST, "Initializing command ", commandType.getName());
                service.injectArguments(command);
            }

            command.initialized();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return (command);
    }


    private Service createService(Class serviceClass) {

        logger.log(Level.FINE, "Creating service ", serviceClass.getName());

        Service service = null;

        try {

            service = (Service) serviceClass.newInstance();
            service.initialize(this, getCurrentConfig());

            if (service instanceof RunnableService) {

                RunnableService runnableService = (RunnableService) service;

                if (runnableService.runOnStartup()) {

                    logger.log(Level.FINER, "Starting RunnableService instance ", serviceClass.getName());

                    // start RunnableService and cache it
                    runnableService.startService();
                    serviceCache.put(serviceClass, service);
                }

            } else if (service instanceof SingletonService) {

                // cache SingletonService
                serviceCache.put(serviceClass, service);
            }

        } catch (Throwable t) {

            t.printStackTrace();

            if (service.isVital()) {

                logger.log(Level.SEVERE, "Vital service {0} failed to start: {1}. Aborting", new Object[] { service.getClass().getSimpleName(), t.getMessage() } );

                // hard(est) exit
                System.exit(1);

            } else {

                logger.log(Level.SEVERE, "Service {0} failed to start: {1}.", new Object[] { service.getClass().getSimpleName(), t.getMessage() } );
            }
        }

        return service;
    }

    private void initialize(final Properties properties) {

        this.feerlarocConf = properties;

        configurationClass     = properties.getProperty(Services.CONFIGURATION);
        configuredServiceNames = properties.getProperty(Services.CONFIGURED_SERVICES);

        // create set of configured services
        configuredServiceClasses.addAll(Arrays.asList(configuredServiceNames.split("[ ,]+")));

        // if configuration is not yet established, instantiate it
        // this is the place where the service classes get the
        // opportunity to modify the default configuration
        getConfigurationProvider();

        //START This should not be here KHOSI: MAYBE ITS OKAY
        for (final String serviceClassName : configuredServiceClasses){

            String moduleClassName = getModuleClass(serviceClassName);
            if(moduleClassName != null){
                try {
                    Class clazz = Class.forName(moduleClassName);
                    registerServiceClass(clazz);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }


        //END This should not be happening here

        logger.log(Level.INFO, "Starting services");

        // initialize other services
        for (final String serviceClassName : configuredServiceClasses) {

            Class serviceClass = getServiceClassForName(serviceClassName);
            if (serviceClass != null) {

                try {

                    final Service service = createService(serviceClass);
                    if (service != null) {

                        service.initialized();

                    } else {

                        logger.log(Level.WARNING, "Service {0} was not started!", serviceClassName);
                    }

                } catch (Throwable t) {

                    logger.log(Level.WARNING, "Exception while registering service {0}: {1}", new Object[] { serviceClassName, t });
                    t.printStackTrace();
                }
            }
        }

        logger.log(Level.INFO, "{0} service(s) processed", serviceCache.size());
        registeredServiceClasses.clear();

        logger.log(Level.INFO, "Registering shutdown hook.");

        logger.log(Level.INFO, "Initialization complete");

        initializationDone = true;
    }


    private String getModuleClass(String serviceClassName){

        for (final String moduleClassName : moduleClasses) {
            try {
                String clazz = Class.forName(moduleClassName).getSimpleName();
                if(clazz.equals(serviceClassName)){
                    return moduleClassName;
                }
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public <T extends Service> T getService(Class<T> serviceClass) {
        return null;
    }

    public Set<String> getResources() {

        final Set<String> resources = new LinkedHashSet<>();

        // scan through structr.conf and try to identify module-specific classes
        for (final Object configurationValue : feerlarocConf.values()) {

            for (final String value : configurationValue.toString().split("[\\s ,;]+")) {

                try {

                    // try to load class and find source code origin
                    final Class candidate = Class.forName(value);
                    if (!candidate.getName().startsWith("com.feerlaroc")) {

                        final String codeLocation = candidate.getProtectionDomain().getCodeSource().getLocation().toString();
                        if (codeLocation.startsWith("file:") && codeLocation.endsWith(".jar") || codeLocation.endsWith(".war")) {

                            final File file = new File(URI.create(codeLocation));
                            if (file.exists()) {

                                resources.add(file.getAbsolutePath());
                            }
                        }
                    }

                } catch (Throwable ignore) { }
            }
        }

        logger.log(Level.INFO, "Found {0} possible resources: {1}", new Object[] { resources.size(), resources } );

        return resources;
    }



    public Properties getCurrentConfig() {
        return feerlarocConf;
    }

    public static Properties getBaseConfiguration() {

        if (baseConf == null) {

            baseConf = new Properties();

            baseConf.setProperty(CONFIGURATION,         JarConfigurationProvider.class.getName());

            baseConf.setProperty(FIREBASE_URL,          "https://myquantum.firebaseio.com/");
            baseConf.setProperty(CONFIGURED_SERVICES,   "FirebaseService ZohoService");

        }

        return baseConf;
    }

    public String getConfigurationValue(String key) {
        return getConfigurationValue(key, "");
    }

    public String getConfigurationValue(String key, String defaultValue) {
        return getCurrentConfig().getProperty(key, defaultValue);
    }

    /**
     * Registers a service, enabling the service layer to automatically start
     * autorun servies.
     *
     * @param serviceClass the service class to register
     */
    public void registerServiceClass(Class serviceClass) {

        registeredServiceClasses.add(serviceClass);

    }

    public Class getServiceClassForName(final String serviceClassName) {

        for (Class serviceClass : registeredServiceClasses) {

            if (serviceClass.getSimpleName().equals(serviceClassName)) {
                return serviceClass;
            }

        }

        return null;
    }

    public Class getEntityClassForName(final String entityClassName){

        for (Class entityClass : entityClasses) {

            if(entityClass.getSimpleName().equals(entityClassName)){
                return entityClass;
            }

        }

        return null;
    }

    public ConfigurationProvider getConfigurationProvider() {

        // instantiate configuration provider
        if (configuration == null) {

            // when executing tests, the configuration class may already exist,
            // so we don't instantiate it again since all the entities are already
            // known to the ClassLoader and we would miss the code in all the static
            // initializers.
            try {

                configuration = (ConfigurationProvider)Class.forName(configurationClass).newInstance();
                configuration.initialize();

            } catch (Throwable t) {

                t.printStackTrace();

                logger.log(Level.SEVERE, "Unable to instantiate schema provider of type {0}: {1}", new Object[] { configurationClass, t.getMessage() });
            }
        }

        return configuration;
    }


}
