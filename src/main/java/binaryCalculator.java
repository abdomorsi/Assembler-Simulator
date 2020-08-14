public class binaryCalculator {

    public static String convertToTwosComplement(String binaryCode)
    {
        String temp = "";
        boolean flag = false;
        for(int i = binaryCode.length() - 1;i >= 0;i--)
        {
            if(!flag)
            {
                temp = binaryCode.charAt(i) + temp;
                if(binaryCode.charAt(i) == '1')
                    flag = true;
            }
            else
            {
                if(binaryCode.charAt(i) == '0')
                    temp = '1' + temp;
                else
                    temp = '0' + temp;
            }
        }
        return temp;
    }
    public static String binaryConverter(int register , int len) {
        StringBuilder padding  = new StringBuilder();
        for (int i = 0; i < len; i++) {
            padding.append('0');
        }
        StringBuilder sb = new StringBuilder(padding);
        String result =Integer.toBinaryString(Math.abs(register));
        result = sb.substring(result.length())+result;
        if(register < 0)
            result = convertToTwosComplement(result);
        System.out.println("inside binary convertor : " + result + " " + register);
        return  result;
    }
    
}
