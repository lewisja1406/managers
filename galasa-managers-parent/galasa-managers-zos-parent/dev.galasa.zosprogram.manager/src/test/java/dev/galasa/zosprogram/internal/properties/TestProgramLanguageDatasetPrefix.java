/*
 * Copyright contributors to the Galasa project
 */
package dev.galasa.zosprogram.internal.properties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

import dev.galasa.framework.spi.ConfigurationPropertyStoreException;
import dev.galasa.framework.spi.IConfigurationPropertyStoreService;
import dev.galasa.framework.spi.cps.CpsProperties;
import dev.galasa.zosprogram.ZosProgram.Language;
import dev.galasa.zosprogram.ZosProgramManagerException;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ZosProgramPropertiesSingleton.class, CpsProperties.class})
public class TestProgramLanguageDatasetPrefix {
//    
//    @Mock
//    private IConfigurationPropertyStoreService configurationPropertyStoreServiceMock;
//    
//    private static final String IMAGE_ID = "IMAGE";
//    private static final String PREFIX = "PREFIX";
//    private static final List<String> PREFIX_LIST = Arrays.asList(PREFIX);
//    
//    @Test
//    public void testConstructor() {
//        ProgramLanguageDatasetPrefix programLanguageDatasetPrefix = new ProgramLanguageDatasetPrefix();
//        Assert.assertNotNull("Object was not created", programLanguageDatasetPrefix);
//    }
//    
//    @Test
//    public void testEmpty() throws Exception {
//        String expectedMessage = "Required property zosprogram.cobol.[imageid].dataset.prefix not supplied";
//        ZosProgramManagerException expectedException = Assert.assertThrows("expected exception should be thrown", ZosProgramManagerException.class, ()->{
//        	getProperty(Collections.emptyList(), Language.COBOL);
//        });
//    	Assert.assertEquals("exception should contain expected cause", expectedMessage, expectedException.getMessage());
//    }
//    
//    @Test
//    public void testValid() throws Exception {
//        Assert.assertEquals("Unexpected value returned from ProgramLanguageDatasetPrefix.get()", PREFIX_LIST, getProperty(PREFIX_LIST, Language.COBOL));
//    }
//    
//    @Test
//    public void testException() throws Exception {
//        String expectedMessage = "Problem asking the CPS for the zOS program COBOL dataset prefix for zOS image " + IMAGE_ID;
//        ZosProgramManagerException expectedException = Assert.assertThrows("expected exception should be thrown", ZosProgramManagerException.class, ()->{
//        	getProperty(Arrays.asList("ANY"), Language.COBOL, true);
//        });
//    	Assert.assertEquals("exception should contain expected cause", expectedMessage, expectedException.getMessage());
//    }
//
//    private List<String> getProperty(List<String> prefixList, Language language) throws Exception {
//        return getProperty(prefixList, language, false);
//    }
//    
//    private List<String> getProperty(List<String> prefixList, Language language, boolean exception) throws Exception {
//        PowerMockito.spy(ZosProgramPropertiesSingleton.class);
//        PowerMockito.doReturn(configurationPropertyStoreServiceMock).when(ZosProgramPropertiesSingleton.class, "cps");
//        PowerMockito.spy(CpsProperties.class);
//        
//        if (!exception) {
//            PowerMockito.doReturn(prefixList).when(CpsProperties.class, "getStringList", Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());            
//        } else {
//            PowerMockito.doThrow(new ConfigurationPropertyStoreException()).when(CpsProperties.class, "getStringList", Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
//        }
//        
//        return ProgramLanguageDatasetPrefix.get(IMAGE_ID, language);
//    }
}
