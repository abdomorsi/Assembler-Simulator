public class Excecution {

    private static int programCounter = 0;
    public static int getProgramCounter()
    {
        return programCounter;
    }
    public static void resetProgramCounter()
    {
        programCounter = 0;
    }
    public static void excecuteUntilHalt()
    {
        String instructionMachineCode = MemoryController.getValueFromAddressWord(programCounter);
        while(instructionMachineCode != null && Integer.parseUnsignedInt(instructionMachineCode,2) != 0)
        {
            excecuteInstruction(instructionMachineCode);
            System.out.println("inside until halt : programCounter = " + programCounter);
            instructionMachineCode = MemoryController.getValueFromAddressWord(programCounter);
        }
    }
    public static void excecuteInstruction(String instructionMachineCode)
    {
        if(instructionMachineCode == null) return;
        System.out.println(instructionMachineCode + "  hi morsy");
        int opCode = Integer.parseInt(instructionMachineCode.substring(0,6),2);
        System.out.println("opCode : " + opCode);
        switch (opCode) {
            case 0:
                excecuteRtypeInstruction(instructionMachineCode);
                break;
            case 2:
                excecuteJtypeInstruction(instructionMachineCode);
                break;
            default:
                excecuteItypeInstruction(instructionMachineCode, opCode);
                break;
        }
        programCounter++;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
                                        // R-type //
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static void excecuteRtypeInstruction(String instructionMachineCode)
    {
        String sRegister = instructionMachineCode.substring(6,11);
        String tRegister = instructionMachineCode.substring(11,16);
        String disRegister = instructionMachineCode.substring(16,21);
        String shamt = instructionMachineCode.substring(21,26);
        String funcCode = instructionMachineCode.substring(26);
        int functionCode = Integer.parseUnsignedInt(funcCode,2);
        System.out.println(instructionMachineCode);
        System.out.println("funccc code " + instructionMachineCode.substring(26));
        if(functionCode == 32)
            excecuteAddInstruction(sRegister, tRegister, disRegister);
        else if(functionCode == 34)
            excecuteSubInstruction(sRegister, tRegister, disRegister);
        else if(functionCode == 36)
            excecuteAndInstruction(sRegister, tRegister, disRegister);
        else if(functionCode == 37)
            excecuteOrInstruction(sRegister, tRegister, disRegister);
        else if(functionCode == 0)
            excecuteSllInstruction(sRegister, shamt, disRegister);
        else if(functionCode == 42)
            excecuteSltInstruction(sRegister, tRegister, disRegister);
        else if(functionCode == 8)
            excecuteJrInstruction(sRegister);
    }



    public static void excecuteAddInstruction(String sRegister, String tRegister, String disRegister)
    {
        int result = RegistersController.getRegisterValue(sRegister) + RegistersController.getRegisterValue(tRegister);
        System.out.println(sRegister + " " + tRegister + " " + disRegister + " " + result);
        RegistersController.setRegisterValue(result,disRegister);
    }
    public static void excecuteSubInstruction(String sRegister, String tRegister, String disRegister)
    {
        int result = RegistersController.getRegisterValue(sRegister) - RegistersController.getRegisterValue(tRegister);
        RegistersController.setRegisterValue(result,disRegister);
    }
    public static void excecuteAndInstruction(String sRegister, String tRegister, String disRegister)
    {
        int result = RegistersController.getRegisterValue(sRegister) & RegistersController.getRegisterValue(tRegister);
        RegistersController.setRegisterValue(result,disRegister);
    }
    public static void excecuteOrInstruction(String sRegister, String tRegister, String disRegister)
    {
        int result = RegistersController.getRegisterValue(sRegister) | RegistersController.getRegisterValue(tRegister);
        RegistersController.setRegisterValue(result,disRegister);
    }
    public static void excecuteSllInstruction(String sRegister, String shamt, String disRegister)
    {
        int result = RegistersController.getRegisterValue(sRegister) << Integer.parseInt(shamt, 2);
        RegistersController.setRegisterValue(result,disRegister);
    }
    public static void excecuteSltInstruction(String sRegister, String tRegister, String disRegister)
    {
        int result;
          if(RegistersController.getRegisterValue(sRegister) < RegistersController.getRegisterValue(tRegister))
              result = 1;
          else
              result = 0;
        RegistersController.setRegisterValue(result,disRegister);
    }
    public static void excecuteJrInstruction(String sRegister)
    {
        programCounter = RegistersController.getRegisterValue(sRegister) / 4 - 1;
        System.out.println("inside jr : " + programCounter);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
                                    // I-type //
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static void excecuteItypeInstruction(String instructionMachineCode, int opCode)
    {
        String sourceRegister = instructionMachineCode.substring(6,11);
        String destinationRegister = instructionMachineCode.substring(11,16);
        System.out.println(instructionMachineCode.substring(16));
        System.out.println(Integer.parseInt(instructionMachineCode.substring(16),2));
        int immediate = (short)Integer.parseInt(instructionMachineCode.substring(16),2);
        System.out.print(opCode);
        if(opCode == 8)
            excecuteAddiInstruction(sourceRegister, destinationRegister, immediate);
        else if(opCode == 12)
            excecuteAndiInstruction(sourceRegister, destinationRegister, immediate);
        else if(opCode == 10)
            excecuteSltiInstruction(sourceRegister, destinationRegister, immediate);
        else if(opCode == 13)
            excecuteOriInstruction(sourceRegister, destinationRegister, immediate);
        else if(opCode == 14)
            excecuteLuiInstruction(destinationRegister, immediate);
        else if(opCode == 4)
            excecuteBeqInstruction(sourceRegister, destinationRegister, immediate);
        else if(opCode == 5)
            excecuteBneInstruction(sourceRegister, destinationRegister, immediate);
        else if(opCode == 35)
            excecuteLwInstruction(sourceRegister, destinationRegister, immediate);
        else if(opCode == 43)
            excecuteSwInstruction(sourceRegister, destinationRegister, immediate);
    }
    public static void excecuteAddiInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        System.out.println("hi wael elnegm " + immediate + " " + sourceRegister + " " + destinationRegister);
        int result = RegistersController.getRegisterValue(sourceRegister) + immediate;
        System.out.println("hi seif : " + result);
        RegistersController.setRegisterValue(result,destinationRegister);
    }
    public static void excecuteAndiInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        int result = RegistersController.getRegisterValue(sourceRegister) & immediate;
        RegistersController.setRegisterValue(result,destinationRegister);
    }
    public static void excecuteSltiInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        int result;
        if(RegistersController.getRegisterValue(sourceRegister) < immediate)
            result = 1;
        else
            result = 0;
        RegistersController.setRegisterValue(result,destinationRegister);
    }
    public static void excecuteOriInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        int result = RegistersController.getRegisterValue(sourceRegister) | immediate;
        RegistersController.setRegisterValue(result,destinationRegister);
    }
    public static void excecuteLuiInstruction(String destinationRegister, int immediate)
    {
        String result = "";
        for(int i = 0;i < 16;i++)
            result += '0';
        result = binaryCalculator.binaryConverter(immediate,16) + result;
        RegistersController.setRegisterValue(Integer.parseInt(result,2),destinationRegister);
    }
    public static void excecuteBeqInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        if(RegistersController.getRegisterValue(sourceRegister) == RegistersController.getRegisterValue(destinationRegister));
            programCounter = immediate / 4 - 1;
    }
    public static void excecuteBneInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        if(RegistersController.getRegisterValue(sourceRegister) != RegistersController.getRegisterValue(destinationRegister));
        programCounter = immediate / 4 - 1;
    }
    public static void excecuteLwInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        int result = (int)Long.parseLong(MemoryController.getValueFromAddress(immediate + RegistersController.getRegisterValue(sourceRegister)),2);
        RegistersController.setRegisterValue(result,destinationRegister);
    }
    public static void excecuteSwInstruction(String sourceRegister, String destinationRegister, int immediate)
    {
        int result = RegistersController.getRegisterValue(destinationRegister);
        System.out.println("inside sw exc : result =  " + result + " and source + immediate = " + RegistersController.getRegisterValue(sourceRegister) + immediate);
        int x = RegistersController.getRegisterValue(sourceRegister) + immediate;
        System.out.println("heeey : " + (5004 + (-4096) ) );
        MemoryController.setValueForAddress(result,x);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
                                    // J-type //
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static void excecuteJtypeInstruction(String instructionMachineCode)
    {
        String address = instructionMachineCode.substring(6);
        System.out.println("address of jumb : " + address);
        programCounter = (Integer.parseInt(address,2)) / 4;
        programCounter--;
    }
}
/*
addi $s1, $s1, 5
addi $s2, $s2, 5
beq $s1, $s2, hi
j hey
hi:
add $s2, $s2, $s2
hey:
addi $s3, $s3, 10

 */
/*
addi $s1, $s1, 5
addi $s2, $s2, 10
j loop
addi $s3, $s3, 15
loop:
addi $s4, $s4, -1
*/