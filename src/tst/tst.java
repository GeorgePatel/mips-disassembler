package tst;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class tst {
    @Test
    public void rFormat_ADD_srcInstProduced() {
        // correct assembly language for add instruction
        int instruction = 0x00A63820;
        String result = App.convertToAssembly(instruction);
        assertEquals(result, "9A040 add $7, $5, $6");
    }

    @Test
    public void rFormat_SUB_srcInstProduced() {
        // correct assembly language for sub instruction

    }

    @Test
    public void iFormat_AND_srcInstProduced() {
        // correct assembly language for and instruction

    }

    @Test
    public void iFormat_OR_srcInstProduced() {
        // correct assembly language for or instruction

    }

    @Test
    public void iFormat_SLT_srcInstProduced() {
        // correct assembly language for slt instruction

    }

    @Test
    public void iFormat_loadWord_srcInstProduced() {
        // correct assembly instruction for load word
        int instruction = 0x8D070004;
        String result = App.convertToAssembly(instruction);
        assertEquals(result, "9A044 lw $7, 4($8)");
    }

    @Test
    public void iFormat_storeWord_srcInstProduced() {
        // correct assembly instruction for store word
        int instruction = 0xAD09FFFC;
        String result = App.convertToAssembly(instruction);
        assertEquals(result, "9A048 sw $9, -4($8)");
    }

    @Test
    public void iFormat_BEQ_srcInstProduced() {
        // correct branch assembly instruction for branch on equal
    }

    @Test
    public void iFormat_BNE_srcInstProduced() {
        // correct branch assembly instruction for branch not on equal
    }

}
