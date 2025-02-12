package app;

public class Transformer {
    int hex;

    public int firstSixBits(int hexInst) {
        int opcode = hexInst >>> 26;
        return opcode;
    }

    public int firstRegister(int hexInst) {
        int bitmask = 0b00000011111000000000000000000000;
        int reg1 = hexInst & bitmask;
        reg1 = reg1 >>> 21;
        return reg1;
    }

    public int secondRegister(int hexInst) {
        int bitmask = 0b00000000000111110000000000000000;
        int reg2 = hexInst & bitmask;
        reg2 = reg2 >>> 16;
        return reg2;
    }

    public int thirdRegister(int hexInst) {
        int bitmask = 0b00000000000000001111100000000000;
        int reg3 = hexInst & bitmask;
        reg3 = reg3 >>> 11;
        return reg3;
    }

    public int lastSixBits(int hexInst) {
        int bitmask = 0b00000000000000000000000000111111;
        int funct = hexInst & bitmask;
        return funct;
    }

    public short offset(int hexInst) {
        int bitmask = 0b00000000000000001111111111111111;
        short offset = (short) (hexInst & bitmask);
        return offset;
    }
}
