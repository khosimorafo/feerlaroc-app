package com.feerlaroc.core.service;

/**
 * A service that can be run in a separate thread. It is a good practice to
 * let your RunnableService implementation extend java.lang.Thread and map
 * the startService() and stopService() methods appropriately.
 *
 *
 */
public interface RunnableService extends Service {

    public void startService();

    public void stopService();

    /**
     * Return true if the service should be started automatically
     * on container startup
     *
     * @return runOnStartup
     */
    public boolean runOnStartup();

    @Override
    public boolean isRunning();

}
