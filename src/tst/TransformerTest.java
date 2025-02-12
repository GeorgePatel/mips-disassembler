package tst;

import app.Transformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformerTest {

    private Transformer transformer;

    @BeforeEach
    public void init() {
        transformer = new Transformer();
    }

    @Test
    public void hex_opcode_firstSixBits() {
        int hex = 0x8CE90014; // 0x00A63820
        int opcode = transformer.firstSixBits(hex);
        assertEquals(0b100011, opcode); // 0b100011 == 35
    }

    @Test
    public void iFormat_srcReg_firstRegister() {
        int hex = 0x8CE90014;
        int srcReg1 = transformer.firstRegister(hex);
        assertEquals(0b00111, srcReg1); // 0b00111 == 7
    }

    @Test
    public void iFormat_destReg_secondRegister() {
        int hex = 0x8CE90014;
        int destReg = transformer.secondRegister(hex);
        assertEquals(0b01001, destReg); // 0b01001 == 9
    }

    @Test
    public void iFormat_posConstant_LastSixteenBits() {
        int hex = 0x8CE90014;
        int constant = transformer.offset(hex);
        assertEquals(0b0000000000010100, constant); // 0b0000000000010100 == 20
    }

    @Test
    public void iFormat_negConstant_LastSixteenBits() {
//        int hex = 0x8CE90014;
//        int constant = input.offset(hex);
//        assertEquals(0b0000000000010100, constant); // 0b0000000000010100 == 20
    }


    @Test
    public void rFormat_firstSrcReg_firstRegister() {
        int hex = 0x8CE90014;
        int srcReg1 = transformer.firstRegister(hex);
        assertEquals(0b00111, srcReg1); // 0b00111 == 7
    }

    @Test
    public void rFormat_secondSrcReg_secondRegister() {
        int hex = 0x8CE90014;
        int srcReg2 = transformer.secondRegister(hex);
        assertEquals(0b01001, srcReg2); // 0b01001 == 9
    }

    @Test
    public void rFormat_destReg_thirdRegister() {
        int hex = 0x00A63820;
        int destReg = transformer.thirdRegister(hex);
        assertEquals(0b00111, destReg); // 0b00111 == 7
    }

    @Test
    public void rFormat_functCode_LastSixBits() {
        int hex = 0x00A63820;
        int functCode = transformer.lastSixBits(hex);
        assertEquals(0b100000, functCode); // 0b100000 == 32
    }
}
