/*
 * Copyright contributors to the Galasa project
 */
package dev.galasa.zosprogram.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

import dev.galasa.artifact.IBundleResources;
import dev.galasa.zos.IZosImage;
import dev.galasa.zosbatch.IZosBatchJob;
import dev.galasa.zosbatch.spi.IZosBatchSpi;
import dev.galasa.zosfile.IZosDataset;
import dev.galasa.zosprogram.ZosProgram.Language;
import dev.galasa.zosprogram.ZosProgramException;
import dev.galasa.zosprogram.ZosProgramManagerException;
import dev.galasa.zosprogram.internal.properties.CICSDatasetPrefix;
import dev.galasa.zosprogram.internal.properties.LanguageEnvironmentDatasetPrefix;
import dev.galasa.zosprogram.internal.properties.ProgramLanguageCompileSyslibs;
import dev.galasa.zosprogram.internal.properties.ProgramLanguageDatasetPrefix;
import dev.galasa.zosprogram.internal.properties.ProgramLanguageLinkSyslibs;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ProgramLanguageDatasetPrefix.class, LanguageEnvironmentDatasetPrefix.class, ProgramLanguageCompileSyslibs.class, ProgramLanguageLinkSyslibs.class, CICSDatasetPrefix.class})
public class TestZosCProgramCompiler {
//    
//    private ZosCProgramCompiler zosCProgramCompiler;
//    
//    private ZosCProgramCompiler zosCProgramCompilerSpy;
//
//    @Mock
//    private IZosImage zosImageMock;
//    
//    @Mock
//    private IBundleResources bundleResourcesMock;
//    
//    @Mock
//    private IZosBatchSpi zosBatchMock;
//
//    @Mock
//    private IZosBatchJob zosBatchJobMock;
//
//    @Mock
//    private ZosProgramImpl zosProgramMock;
//
//    @Mock
//    private ZosProgramManagerImpl zosProgramManagerMock;
//
//    @Mock
//    private IZosDataset loadlibMock;
//
//    private static final String NAME = "NAME";
//
//    private static final Language LANGUAGE = Language.C;
//
//    private static final String SOURCE = "SOURCE";
//
//    private static final String LOADLIB = "LOAD.LIBRARY";
//
//    @Before
//    public void setup() throws Exception {
//        Mockito.when(zosProgramMock.getLanguage()).thenReturn(Language.C);
//        Mockito.when(zosProgramMock.getLoadlib()).thenReturn(loadlibMock);
//        Mockito.when(zosProgramMock.getImage()).thenReturn(zosImageMock);
//        Mockito.when(zosProgramMock.getName()).thenReturn(NAME);
//        Mockito.when(zosProgramMock.getProgramSource()).thenReturn(SOURCE);
//        Mockito.when(loadlibMock.getName()).thenReturn(LOADLIB);
//        Mockito.when(zosProgramMock.getLoadlib()).thenReturn(loadlibMock);
//        Mockito.when(zosProgramMock.getZosProgramManager()).thenReturn(zosProgramManagerMock);
//        Mockito.when(zosProgramManagerMock.getManagerBundleResources()).thenReturn(bundleResourcesMock);
//        PowerMockito.mockStatic(ProgramLanguageDatasetPrefix.class);
//        Mockito.when(ProgramLanguageDatasetPrefix.get(Mockito.any(), Mockito.any())).thenReturn(Arrays.asList());
//        PowerMockito.mockStatic(LanguageEnvironmentDatasetPrefix.class);
//        Mockito.when(LanguageEnvironmentDatasetPrefix.get(Mockito.any())).thenReturn(Arrays.asList());
//        PowerMockito.mockStatic(ProgramLanguageCompileSyslibs.class);
//        Mockito.when(ProgramLanguageCompileSyslibs.get(Mockito.any(), Mockito.any())).thenReturn(Arrays.asList());
//        PowerMockito.mockStatic(ProgramLanguageLinkSyslibs.class);
//        Mockito.when(ProgramLanguageLinkSyslibs.get(Mockito.any(), Mockito.any())).thenReturn(Arrays.asList());
//        PowerMockito.mockStatic(CICSDatasetPrefix.class);
//        Mockito.when(CICSDatasetPrefix.get(Mockito.any())).thenReturn(Arrays.asList());
//        Mockito.when(zosProgramMock.getLoadlib()).thenReturn(loadlibMock);
//        
//        zosCProgramCompiler = new ZosCProgramCompiler(zosProgramMock);
//        zosCProgramCompilerSpy = Mockito.spy(zosCProgramCompiler);
//    }
//    
//    @Test
//    public void testBuildParameters() throws ZosProgramManagerException {
//        HashMap<String, Object> parameters = new HashMap<>();
//        Mockito.when(zosProgramMock.isCics()).thenReturn(true);
//        parameters.put("LKED.SYSLIB", "DUMMY");
//        parameters.put("LKED.PARM", "'AMODE=31,MAP,RENT,DYNAM=DLL,CASE=MIXED'");
//        parameters.put("C.SYSLIB", "DUMMY");
//        parameters.put("SOURCE", SOURCE);
//        parameters.put("C.PARM", "'SOURCE,LIST,RENT,CICS'");
//        parameters.put("SYSLMOD", LOADLIB);
//        parameters.put("TYPE", "C/CICS");
//        parameters.put("PROGRAM", NAME);
//        parameters.put("C.STEPLIB", "DUMMY");
//        parameters.put("LKED.SYSIN", 
//                "*\n" + 
//                "  INCLUDE SYSLIB(DFHELII)\n" + 
//                "//         DD DISP=(OLD,DELETE),DSN=&&SYSLIN\n" + 
//                "//         DD *\n" + 
//                "  NAME NAME(R)");
//        Assert.assertEquals("Error in buildParameters() method", parameters, zosCProgramCompilerSpy.buildParameters());
//        
//        parameters = new HashMap<>();
//        Mockito.when(zosProgramMock.isCics()).thenReturn(false);
//        parameters.put("LKED.SYSLIB", "DUMMY");
//        parameters.put("LKED.PARM", "'AMODE=31,MAP,RENT,DYNAM=DLL,CASE=MIXED'");
//        parameters.put("C.SYSLIB", "DUMMY");
//        parameters.put("SOURCE", SOURCE);
//        parameters.put("C.PARM", "'SOURCE,LIST,RENT'");
//        parameters.put("SYSLMOD", LOADLIB);
//        parameters.put("TYPE", "C/BATCH");
//        parameters.put("PROGRAM", NAME);
//        parameters.put("C.STEPLIB", "DUMMY");
//        parameters.put("LKED.SYSIN", 
//                "DISP=(OLD,DELETE),DSN=&&SYSLIN\n" + 
//                "//         DD *\n" + 
//                "  NAME NAME(R)");
//        Assert.assertEquals("Error in buildParameters() method", parameters, zosCProgramCompilerSpy.buildParameters());
//        
//        Mockito.when(ProgramLanguageDatasetPrefix.get(Mockito.any(), Mockito.any())).thenThrow(new ZosProgramManagerException());
//        String expectedMessage = "Problem building compile JCL for " + LANGUAGE + " program " + NAME;
//        ZosProgramException expectedException = Assert.assertThrows("expected exception should be thrown", ZosProgramException.class, ()->{
//        	zosCProgramCompilerSpy.buildParameters();
//        });
//    	Assert.assertEquals("exception should contain expected cause", expectedMessage, expectedException.getMessage());
//    }
//    
//    @Test
//    public void testBuildSteplib() {
//        setupFormatDatasetConcatenationAnswer();
//        List<String> languagePrefix = new LinkedList<>();
//        List<String> lePrefix = new LinkedList<>();
//        List<String> cicsPrefix = new LinkedList<>();
//        String datasetList = "";
//        Assert.assertEquals("Error in buildSteplib() method", datasetList, zosCProgramCompilerSpy.buildSteplib(languagePrefix, lePrefix, cicsPrefix));
//
//        lePrefix.add("LE");
//        languagePrefix.add("C");
//        cicsPrefix.add("CICS");
//        datasetList = "C.SCCNCMP@LE.SCEERUN@LE.SCEERUN2@CICS.SDFHLOAD";
//        Assert.assertEquals("Error in buildSteplib() method", datasetList, zosCProgramCompilerSpy.buildSteplib(languagePrefix, lePrefix, cicsPrefix));
//    }
//    
//    @Test
//    public void testBuildCompileSyslib() {
//        setupFormatDatasetConcatenationAnswer();
//        List<String> compileSyslibs = new LinkedList<>();
//        List<String> lePrefix = new LinkedList<>();
//        List<String> cicsPrefix = new LinkedList<>();
//        String datasetList = "";
//        Assert.assertEquals("Error in buildCompileSyslib() method", datasetList, zosCProgramCompilerSpy.buildCompileSyslib(compileSyslibs, lePrefix, cicsPrefix));
//
//        compileSyslibs.add("MY.SYSLIB");
//        lePrefix.add("LE");
//        cicsPrefix.add("CICS");
//        datasetList = "MY.SYSLIB@LE.SCEESAMP@CICS.SDFHC370@CICS.SDFHMAC@CICS.SDFHSAMP";
//        Assert.assertEquals("Error in buildCompileSyslib() method", datasetList, zosCProgramCompilerSpy.buildCompileSyslib(compileSyslibs, lePrefix, cicsPrefix));
//    }
//    
//    @Test
//    public void testBuildLinkSyslib() {
//        setupFormatDatasetConcatenationAnswer();
//        List<String> linkSyslibs = new LinkedList<>();
//        List<String> lePrefix = new LinkedList<>();
//        List<String> cicsPrefix = new LinkedList<>();
//        String datasetList = "";
//        Assert.assertEquals("Error in buildLinkSyslib() method", datasetList, zosCProgramCompilerSpy.buildLinkSyslib(linkSyslibs, lePrefix, cicsPrefix));
//
//        linkSyslibs.add("MY.SYSLIB");
//        lePrefix.add("LE");
//        cicsPrefix.add("CICS");
//        datasetList = "MY.SYSLIB@CICS.SDFHLOAD@LE.SCEELKEX@LE.SCEELKED";
//        Assert.assertEquals("Error in buildLinkSyslib() method", datasetList, zosCProgramCompilerSpy.buildLinkSyslib(linkSyslibs, lePrefix, cicsPrefix));
//    }
//
//    private void setupFormatDatasetConcatenationAnswer() {
//        Answer<String> answer = new Answer<String>() {
//            @Override
//            public String answer(InvocationOnMock invocation) throws Throwable {
//                List<String> datasetList = invocation.getArgument(0);
//                StringBuilder sb = new StringBuilder();
//                Iterator<String> it = datasetList.iterator();
//                while (it.hasNext()) {
//                    sb.append(it.next());
//                    sb.append("@");
//                }
//                if (sb.length() > 0) {
//                    sb.delete(sb.length()-1, sb.length());
//                }
//                return sb.toString();
//            }
//        };
//        Mockito.doAnswer(answer).when(zosCProgramCompilerSpy).formatDatasetConcatenation(Mockito.any());
//    }
}
