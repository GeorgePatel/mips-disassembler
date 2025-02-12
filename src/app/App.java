package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Transformer transformer = new Transformer();

    public static void main(String[] args) {
        int project_pc_address = 0x0009A040; // the first instruction begins at address hex 9A040
        int[] embedded_machine_instructions = {0x032BA020, 0x8CE90014, 0x12A90003, 0x022DA822, 0xADB30020, 0x02697824, 0xAE8FFFF4,
                0x018C6020, 0x02A4A825, 0x158FFFF7, 0x8ECDFFF0};
//        int start_pc_address = 0x0;
//        int[] userInput_machine_instructions = getUserInputInstructions();
        disassemble(embedded_machine_instructions, project_pc_address);
//        disassemble(userInput_machine_instructions, start_pc_address);
    }

    private static void disassemble(int[] machine_instructions, int pc_address) {
        AssemblyLanguage disassembler;

        for (int instruction: machine_instructions) {
            disassembler = new AssemblyLanguage(pc_address);
            String assembly = disassembler.convertToAssembly(instruction);
            String output = disassembler.programOutput(pc_address, assembly);
            System.out.println(output);
            pc_address+=4;
        }
    }

    private static int[] getUserInputInstructions() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> instructionList = new ArrayList<>();

        System.out.println("Enter hexadecimal MIPS instructions (one per line). Type 'done' to finish:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int hexInstruction = Integer.parseUnsignedInt(input, 16);
                if (isSupportedInstruction(hexInstruction)) {
                    instructionList.add(hexInstruction);
                } else {
                    System.out.println("Unsupported instruction: " + input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid format! Please enter a valid hexadecimal MIPS instruction.");
            }
        }

        scanner.close();

        return instructionList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static boolean isSupportedInstruction(int hexInst) {
        int opcode = transformer.firstSixBits(hexInst);

        if (opcode == 0) { // R-format instructions
            int funct = transformer.lastSixBits(hexInst);
            return funct == OPCODE.add.bitfields ||
                    funct == OPCODE.sub.bitfields ||
                    funct == OPCODE.and.bitfields ||
                    funct == OPCODE.or.bitfields ||
                    funct == OPCODE.slt.bitfields;
        } else { // I-format instructions
            return opcode == OPCODE.lw.bitfields ||
                    opcode == OPCODE.sw.bitfields ||
                    opcode == OPCODE.beq.bitfields ||
                    opcode == OPCODE.bne.bitfields;
        }
    }
}
