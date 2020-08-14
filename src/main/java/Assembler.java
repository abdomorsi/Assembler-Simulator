import java.util.ArrayList;
import java.util.HashMap;

public class Assembler {
    public static ArrayList<String> instructionList = new ArrayList<>();


////////////////////////////////// R-type //////////////////////////////////////////////////////////
    public static void add(String rd , String rs , String rt) {
        String opCode = "000000" ,shamt="00000", funct = binaryCalculator.binaryConverter(32,6);
        String instruction = opCode + rs + rt + rd + shamt + funct ;
        instructionList.add(instruction);
    }

    public static void sub(String rd , String rs , String rt) {
        String opCode = "000000" ,shamt="00000", funct = binaryCalculator.binaryConverter(34,6);
        String instruction = opCode + rs + rt + rd + shamt + funct ;
        instructionList.add(instruction);
    }

    public static void and(String rd , String rs , String rt) {
        String opCode = "000000" ,shamt="00000", funct = binaryCalculator.binaryConverter(36,6);
        String instruction = opCode + rs + rt + rd + shamt + funct ;
        instructionList.add(instruction);
    }

    public static void or(String rd , String rs , String rt) {
        String opCode = "000000" ,shamt="00000", funct = binaryCalculator.binaryConverter(37,6);
        String instruction = opCode + rs + rt + rd + shamt + funct ;
        instructionList.add(instruction);
    }

    public static void sll(String rd  ,String rt , int shamnt) {
        String opCode = "000000" ,shamt = binaryCalculator.binaryConverter(shamnt,5), funct = binaryCalculator.binaryConverter(0,6);
        String instruction = opCode + "00000" + rt + rd + shamt + funct ;
        instructionList.add(instruction);
    }

    public static void slt(String rd , String rs , String rt) {
        String opCode = "000000" ,shamt="00000", funct = binaryCalculator.binaryConverter(42,6);
        String instruction = opCode + rs + rt + rd + shamt + funct ;
        instructionList.add(instruction);
    }

    public static void jr(String rs) {
        String opCode = "000000" ,shamt="00000", funct = binaryCalculator.binaryConverter(8,6);
        String instruction = opCode + rs + "00000" +"00000" + shamt + funct ;
        System.out.println("inside jr assember : " + instruction) ;
        instructionList.add(instruction);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
                                        // I-type //
    /////////////////////////////////////////////////////////////////////////////////////////////
    public static void addi(String rs , String rt , int immediate) {
        String opCode = binaryCalculator.binaryConverter(8,6) , imm = binaryCalculator.binaryConverter(immediate,16);
        String instruction = opCode + rs + rt + imm ;
                System.out.println("ayman : " + instruction);

        instructionList.add(instruction);
    }

    public static void andi(String rs , String rt , int immediate) {
        String opCode = binaryCalculator.binaryConverter(12,6) , imm = binaryCalculator.binaryConverter(immediate,16);
        String instruction = opCode + rs + rt + imm ;
        
        instructionList.add(instruction);
    }

    public static void ori(String rs , String rt , int immediate) {
        String opCode = binaryCalculator.binaryConverter(13,6) , imm = binaryCalculator.binaryConverter(immediate,16);
        String instruction = opCode + rs + rt + imm ;
        instructionList.add(instruction);
    }

    public static void slti(String rs , String rt , int immediate) {
        String opCode =binaryCalculator. binaryConverter(10,6) , imm = binaryCalculator.binaryConverter(immediate,16);
        String instruction = opCode + rs + rt + imm ;
        instructionList.add(instruction);
    }

    public static void lui( String rt , int immediate) {
        String opCode = binaryCalculator.binaryConverter(13,6) , imm = binaryCalculator.binaryConverter(immediate,16);
        String instruction = opCode + "00000" + rt + imm ;
        instructionList.add(instruction);
    }

    public static void beq( String rs , String rt , int label) {
        String opCode = binaryCalculator.binaryConverter(4,6) ;
        String instruction = opCode + rs + rt + binaryCalculator.binaryConverter(label,16) ;
        instructionList.add(instruction);
    }

    public static void bne( String rs , String rt , int label) {
        String opCode = binaryCalculator.binaryConverter(5,6) ;
        String instruction = opCode + rs + rt + binaryCalculator.binaryConverter(label,16);
        instructionList.add(instruction);
    }


    public static void sw(String rs , String rt , int immediate) {
        String opCode = binaryCalculator.binaryConverter(43,6) , imm = binaryCalculator.binaryConverter(immediate,16);
        String instruction = opCode + rs + rt + imm ;
        instructionList.add(instruction);
    }

    public static void lw(String rs , String rt , int immediate) {
        String opCode = binaryCalculator.binaryConverter(35,6) , imm = binaryCalculator.binaryConverter(immediate,16);
        String instruction = opCode + rs + rt + imm ;
        instructionList.add(instruction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
                                        // J-type //
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static void j(int address) {
        String opCode = binaryCalculator.binaryConverter(2,6);
        String instruction = opCode + binaryCalculator.binaryConverter(address * 4,26);
        instructionList.add(instruction);
    }



    /*
    private static HashMap<String, String> mipsRegisters = new HashMap<>();

    static  String[] registersNames = {"0","at","v0","v1","a0","a1","a2","a3",
                                       "t0","t1","t2","t3","t4","t5","t6","t7",
                                       "s0","s1","s2","s3","s4","s5","s6","s7",
                                       "t8","t9","k0","k1","gp","sp","fp","ra"};

    private static void setMipsRegisters() {
        for (int i = 0; i < 32; i++) {
            mipsRegisters.put(registersNames[i],binaryConverter(i));
        }
    }
    */


    public static void main(String[] args) {
        //System.out.println(binaryConverter(5));

    }
}
