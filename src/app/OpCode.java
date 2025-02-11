package app;

public enum OpCode {
    final int ADD = 0b100000; // opcode = 0, funct = 32
    final int SUB = 0b100010; // opcode = 0, funct = 34
    final int AND = 0b100100; // opcode = 0, funct = 36
    final int OR = 0b100101; // opcode = 0, funct = 37
    final int SLT = 0b101010; // opcode = 0, funct = 42
    final int LW = 0b100011; // opcode = 35
    final int SW = 0b101011; // opcode = 43
    final int BEQ = 0b000100; // opcode = 4
    final int BNE = 0b000101; // opcode = 5
}
