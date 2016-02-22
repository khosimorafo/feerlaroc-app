package com.feerlaroc.core.service;


import com.feerlaroc.core.Command;

import java.util.Properties;


//~--- interfaces -------------------------------------------------------------

/**
 * The base class for services in structr.
 *
 *
 */
public interface Service {

    /**
     * Called by Services#createCommand before the command is returned to
     * the user. Use this method to inject service-specific resources into your command
     * objects so you can access them later in the <code>execute()</code> method.
     *
     * @param command
     */
    public void injectArguments(Command command);

    /**
     * Called by the service layer after the service is instantiated to initialize
     * service-specific resources etc.
     *
     * @param services
     * @param config
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void initialize(final CoreServices services, final Properties config) throws ClassNotFoundException, InstantiationException, IllegalAccessException;

    /**
     * Called before the service is discarded. Note that this method will not be called
     * for instances of {@link //PrototypeService}.
     */
    public void shutdown();

    /**
     * Called by the service layer when the service was initialized successfully
     */
    public void initialized();

    /**
     * Return name of service
     * @return name
     */
    public String getName();

    /**
     * Return true if Service is running.
     * @return isRunning
     */
    public boolean isRunning();

    /**
     * Return true if Service is vital for the start of Structr. The failure
     * of vital services will stop Structr from starting and display an
     * appropriate error message.
     * @return a boolean
     */
    public boolean isVital();

}
