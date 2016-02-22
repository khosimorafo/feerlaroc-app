package com.feerlaroc.firebase.core;

import java.io.Serializable;
import java.util.Set;

public interface Module extends Serializable
{
    /**
     * @return the path of this module
     */
    public String getModulePath();

    /**
     * @return the classes this module contains
     */
    public Set<String> getClasses();

    /**
     * @return the properties this module contains
     */
    public Set<String> getProperties();

    /**
     * @return the resources this module contains
     */
    public Set<String> getResources();

    /**
     * @return the libraries this module contains
     */
    public Set<String> getLibraries();
}
