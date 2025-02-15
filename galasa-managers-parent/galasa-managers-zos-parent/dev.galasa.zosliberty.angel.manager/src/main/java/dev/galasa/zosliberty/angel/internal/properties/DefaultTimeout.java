/*
 /*
 * Copyright contributors to the Galasa project
 */
package dev.galasa.zosliberty.angel.internal.properties;

import dev.galasa.framework.spi.cps.CpsProperties;
import dev.galasa.zos.IZosImage;
import dev.galasa.zosliberty.angel.ZosLibertyAngelManagerException;

/**
 * The Default Timeout value in seconds for Liberty angel processes on a zOS Image
 * 
 * @galasa.cps.property
 * 
 * @galasa.name zosliberty.angel.[image].default.timeout
 * 
 * @galasa.description Provides a value for the default timeout for Liberty angel processes on a zOS Image
 * 
 * @galasa.required No
 * 
 * @galasa.default 20 seconds
 * 
 * @galasa.valid_values 
 * 
 * @galasa.examples 
 * <code>zosliberty.angel.[image].default.timeout=20</code><br>
 *
 */
public class DefaultTimeout extends CpsProperties {

    public static int get(IZosImage image) throws ZosLibertyAngelManagerException {
            return getIntWithDefault(ZosLibertyAngelPropertiesSingleton.cps(), 20000, "angel", "default.timeout", image.getImageID());
    }
}
