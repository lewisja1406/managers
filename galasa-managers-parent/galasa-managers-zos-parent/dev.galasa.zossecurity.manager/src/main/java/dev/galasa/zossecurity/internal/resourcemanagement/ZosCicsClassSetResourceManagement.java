/*
 * Copyright contributors to the Galasa project
 */
package dev.galasa.zossecurity.internal.resourcemanagement;

import static dev.galasa.zossecurity.internal.ZosSecurityImpl.ZOS_CICS_CLASS_SET_PATTERN;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dev.galasa.framework.spi.IDynamicStatusStoreService;
import dev.galasa.framework.spi.IFramework;
import dev.galasa.framework.spi.IResourceManagement;
import dev.galasa.zossecurity.ZosSecurityManagerException;
import dev.galasa.zossecurity.internal.ZosSecurityImpl;
import dev.galasa.zossecurity.internal.ZosSecurityImpl.ResourceType;
import dev.galasa.zossecurity.internal.resources.ZosCicsClassSetImpl;

public class ZosCicsClassSetResourceManagement implements Runnable {

	private final ZosSecurityImpl zosSecurity;
    private final IFramework framework;
    private final IResourceManagement resourceManagement;
    private final IDynamicStatusStoreService dss;
    private final Log logger = LogFactory.getLog(this.getClass());

    public ZosCicsClassSetResourceManagement(ZosSecurityImpl zosSecurtityImpl, IFramework framework, IResourceManagement resourceManagement, IDynamicStatusStoreService dss) {
        this.zosSecurity = zosSecurtityImpl;
    	this.framework = framework;
        this.resourceManagement = resourceManagement;
        this.dss = dss;
        this.logger.info("zOS CICS Class Set resource management initialised");
    }

	@Override
    public void run() {
        logger.info("Starting zOS CICS Class Set cleanup");
        try {
            // Find all the runs with zOS CICS Class Sets
            Map<String, String> zosCicsClassSets = dss.getPrefix(ResourceType.ZOS_CICS_CLASS_SET.getName() + ".run.");

            Set<String> activeRunNames = this.framework.getFrameworkRuns().getActiveRunNames();

            for (String key : zosCicsClassSets.keySet()) {
                Matcher matcher = ZOS_CICS_CLASS_SET_PATTERN.matcher(key);
                if (matcher.find()) {
                    String runName = matcher.group(1);

                    if (!activeRunNames.contains(runName)) {
                        String cicsClassSet = matcher.group(2);
                        String sysplexId = matcher.group(3);

                        if (!activeRunNames.contains(runName)) {
                        	logger.info("Discarding zOS CICS Class Set " + cicsClassSet + " on sysplex " + sysplexId + " as run " + runName + " has gone");

                        	try {
                        		ZosCicsClassSetImpl zosCicsClassSet = new ZosCicsClassSetImpl(zosSecurity, cicsClassSet, sysplexId, runName);
                        		zosCicsClassSet.delete();
                        	} catch (ZosSecurityManagerException e) {
                        		logger.error("Failed to discard zOS CICS Class Set " + cicsClassSet + " for run " + runName + " - " + e.getCause());
                        	}
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Failure during zOS CICS Class Set cleanup", e);
        }

        this.resourceManagement.resourceManagementRunSuccessful();
        logger.info("Finished zOS CICS Class Set cleanup");
    }

    public void runFinishedOrDeleted(String runName) {
        try {
            Map<String, String> serverRuns = dss.getPrefix(ResourceType.ZOS_CICS_CLASS_SET.getName() + ".run." + runName + ".");
            for (String key : serverRuns.keySet()) {
                Matcher matcher = ZOS_CICS_CLASS_SET_PATTERN.matcher(key);
                if (matcher.find()) {
                	String cicsClassSet = matcher.group(2);
                    String sysplexId = matcher.group(3);

                    logger.info("Discarding zOS CICS Class Set " + cicsClassSet + " on sysplex " + sysplexId + " as run " + runName + " has gone");
                    
                    try {
                		ZosCicsClassSetImpl zosCicsClassSet = new ZosCicsClassSetImpl(zosSecurity, cicsClassSet, sysplexId, runName);
                		zosCicsClassSet.delete();
                	} catch (ZosSecurityManagerException e) {
                		logger.error("Failed to discard zOS CICS Class Set " + cicsClassSet + " for run " + runName + " - " + e.getCause());
                	}
                }
            }
        } catch (Exception e) {
            logger.error("Failed to delete zOS CICS Class Set for run " + runName + " - " + e.getCause());
        }
    }
}
