package tst;

import app.AssemblyLanguage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssemblyLanguageTest {
    private AssemblyLanguage assemblyLanguage;

    @BeforeEach
    public void init() {
        assemblyLanguage = new AssemblyLanguage(0x00000000);
    }

    @Test
    public void rFormatHex_ADD_convertedToAssembly() { // correct assembly language for add instruction
        int instruction = 0x00A63820;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("add $7, $5, $6", result);
    }

    @Test
    public void rFormatHex_SUB_convertedToAssembly() { // correct assembly language for sub instruction
        int instruction = 0x022DA822;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("sub $21, $17, $13", result);
    }

    @Test
    public void rFormatHex_AND_convertedToAssembly() { // correct assembly language for add instruction
        int instruction = 0x02697824;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("and $15, $19, $9", result);
    }

    @Test
    public void rFormatHex_OR_convertedToAssembly() { // correct assembly language for OR instruction
        int instruction = 0x02A4A825;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("or $21, $21, $4", result);
    }

    @Test
    public void rFormatHex_SLT_convertedToAssembly() { // correct assembly language for slt instruction
        int instruction = 0x0274402A;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("slt $8, $19, $20", result);
    }

    @Test
    public void iFormatHex_LW_convertedToAssembly() { // correct assembly language for lw instruction
        int instruction = 0x8CE90014;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("lw $9, 20($7)", result);
    }
    @Test
    public void iFormatHex_posOffsetLW_convertedToAssembly() { // correct assembly language for lw instruction with positive offset
        int instruction = 0x8D070004;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("lw $7, 4($8)", result);
    }

    @Test
    public void iFormatHex_posOffsetSW_convertedToAssembly() { // correct assembly language for sw instruction
        int instruction = 0xADB30020;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("sw $19, 32($13)", result);
    }

    @Test
    public void iFormatHex_negOffsetSW_convertedToAssembly() { // correct assembly language for sw instruction with negative offset
        int instruction = 0xAE8FFFF4;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("sw $15, -12($20)", result);
    }

    @Test
    public void iFormatHex_lectureExampleSW_convertedToAssembly() {
        int instruction = 0xAD09FFFC;
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("sw $9, -4($8)", result);
    }

    @Test
    public void iFormatHex_BEQ_convertedToAssembly() { // correct assembly language for beq instruction
        // 0x10640006, 0x10E30014, 0x00238E00
        int instruction = 0x12A90003; // offset is 0x3
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("beq $21, $9, address 10", result); // PC is set to 0x0 before each test case
    }

    @Test
    public void iFormatHex_BNE_convertedToAssembly() { // correct assembly language for bne instruction
        int instruction = 0x158FFFF7; // offset is -9 or 0xFFF7
        String result = assemblyLanguage.convertToAssembly(instruction);
        assertEquals("bne $12, $15, address FFE0", result); // PC is set to 0x0 before each test case
    }
}
