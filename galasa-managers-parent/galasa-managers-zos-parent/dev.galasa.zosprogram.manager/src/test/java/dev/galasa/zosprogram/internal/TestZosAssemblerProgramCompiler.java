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
public class TestZosAssemblerProgramCompiler {
//    
//    private ZosAssemblerProgramCompiler zosAssemblerProgramCompiler;
//    
//    private ZosAssemblerProgramCompiler zosAssemblerProgramCompilerSpy;
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
//    private static final Language LANGUAGE = Language.ASSEMBLER;
//
//    private static final String SOURCE = "SOURCE";
//
//    private static final String LOADLIB = "LOAD.LIBRARY";
//
//    @Before
//    public void setup() throws Exception {
//        Mockito.when(zosProgramMock.getLanguage()).thenReturn(Language.ASSEMBLER);
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
//        zosAssemblerProgramCompiler = new ZosAssemblerProgramCompiler(zosProgramMock);
//        zosAssemblerProgramCompilerSpy = Mockito.spy(zosAssemblerProgramCompiler);
//    }
//    
//    @Test
//    public void testBuildParameters() throws ZosProgramManagerException {
//        HashMap<String, Object> parameters = new HashMap<>();
//        Mockito.when(zosProgramMock.isCics()).thenReturn(true);
//        parameters.put("LKED.SYSLIB", "DUMMY");
//        parameters.put("LKED.PARM", "'XREF,LET,LIST'");
//        parameters.put("TRN.STEPLIB", "DUMMY");
//        parameters.put("ASM.SYSLIB", "DISP=SHR,DSN=SYS1.MACLIB\n//         DD DISP=SHR,DSN=SYS1.MODGEN");
//        parameters.put("SOURCE", SOURCE);
//        parameters.put("ASM.PARM", "'NODECK,OBJECT,XREF(SHORT),LIST'");
//        parameters.put("SYSLMOD", LOADLIB);
//        parameters.put("TYPE", "ASSEMBLER/CICS");
//        parameters.put("PROGRAM", NAME);
//        parameters.put("LKED.SYSIN",
//                "DISP=(OLD,DELETE),DSN=&&SYSLIN\n" +
//                "//         DD *\n" +
//                "  ORDER DFHEAG\n" +
//                "  INCLUDE SYSLIB(DFHEAG)\n" +
//                "  ENTRY NAME\n" + 
//                "  NAME NAME(R)");
//        Assert.assertEquals("Error in buildParameters() method", parameters, zosAssemblerProgramCompilerSpy.buildParameters());
//        
//        parameters = new HashMap<>();
//        Mockito.when(zosProgramMock.isCics()).thenReturn(false);
//        parameters.put("LKED.SYSLIB", "DUMMY");
//        parameters.put("LKED.PARM", "'XREF,LET,LIST'");
//        parameters.put("ASM.SYSLIB", "DISP=SHR,DSN=SYS1.MACLIB\n//         DD DISP=SHR,DSN=SYS1.MODGEN");
//        parameters.put("SOURCE", SOURCE);
//        parameters.put("ASM.PARM", "'NODECK,OBJECT,XREF(SHORT),LIST'");
//        parameters.put("SYSLMOD", LOADLIB);
//        parameters.put("TYPE", "ASSEMBLER/BATCH");
//        parameters.put("PROGRAM", NAME);
//        parameters.put("LKED.SYSIN", 
//                "DISP=(OLD,DELETE),DSN=&&SYSLIN\n" +
//                "//         DD *\n" +
//                "  ENTRY NAME\n" + 
//                "  NAME NAME(R)");
//        Assert.assertEquals("Error in buildParameters() method", parameters, zosAssemblerProgramCompilerSpy.buildParameters());
//        
//        Mockito.when(LanguageEnvironmentDatasetPrefix.get(Mockito.any())).thenThrow(new ZosProgramManagerException());
//        String expectedMessage = "Problem building compile JCL for " + LANGUAGE + " program " + NAME;
//        ZosProgramException expectedException = Assert.assertThrows("expected exception should be thrown", ZosProgramException.class, ()->{
//        	zosAssemblerProgramCompilerSpy.buildParameters();
//        });
//    	Assert.assertEquals("exception should contain expected cause", expectedMessage, expectedException.getMessage());
//    }
//    
//    @Test
//    public void testBuildSteplib() {
//        setupFormatDatasetConcatenationAnswer();
//        List<String> cicsPrefix = new LinkedList<>();
//        String datasetList = "";
//        Assert.assertEquals("Error in buildSteplib() method", datasetList, zosAssemblerProgramCompilerSpy.buildSteplib(cicsPrefix));
//
//        cicsPrefix.add("CICS");
//        datasetList = "CICS.SDFHLOAD";
//        Assert.assertEquals("Error in buildSteplib() method", datasetList, zosAssemblerProgramCompilerSpy.buildSteplib(cicsPrefix));
//    }
//    
//    @Test
//    public void testBuildCompileSyslib() {
//        setupFormatDatasetConcatenationAnswer();
//        List<String> compileSyslibs = new LinkedList<>();
//        List<String> lePrefix = new LinkedList<>();
//        List<String> cicsPrefix = new LinkedList<>();
//        String datasetList = "SYS1.MACLIB@SYS1.MODGEN";
//        Assert.assertEquals("Error in buildCompileSyslib() method", datasetList, zosAssemblerProgramCompilerSpy.buildCompileSyslib(compileSyslibs, lePrefix, cicsPrefix));
//
//        compileSyslibs.add("MY.SYSLIB");
//        lePrefix.add("LE");
//        cicsPrefix.add("CICS");
//        datasetList = "SYS1.MACLIB@SYS1.MODGEN@MY.SYSLIB@LE.SCEESAMP@CICS.SDFHMAC@CICS.SDFHSAMP";
//        Assert.assertEquals("Error in buildCompileSyslib() method", datasetList, zosAssemblerProgramCompilerSpy.buildCompileSyslib(compileSyslibs, lePrefix, cicsPrefix));
//    }
//    
//    @Test
//    public void testBuildLinkSyslib() {
//        setupFormatDatasetConcatenationAnswer();
//        List<String> linkSyslibs = new LinkedList<>();
//        List<String> lePrefix = new LinkedList<>();
//        List<String> cicsPrefix = new LinkedList<>();
//        String datasetList = "";
//        Assert.assertEquals("Error in buildLinkSyslib() method", datasetList, zosAssemblerProgramCompilerSpy.buildLinkSyslib(linkSyslibs, lePrefix, cicsPrefix));
//
//        linkSyslibs.add("MY.SYSLIB");
//        lePrefix.add("LE");
//        cicsPrefix.add("CICS");
//        datasetList = "MY.SYSLIB@CICS.SDFHLOAD@LE.SCEELKEX@LE.SCEELKED";
//        Assert.assertEquals("Error in buildLinkSyslib() method", datasetList, zosAssemblerProgramCompilerSpy.buildLinkSyslib(linkSyslibs, lePrefix, cicsPrefix));
//    }
//    
//    @Test
//    public void getGetSkelName() {
//        Mockito.when(zosProgramMock.isCics()).thenReturn(false);
//        Assert.assertEquals("Error in getSkelName() method", "assemblerBatch.skel", zosAssemblerProgramCompilerSpy.getSkelName());
//        Mockito.when(zosProgramMock.isCics()).thenReturn(true);
//        Assert.assertEquals("Error in getSkelName() method", "assemblerCICS.skel", zosAssemblerProgramCompilerSpy.getSkelName());
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
//        Mockito.doAnswer(answer).when(zosAssemblerProgramCompilerSpy).formatDatasetConcatenation(Mockito.any());
//    }
}
