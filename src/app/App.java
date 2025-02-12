package app;

public class App {


    public static void main(String[] args) {
        int[] machine_instructions = {0x032BA020, 0x8CE90014, 0x12A90003, 0x022DA822, 0xADB30020, 0x02697824, 0xAE8FFFF4,
                0x018C6020, 0x02A4A825, 0x158FFFF7, 0x8ECDFFF0};

        embedded(machine_instructions);

    }

    private static void embedded(int[] machine_instructions) {
        int pc_address = 0x0009A040; // the first instruction begins at address hex 9A040
        AssemblyLanguage disassembler;

        for (int instruction: machine_instructions) {
            disassembler = new AssemblyLanguage(pc_address);
            String assembly = disassembler.convertToAssembly(instruction);
            String output = disassembler.programOutput(pc_address, assembly);
            System.out.println(output);
            pc_address+=4;
        }
    }
}
