
import javax.swing.table.DefaultTableModel;

public class RegistersController {
    static int[] registers = new int[32];
    static DefaultTableModel model;
    public static void setRegisterTable(DefaultTableModel model2)
    {
        model = model2;
    }
    public static int getRegisterValue(String registerBinaryCode)
    {
            return registers[Integer.parseInt(registerBinaryCode,2)];
    }
    public static void setRegisterValue(int value, String registerBinaryAddress)
    {
           System.out.println("inside set Register " + value);
           registers[Integer.parseInt(registerBinaryAddress,2)] = value;
           model.setValueAt(binaryCalculator.binaryConverter(value, 32), Integer.parseInt(registerBinaryAddress,2),1);
            model.setValueAt(value, Integer.parseUnsignedInt(registerBinaryAddress,2),2);
    }
    public static void clearRegisters()
    {
        for(int i = 0;i < registers.length;i++)
            {
                registers[i] = 0;
            }
    }
}