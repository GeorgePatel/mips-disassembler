package app;

public class IFormat {
    int opcode; //6bits
    int srcRegister; // 5bits
    int destRegister; // 5bits
    short offset; // 16bits

    public IFormat(int opcode, int srcRegister, int destRegister, short offset) {
        this.opcode = opcode;
        this.srcRegister = srcRegister;
        this.destRegister = destRegister;
        this.offset = offset;
    }

    public int getOpcode() {
        return opcode;
    }

    public void setOpcode(int opcode) {
        this.opcode = opcode;
    }

    public int getSrcRegister() {
        return srcRegister;
    }

    public void setSrcRegister(int srcRegister) {
        this.srcRegister = srcRegister;
    }

    public int getDestRegister() {
        return destRegister;
    }

    public void setDestRegister(int destRegister) {
        this.destRegister = destRegister;
    }

    public short getOffset() {
        return offset;
    }

    public void setOffset(short offset) {
        this.offset = offset;
    }
}
