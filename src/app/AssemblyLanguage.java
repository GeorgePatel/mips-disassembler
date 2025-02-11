package app;

public class AssemblyLanguage {
    private String address;
    private String instruction;
    private final Input input = new Input();


    public String convertToAssembly(int hexInst) {
        int opcode = input.firstSixBits(hexInst);
        if (opcode == 0) {
            return convertToRFormat(hexInst);
        } else {
            return convertToIFormat(hexInst, opcode);
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

    private String convertToIFormat(int hexInst,int opcode) {
        IFormat iInst = new IFormat(opcode, input.firstRegister(hexInst), input.secondRegister(hexInst), input.offset(hexInst));
        String assemblyInst;
        switch (opcode) {
            case 0b100011: // lw
                assemblyInst = String.format("%s $%d, %d($%d)", OPCODE.lw, iInst.getDestRegister(), iInst.getOffset(), iInst.getSrcRegister());
                return assemblyInst;
            case 0b101011: // sw
                assemblyInst = String.format("%s $%d, %d($%d)", OPCODE.sw, iInst.getDestRegister(), iInst.getOffset(), iInst.getSrcRegister());
                return assemblyInst;
            case 0b000100: // beq
                break;
            case 0b000101: // bne
                break;
            default:
                return "Instruction not supported.";
        }
        return "";
    }
}
