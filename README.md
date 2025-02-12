# **MIPS Disassembler**

### A simple MIPS disassembler that converts hexadecimal machine code into human-readable assembly instructions.

## **Overview**

This Java-based MIPS disassembler converts machine instructions into MIPS assembly language. It currently supports a subset of MIPS instructions, including:

- **R-format instructions:** `add`, `sub`, `and`, `or`, `slt`
- **I-format instructions:** `lw`, `sw`, `beq`, `bne`

Users can either:

1. **Use embedded test instructions** to disassemble a predefined set of MIPS machine codes.
2. **Enter hexadecimal MIPS instructions manually**, which will be validated and converted to assembly.

## **Features**

✔ Converts MIPS hexadecimal machine code to assembly.\
✔ Supports **R-format and I-format** instructions.\
✔ Validates user input to ensure correctness.\
✔ Displays instructions along with memory addresses.\
✔ Includes unit tests for reliability.

## **Getting Started**

### **Prerequisites**

- **Java 11 or later** (JDK)
- **JUnit 5** (for running unit tests)
- **An IDE (optional)** such as IntelliJ IDEA or Eclipse

### **Installation**

1. **Clone the repository**

   ```
   git clone https://github.com/yourusername/mips-disassembler.git
   cd mips-disassembler
   ```

2. **Compile the project**

   ```
   javac -d bin src/app/*.java
   ```

3. **Run the program**

   ```
   java -cp bin app.App
   ```

---

## **Usage**

### **Running the Disassembler**

The `App.java` file provides two modes:

- **Embedded MIPS Instructions**: A predefined set of MIPS machine code is disassembled.
- **User Input Mode** *(currently commented out)*: Users can enter hexadecimal instructions manually.

#### **Example Output**

```
9A040 add $7, $5, $6
9A044 lw $9, 20($7)
9A048 beq $21, $9, address 9A050
Unsupported instruction: 12345678
Invalid format! Please enter a valid hexadecimal MIPS instruction.
```

### **Adding Custom Instructions**

To enable **manual user input**, uncomment the following lines in `App.java`:

```java
int start_pc_address = 0x0;
int[] userInput_machine_instructions = getUserInputInstructions();
disassemble(userInput_machine_instructions, start_pc_address);
```

---

## **Code Structure**

```
/src
│── app/
│   ├── App.java               # Main entry point for the disassembler
│   ├── AssemblyLanguage.java  # Handles instruction conversion logic
│   ├── Transformer.java       # Extracts fields from machine code
│   ├── RFormat.java           # Represents R-format instructions
│   ├── IFormat.java           # Represents I-format instructions
│   ├── OPCODE.java            # Enum for supported opcodes and functions
│── tst/
│   ├── AssemblyLanguageTest.java  # Unit tests for instruction conversion
│   └── TransformerTest.java       # Unit tests for binary extraction logic
```

## **Testing**

This project includes **JUnit 5** tests for instruction conversion and binary extraction.

### **Run Tests**

```
javac -cp "lib/*:bin" -d bin tst/*.java
java -cp "lib/*:bin" org.junit.platform.console.ConsoleLauncher --select-package tst
```

or run tests inside your **IDE’s test runner**.

---

## **Supported Instructions**

### **R-Format**

| Instruction | Function Code   |
| ----------- | --------------- |
| add         | `100000` (0x20) |
| sub         | `100010` (0x22) |
| and         | `100100` (0x24) |
| or          | `100101` (0x25) |
| slt         | `101010` (0x2A) |

### **I-Format**

| Instruction | Opcode          |
| ----------- | --------------- |
| lw          | `100011` (0x23) |
| sw          | `101011` (0x2B) |
| beq         | `000100` (0x04) |
| bne         | `000101` (0x05) |

---

## **Future Improvements**

🔹 Support for **additional MIPS instructions**.\
🔹 More user-friendly **error handling**.\
🔹 Option to **save disassembled output to a file**.

---

## **Contributing**

Contributions are welcome! Please follow these steps:

1. **Fork the repository**.
2. **Create a new branch** for your feature/fix.
   ```
   git checkout -b feature-new-instruction
   ```
3. **Commit and push your changes**.
   ```
   git commit -m "Added support for new instruction"
   git push origin feature-new-instruction
   ```
4. **Submit a Pull Request**.

---

## **License**

This project is licensed under the **MIT License**.

