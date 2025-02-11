package tst;

import app.AssemblyLanguage;
import app.Input;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssemblyLanguageTest {
    private Input input;
    private AssemblyLanguage assemblyLanguage;

    @BeforeEach
    public void init() {
        input = new Input();
        assemblyLanguage = new AssemblyLanguage();
    }

    @Test
    public void ADD_rFormatHex_convertedToAssembly() {
        // correct assembly language for add instruction
        int instruction = 0x00A63820;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("add $7, $5, $6", result);
//        assertEquals(result, "9A040 add $7, $5, $6");
    }

//    @Test
//    public void SUB_rFormatHex_convertedToAssembly() {
//        // correct assembly language for add instruction
//        int instruction = 0x00A63820;
//        String result = assemblyLanguage.convertToAssembly(instruction);
//        assertEquals("ADD $7, $5, $6", result);
////        assertEquals(result, "9A040 add $7, $5, $6");
//    }
//
//    @Test
//    public void AND_rFormatHex_convertedToAssembly() {
//        // correct assembly language for add instruction
//        int instruction = 0x00A63820;
//        String result = assemblyLanguage.convertToAssembly(instruction);
//        assertEquals("ADD $7, $5, $6", result);
////        assertEquals(result, "9A040 add $7, $5, $6");
//    }
//
//    @Test
//    public void OR_rFormatHex_convertedToAssembly() {
//        // correct assembly language for add instruction
//        int instruction = 0x00A63820;
//        String result = assemblyLanguage.convertToAssembly(instruction);
//        assertEquals("ADD $7, $5, $6", result);
////        assertEquals(result, "9A040 add $7, $5, $6");
//    }
//
//    @Test
//    public void SLT_rFormatHex_convertedToAssembly() {
//        // correct assembly language for add instruction
//        int instruction = 0x00A63820;
//        String result = assemblyLanguage.convertToAssembly(instruction);
//        assertEquals("ADD $7, $5, $6", result);
////        assertEquals(result, "9A040 add $7, $5, $6");
//    }
}
