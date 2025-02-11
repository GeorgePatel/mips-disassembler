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
            convertToIFormat(hexInst, opcode);
        }

        return "";
    }

    private String convertToRFormat(int hexInst) {
        int funct = input.lastSixBits(hexInst);
        String assemblyInst;
        int firstSrcOperand = input.firstRegister(hexInst);
        int secondSrcOperand = input.secondRegister(hexInst);
        int destOperand = input.thirdRegister(hexInst);
        return switch (funct) {
            case 0b100000 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.add, destOperand, firstSrcOperand, secondSrcOperand);
                yield assemblyInst;
            }
            case 0b100010 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.sub, destOperand, firstSrcOperand, secondSrcOperand);
                yield assemblyInst;
            }
            case 0b100100 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.and, destOperand, firstSrcOperand, secondSrcOperand);
                yield assemblyInst;
            }
            case 0b100101 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.or, destOperand, firstSrcOperand, secondSrcOperand);
                yield assemblyInst;
            }
            case 0b101010 -> {
                assemblyInst = String.format("%s $%d, $%d, $%d", OPCODE.slt, destOperand, firstSrcOperand, secondSrcOperand);
                yield assemblyInst;
            }
            default -> "Instruction not supported.";
        };
    }

    private String convertToIFormat(int hexInst,int opcode) {
        return "";
    }
}
