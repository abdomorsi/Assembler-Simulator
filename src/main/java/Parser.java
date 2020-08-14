import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Parser {

    private static HashMap<String, String> mipsRegisters;
    private static HashMap<String, Integer> labels  = new HashMap<>();
    public static ArrayList<Integer> labelsIndexes = new ArrayList<>();
    static  String[] registersNames = {"$0","$at","$v0","$v1","$a0","$a1","$a2","$a3",
            "$t0","$t1","$t2","$t3","$t4","$t5","$t6","$t7",
            "$s0","$s1","$s2","$s3","$s4","$s5","$s6","$s7",
            "$t8","$t9","$k0","$k1","$gp","$sp","$fp","$ra"};
    private static String notRegister = "5";
    Parser()
    {
        
    }
    public static void setMipsRegisters() {
        mipsRegisters = new HashMap<>();
        for (int i = 0; i < 32; i++) {
            System.out.println("inside mips : " + registersNames[i] + "  " + binaryCalculator.binaryConverter(i,5) );
            mipsRegisters.put(registersNames[i],binaryCalculator.binaryConverter(i,5));
        }
    }
    private static boolean isMemoryAddress(String argument)
    {
        if(argument.indexOf('(') == -1 || argument.indexOf(')') == -1 || argument.length() < 5 || argument.indexOf(')') + 1 != argument.length() )
            return false;

        if(getImmediateFromMemoryAddress(argument) != 30000 && !getRegisterFromMemoryAddress(argument).equals("5"))
            return true;
        else
            return false;
    }
    private static String getRegisterFromMemoryAddress(String argument)
    {
        argument = argument.substring(argument.indexOf('(') + 1, argument.indexOf(')'));
        if(!getRegisterAddress(argument).equals("5"))
            return argument;
        else
            return "5";
    }
    private static int getImmediateFromMemoryAddress(String argument)
    {
        argument = argument.substring(0, argument.indexOf('('));
        if(argument.matches("-?\\d+"))
            return Integer.parseInt(argument);
        else
            return 30000;
    }
    public static String getRegisterAddress(String input)
    {
        System.out.println("hi " + input);
        if(mipsRegisters.get(input) != null)
            return mipsRegisters.get(input);
        else
            return "5";
    }
    public static boolean parse(String instruction, String[] arguments){

        if(instruction.equals("add") && arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && !getRegisterAddress(arguments[2]).equals(notRegister))
            Assembler.add(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),getRegisterAddress(arguments[2]));
        else if(instruction.equals("addi") && arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && arguments[2].matches("-?\\d+"))
            Assembler.addi(getRegisterAddress(arguments[1]),getRegisterAddress(arguments[0]),Integer.parseInt(arguments[2]));
        else if(instruction.equals("sub") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && !getRegisterAddress(arguments[2]).equals(notRegister))
            Assembler.sub(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),getRegisterAddress(arguments[2]));
        else if(instruction.equals("and") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && !getRegisterAddress(arguments[2]).equals(notRegister))
            Assembler.and(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),getRegisterAddress(arguments[2]));
        else if(instruction.equals("andi") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && arguments[2].matches("-?\\d+"))
            Assembler.andi(getRegisterAddress(arguments[1]),getRegisterAddress(arguments[0]),Integer.parseInt(arguments[2]));
        else if(instruction.equals("or") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && !getRegisterAddress(arguments[2]).equals(notRegister))
            Assembler.or(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),getRegisterAddress(arguments[2]));
        else if(instruction.equals("ori") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && arguments[2].matches("-?\\d+"))
            Assembler.ori(getRegisterAddress(arguments[1]),getRegisterAddress(arguments[0]),Integer.parseInt(arguments[2]));
        else if(instruction.equals("sll") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && arguments[2].matches("-?\\d+"))
            Assembler.sll(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),Integer.parseInt(arguments[2]));
        else if(instruction.equals("slt") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && !getRegisterAddress(arguments[2]).equals(notRegister))
            Assembler.slt(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),getRegisterAddress(arguments[2]));
        else if(instruction.equals("slti") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && arguments[2].matches("-?\\d+"))
            Assembler.slti(getRegisterAddress(arguments[1]),getRegisterAddress(arguments[0]),Integer.parseInt(arguments[2]));
        else if(instruction.equals("lui") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && arguments[1].matches("-?\\d+"))
            Assembler.lui(getRegisterAddress(arguments[0]),Integer.parseInt(arguments[1]));
        else if(instruction.equals("jr") &&  arguments.length == 1 && !getRegisterAddress(arguments[0]).equals(notRegister))
            Assembler.jr(getRegisterAddress(arguments[0]));
        else if(instruction.equals("j") &&  arguments.length == 1 && labels.get(arguments[0]) != null)
            Assembler.j(labels.get(arguments[0]));
        else if(instruction.equals("beq") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && labels.get(arguments[2]) != null  )
            Assembler.beq(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),labels.get(arguments[2]));
        else if(instruction.equals("bne") &&  arguments.length == 3 && !getRegisterAddress(arguments[0]).equals(notRegister) && !getRegisterAddress(arguments[1]).equals(notRegister) && labels.get(arguments[2]) != null  )
            Assembler.bne(getRegisterAddress(arguments[0]),getRegisterAddress(arguments[1]),labels.get(arguments[2]));
        else if(instruction.equals("lw") &&  arguments.length == 2 && !getRegisterAddress(arguments[0]).equals(notRegister) && isMemoryAddress(arguments[1])  )
            Assembler.lw(getRegisterAddress(getRegisterFromMemoryAddress(arguments[1])),getRegisterAddress(arguments[0]),getImmediateFromMemoryAddress(arguments[1]));
        else if(instruction.equals("sw") &&  arguments.length == 2 && !getRegisterAddress(arguments[0]).equals(notRegister) && isMemoryAddress(arguments[1])  )
            Assembler.sw(getRegisterAddress(getRegisterFromMemoryAddress(arguments[1])),getRegisterAddress(arguments[0]),getImmediateFromMemoryAddress(arguments[1]));
        else
            return false;

        return true;
    }
    public static boolean parse(String content, int j)
    {
       // System.out.println( (content.indexOf(':') + 1 ) + " " + content.length())
        System.out.println(labelsIndexes.contains(j));;
        if(labelsIndexes.contains(j))
        {
            
            if(content.indexOf(':') + 1 != content.length())
                content = content.substring(content.indexOf(' ') + 1 );
            else
                return true;
        }
        System.out.println(content);
        if(!content.contains(" ")) return false;
        String instruction = content.substring(0, content.indexOf(' '));
        String rest = content.substring(content.indexOf(' ') + 1);
        String[] arguments = rest.split(",");
        for(int i = 0;i < arguments.length;i++)
            arguments[i] = arguments[i].replaceAll("\\s","");

        System.out.println(instruction);
        for(int i = 0;i < arguments.length;i++)
            System.out.println(arguments[i]);

        return parse(instruction, arguments);
    }
    public static boolean parseLabel(String x, int i,int j)
    {
        if(!x.contains(":"))
            return true;
        String temp = x.substring(0,x.indexOf(':'));
        String label = temp.replaceAll("\\s","");
        if(!label.equals(temp))
            return true;
        if(x.indexOf(':') != x.length())
        {
            String instruction = x.substring(x.indexOf(':') + 1);
            instruction = instruction.replaceAll("\\s","");
            if(instruction.length() > 0)
            {
                labelsIndexes.add(j);
                labels.put(label,i);
                return true;
            }
        }
        labelsIndexes.add(j);
        labels.put(label,i);
    return false;
    }
}
