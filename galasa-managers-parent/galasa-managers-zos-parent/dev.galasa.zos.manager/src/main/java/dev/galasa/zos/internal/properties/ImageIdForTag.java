/*
 * Copyright contributors to the Galasa project
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package dev.galasa.zos.internal.properties;

import javax.validation.constraints.NotNull;

import dev.galasa.framework.spi.ConfigurationPropertyStoreException;
import dev.galasa.framework.spi.cps.CpsProperties;
import dev.galasa.zos.ZosManagerException;

/**
 * The zOS Image
 * 
 * @galasa.cps.property
 * 
 * @galasa.name zos.dse.tag.[tag].imageid
 * 
 * @galasa.description The image ID for the specified tag
 * 
 * @galasa.required No
 * 
 * @galasa.default None
 * 
 * @galasa.valid_values 
 * 
 * @galasa.examples 
 * <code>zos.dse.tag.[tag].imageid=SYSA</code><br>
 *
 */
public class ImageIdForTag extends CpsProperties {
    
    public static String get(@NotNull String tag) throws ZosManagerException {
        try {
            return getStringNulled(ZosPropertiesSingleton.cps(), "tag", "imageid", tag);
        } catch (ConfigurationPropertyStoreException e) {
            throw new ZosManagerException("Problem asking the CPS for the image id for tag '"  + tag + "'", e);
        }
    }

}
