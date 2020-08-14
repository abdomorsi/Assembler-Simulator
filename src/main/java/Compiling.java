import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Compiling {

    public static ArrayList<Integer> compile(String content) throws IOException {
       
        //System.out.print(content);
        String[] lines = content.split("\n"); 
        System.out.println("lines length : " + lines.length);
        String x;
        int i = 0;
        int j = 0;
        for(j = 0;j < lines.length;j++) {
            x = lines[j];
            if(Parser.parseLabel(x,i,j))
                i++;
        }
        ArrayList<Integer>errorLines = new ArrayList<>();
        for(j = 0;j < lines.length;j++)
        {
            x = lines[j];
            System.out.println(x);
                if(Parser.parse(x,j) == false)
                    errorLines.add(j);
        }
        
        return errorLines;
    }
       public static ArrayList<Integer> compileFile(String filePath) throws IOException {
               Scanner in = new Scanner(System.in);
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String x;
        int i = 0;
        int j = 0;
        while((x = br.readLine()) != null) {
            if(Parser.parseLabel(x,i,j))
                i++;
            j++;
        }
        for(int k = 0;k < Parser.labelsIndexes.size();k++)
            System.out.println(Parser.labelsIndexes.get(k));
        br.close();
        File file1 = new File("input.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        j = 0;
        ArrayList<Integer> errorLines = new ArrayList<>();
            while((x = br1.readLine()) != null)
        {
            if(Parser.parse(x,j) == false)
                errorLines.add(j);
            j++;
        }
           return errorLines;
       }
}

 /*
        
        for(int k = 0;k < Assembler.instructionList.size();k++)
            System.out.println(Assembler.instructionList.get(k));

        System.out.println("----------------------");
        System.out.println(binaryCalculator.binaryConverter(-10,5));
        */