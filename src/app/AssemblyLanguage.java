package app;

public class AssemblyLanguage {
    private int address;
    private String instruction;
    private final Input input = new Input();
    private int hexInput;

    public AssemblyLanguage(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public String programOutput(int address, String instruction) {
        String pc_address = Integer.toHexString(address).toUpperCase();
        return String.format("%s %s", pc_address, instruction);
    }

    public String convertToAssembly(int hexInst) {
        int opcode = input.firstSixBits(hexInst);
        if (opcode == 0) {
            return convertToRFormat(hexInst);
        } else {
            return convertToIFormat(hexInst, opcode, getAddress());
        }
    }

    private String convertToRFormat(int hexInst) {
        int funct = input.lastSixBits(hexInst);
        RFormat rInst = new RFormat(input.firstRegister(hexInst), input.secondRegister(hexInst), input.thirdRegister(hexInst), 0, funct);
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
        IFormat iInst = new IFormat(opcode, input.firstRegister(hexInst), input.secondRegister(hexInst), input.offset(hexInst));
        String assemblyInst;
        int label = iInst.getOffset();
        String mylabel;
        switch (opcode) {
            case 0b100011: // lw
                assemblyInst = String.format("%s $%d, %d($%d)", OPCODE.lw, iInst.getDestRegister(), iInst.getOffset(), iInst.getSrcRegister());
                return assemblyInst;
            case 0b101011: // sw
                assemblyInst = String.format("%s $%d, %d($%d)", OPCODE.sw, iInst.getDestRegister(), iInst.getOffset(), iInst.getSrcRegister());
                return assemblyInst;
            case 0b000100: // beq
                label = label << 2; // convert the 18 bit offset to 16 bit offset
                label = label + 4; // account for pc increment
                label = label + pc_address; // to gain the final relative address that assembler needs to branch to
                mylabel = Integer.toHexString(label).toUpperCase();
                assemblyInst = String.format("%s $%d, $%d, address %s", OPCODE.beq, iInst.getSrcRegister(), iInst.getDestRegister(), mylabel);
                return assemblyInst;
            case 0b000101: // bne
                label = label << 2; // convert the 18 bit offset to 16 bit offset
                label = label + 4; // account for pc increment
                label = label + pc_address; // to gain the final relative address that assembler needs to branch to
                mylabel = Integer.toHexString(label).toUpperCase();
                assemblyInst = String.format("%s $%d, $%d, address %s", OPCODE.bne, iInst.getSrcRegister(), iInst.getDestRegister(), mylabel);
                return assemblyInst;
            default:
                return "Instruction not supported.";
        }
    }
}
