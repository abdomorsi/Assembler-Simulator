
import javax.swing.table.DefaultTableModel;

public class MemoryController {
    static String[] memoryContents = new String[100];
    static DefaultTableModel model1;
    static DefaultTableModel segmentTable;
    public static void setMemoryTable(DefaultTableModel model2, DefaultTableModel segmentTable)
    {
        model1 = model2;
        MemoryController.segmentTable = segmentTable;
    }
    public static String getValueFromAddressWord(int address)
    {
        return memoryContents[address];
    }
    public static String getValueFromAddress(String address)
    {
        int address1 = Integer.parseInt(address,2);
        return getValueFromAddress(address1);
    }
    public static String getValueFromAddress(int address)
    {
        if(address >= 4096)
            address = ( (address % 4096) / 4 ) + 50;
        else
            address /= 4;
        return memoryContents[address];
    }
    public static void setValueForAddress(int value, int address)
    {
        if(address >= 4096)
        {
        address = ( (address % 4096) / 4 ) + 50;
        segmentTable.setValueAt(binaryCalculator.binaryConverter(value,32), (int)address - 50, 1);
        segmentTable.setValueAt(value, (int)address - 50, 2);
        }
        else
        {
            address /= 4;
        model1.setValueAt(binaryCalculator.binaryConverter(value,32), (int)address, 1);
        model1.setValueAt(value, (int)address - 50, 2);
        }
        memoryContents[(int)address] = binaryCalculator.binaryConverter(value,32);
    }
    /*
addi $s1, $s1, 4104
addi $s2, $s2, 10
sw $s2, 4($s1)
    */
    public static void setValueForAddressWord(String value, int address)
    {
        memoryContents[address] = value;
        model1.setValueAt(value, address, 1);
        System.out.println(value);
        int x = Integer.parseUnsignedInt(value,2);
        model1.setValueAt( Integer.toHexString(x) , address, 2);
    }
     public static void setValueForAddress(String value, int address)
    {
        if(address >= 4096)
        {
        address = ( (address % 4096) / 4 ) + 50;
        segmentTable.setValueAt(value, (int)address - 50, 1);
        segmentTable.setValueAt(Integer.parseInt(value,2), (int)address - 50, 2);
        }
        else
        {
            address /= 4;
            model1.setValueAt(value, (int)address, 1);
            model1.setValueAt(Integer.toHexString(Integer.parseUnsignedInt(value,2)), (int)address, 2);
        }
        memoryContents[address] = value;
    }
     /*
addi $s1, $s1, 4104
addi $s2, $s2, -1
sw $s2, 4($s1)
lw $s3, 4($s1)
     */
    public static void clearMemory()
    {
        for(int i = 0;i < memoryContents.length;i++)
            {
                memoryContents[i] = binaryCalculator.binaryConverter(0,32);
            }
    }

}
