package app;

public class AssemblyLanguage {
    private int hexInput;
    private int PC_address;
    private String instruction;
    private final Transformer transformer = new Transformer();

    public AssemblyLanguage(int PC_address) {
        this.PC_address = PC_address;
    }

    public int getPC_address() {
        return PC_address;
    }

    public void setPC_address(int PC_address) {
        this.PC_address = PC_address;
    }

    public String programOutput(int address, String instruction) {
        String pc_address = Integer.toHexString(address).toUpperCase();
        return String.format("%s %s", pc_address, instruction);
    }

    public String convertToAssembly(int hexInst) {
        int opcode = transformer.firstSixBits(hexInst);
        if (opcode == 0) {
            return convertToRFormat(hexInst);
        } else {
            return convertToIFormat(hexInst, opcode, getPC_address());
        }
    }

    private String convertToRFormat(int hexInst) {
        int funct = transformer.lastSixBits(hexInst);
        RFormat rInst = new RFormat(transformer.firstRegister(hexInst), transformer.secondRegister(hexInst), transformer.thirdRegister(hexInst), 0, funct);
        String assemblyInst;
        return switch (funct) {
            case 0b100000 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.add, rInst.getDestOperand(), rInst.getFirstSrcOperand(), rInst.getSecondSrcOperand());
                yield assemblyInst;
            }
            case 0b100010 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.sub, rInst.getDestOperand(), rInst.getFirstSrcOperand(), rInst.getSecondSrcOperand());
                yield assemblyInst;
            }
            case 0b100100 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.and, rInst.getDestOperand(), rInst.getFirstSrcOperand(), rInst.getSecondSrcOperand());
                yield assemblyInst;
            }
            case 0b100101 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.or, rInst.getDestOperand(), rInst.getFirstSrcOperand(), rInst.getSecondSrcOperand());
                yield assemblyInst;
            }
            case 0b101010 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.slt, rInst.getDestOperand(), rInst.getFirstSrcOperand(), rInst.getSecondSrcOperand());
                yield assemblyInst;
            }
            default -> "Instruction not supported.";
        };
    }

    private String convertToIFormat(int hexInst,int opcode, int pc_address) {
        IFormat iInst = new IFormat(opcode, transformer.firstRegister(hexInst), transformer.secondRegister(hexInst), transformer.offset(hexInst));
        int offset = iInst.getOffset();
        String assemblyInst;
        String label;
        switch (opcode) {
            case 0b100011: // lw
                assemblyInst = String.format("%s $%d, %d($%d)", OPCODE.lw, iInst.getDestRegister(), iInst.getOffset(), iInst.getSrcRegister());
                return assemblyInst;
            case 0b101011: // sw
                assemblyInst = String.format("%s $%d, %d($%d)", OPCODE.sw, iInst.getDestRegister(), iInst.getOffset(), iInst.getSrcRegister());
                return assemblyInst;
            case 0b000100: // beq
                offset = (offset << 2); // convert the 18 bit offset to 16 bit offset
                offset = (offset + pc_address + 4); // account for pc increment
                if (offset < 0)
                    offset = offset & 0xFFFF;
                label = Integer.toHexString(offset).toUpperCase();
                assemblyInst = String.format("%s $%d, $%d, address %s", OPCODE.beq, iInst.getSrcRegister(), iInst.getDestRegister(), label);
                return assemblyInst;
            case 0b000101: // bne
                offset = (offset << 2); // convert the 18 bit offset to 16 bit offset
                offset = (offset + pc_address + 4); // account for pc increment
                if (offset < 0)
                    offset = offset & 0xFFFF;
                label = Integer.toHexString(offset).toUpperCase();
                assemblyInst = String.format("%s $%d, $%d, address %s", OPCODE.bne, iInst.getSrcRegister(), iInst.getDestRegister(), label);
                return assemblyInst;
            default:
                return "Instruction not supported.";
        }
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setHexInput(int hexInput) {
        this.hexInput = hexInput;
    }
}
