package app;

public class RFormat {
    private final int opcode = 0; //6bits
    private int firstSrcOperand; //5bits
    private int secondSrcOperand; //5bits
    private int destOperand; //5bits
    private int shamt; // 5bits
    private int funct; // 6bits

    public RFormat(int firstSrcOperand, int secondSrcOperand, int destOperand, int shamt, int funct) {
        this.firstSrcOperand = firstSrcOperand;
        this.secondSrcOperand = secondSrcOperand;
        this.destOperand = destOperand;
        this.shamt = shamt;
        this.funct = funct;
    }

    public int getFirstSrcOperand() {
        return firstSrcOperand;
    }

    public void setFirstSrcOperand(int firstSrcOperand) {
        this.firstSrcOperand = firstSrcOperand;
    }

    public int getSecondSrcOperand() {
        return secondSrcOperand;
    }

    public void setSecondSrcOperand(int secondSrcOperand) {
        this.secondSrcOperand = secondSrcOperand;
    }

    public int getDestOperand() {
        return destOperand;
    }

    public void setDestOperand(int destOperand) {
        this.destOperand = destOperand;
    }

    public int getShamt() {
        return shamt;
    }

    public void setShamt(int shamt) {
        this.shamt = shamt;
    }

    public int getFunct() {
        return funct;
    }

    public void setFunct(int funct) {
        this.funct = funct;
    }

    public int getOpcode() {
        return opcode;
    }
}
