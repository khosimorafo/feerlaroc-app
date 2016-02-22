package com.feerlaroc.core.module;

import com.feerlaroc.core.Services;
import com.feerlaroc.core.schema.ConfigurationProvider;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 2016/02/15.
 */
public class JarConfigurationProvider implements ConfigurationProvider {

    private static final Logger logger = Logger.getLogger(JarConfigurationProvider.class.getName());

    private final String fileSep             = System.getProperty("file.separator");
    private final String pathSep             = System.getProperty("path.separator");
    private final String fileSepEscaped      = fileSep.replaceAll("\\\\", "\\\\\\\\");
    private final String testClassesDir      = fileSep.concat("test-classes");
    private final String classesDir          = fileSep.concat("classes");



    @Override
    public void initialize() {
        //scanResources();
    }

    @Override
    public void shutdown() {

    }


    // ----- private methods -----
    private void scanResources() {

        Set<String> resourcePaths = getResourcesToScan();

        for (String resourcePath : resourcePaths) {

            //scanResource(resourcePath);
        }

        logger.log(Level.INFO, "{0} JARs scanned", resourcePaths.size());

    }

    /**
     * Scans the class path and returns a Set containing all structr
     * modules.
     *
     * @return a Set of active module names
     */
    private Set<String> getResourcesToScan() {

        String classPath = System.getProperty("java.class.path");
        Set<String> modules = new LinkedHashSet<>();
        Pattern pattern = Pattern.compile(".*(feerlaroc).*(war|jar)");
        Matcher matcher = pattern.matcher("");

        /*for (String jarPath : classPath.split("[".concat(pathSep).concat("]+"))) {

            String lowerPath = jarPath.toLowerCase();

            if (lowerPath.endsWith(classesDir) || lowerPath.endsWith(testClassesDir)) {

                modules.add(jarPath);

            } else {

                String moduleName = lowerPath.substring(lowerPath.lastIndexOf(pathSep) + 1);

                matcher.reset(moduleName);

                if (matcher.matches()) {

                    modules.add(jarPath);
                }

            }

        }*/

        for (String resource : Services.getInstance().getResources()) {

            String lowerResource = resource.toLowerCase();

            if (lowerResource.endsWith(".jar") || lowerResource.endsWith(".war")) {

                modules.add(resource);
            }

        }

        return modules;
    }


}
