package app;

public enum OPCODE {
    add(0b100000), /* opcode = 0, funct = 32 */
    sub(0b100010), // opcode = 0, funct = 34
    and(0b100100), // opcode = 0, funct = 36
    or(0b100101), // opcode = 0, funct = 37
    slt(0b101010), // opcode = 0, funct = 42
    lw(0b100011), // opcode = 35
    sw(0b101011), // opcode = 43
    beq(0b000100), // opcode = 4
    bne(0b000101); // opcode = 5

    public final int bitfields;
    OPCODE(int bitfields) {
        this.bitfields = bitfields;
    }
}
