/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.LinkedHashMap;

public class InstructionDefiner {
    
    public static HashMap<String,String> getInstructionParts(String instructionMachineCode)
    {
        HashMap<String,String> parts = new LinkedHashMap<>();
        int opCode = Integer.parseInt(instructionMachineCode.substring(0,6),2);
        switch (opCode) {
            case 0:
                parts.put("Type", "R");
                parts.put("OP", Integer.toBinaryString(opCode));
                parts.put("rs", instructionMachineCode.substring(6,11));
                parts.put("rt", instructionMachineCode.substring(11,16));
                parts.put("rd", instructionMachineCode.substring(16,21));
                parts.put("shamt", instructionMachineCode.substring(21,26));
                parts.put("func", instructionMachineCode.substring(26));
                break;
            case 2:
                parts.put("Type", "I");
                parts.put("rs", instructionMachineCode.substring(6,11));
                parts.put("rt", instructionMachineCode.substring(11,16));  
                parts.put("Immediate", instructionMachineCode.substring(16));
                break;
            default:
                parts.put("Type", "J");
                parts.put("Address", instructionMachineCode.substring(6));
                break;
        }
        return parts;
    }
}
