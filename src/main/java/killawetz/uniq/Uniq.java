package killawetz.uniq;

import java.io.*;
import java.util.*;

public class Uniq {

    private boolean ignoreCase;
    private int ignoreNSymbols;
    private boolean onlyUniq;
    private boolean numberStrings;

    public Uniq(boolean ignoreCase, int ignoreNSymbols, boolean onlyUniq, boolean numberStrings) {
        this.ignoreCase = ignoreCase;
        this.ignoreNSymbols = ignoreNSymbols;
        this.onlyUniq = onlyUniq;
        this.numberStrings = numberStrings;
    }

    private ArrayList<String> inputStrings = new ArrayList<>();
    private ArrayList<String> finalList = new ArrayList<>();
    private static String endOfConsole = "End"; //ограничитель для сканера

    public void scanText(String input) throws IOException { // метод для получения текста
        if (input != null) {
            FileReader reader = new FileReader(input);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                inputStrings.add(scan.next());
            }
            reader.close();
        }
         else {
            Scanner scanInput = new Scanner(System.in);
            System.out.println("Напишите " + endOfConsole + " когда закончите ввод");
            while(!scanInput.nextLine().equals(endOfConsole)) {
                inputStrings.add(scanInput.nextLine());
            }
            scanInput.close();
        }
    }

    public void union(ArrayList<String> inputs) {
        int count = 1;
        String str1;
        String str2;
        for (int i = 0; i < inputs.size() - 1; i++) {
            str1 = inputs.get(i);
            str2 = inputs.get(i+1);
            if(comparisonIgnoreCase(str1, str2) || comparisonWithoutNSymbols(str1,str2)) {
            count++;
            }
            else {
            finalAdding(str1, count);
            count = 1;
            }
        }

    }

    public void finalAdding(String str, int count){
        if(onlyUniq && count == 1) {
        finalList.add(str);
        }
        if(!onlyUniq) {
            if (numberStrings) {
                finalList.add(count + " " + str);
            } else {
                finalList.add(str);
            }
        }
    }

    public boolean comparisonIgnoreCase(String string1, String string2) { // флаг -i
        boolean equal = false;
        if(ignoreCase) {
            equal = string1.equalsIgnoreCase(string2);
        }
        return equal;
    }

    public boolean comparisonWithoutNSymbols(String string1, String string2) { // флаг -s N
        boolean equal = false;
        if (ignoreNSymbols > 0) {
            if (string1.length() == string2.length()) {
                equal = string1.regionMatches(ignoreNSymbols,
                        string2,
                        ignoreNSymbols,
                        string1.length() - ignoreNSymbols);
            }
        }
        return equal;
    }


    public void outputText(String outputFileName, ArrayList<String> data ) throws IOException {
        if(outputFileName!= null) {
            FileWriter writer = new FileWriter(outputFileName);
            for (String aData : data) {
                writer.write(aData);
            }
            writer.close();
        }
        else {
            for (String aData : data) {
                System.out.println(aData);
            }
        }

    }

    public void uniq(String inputFileName, String outputFileName) throws IOException {
        scanText(inputFileName);
        union(inputStrings);
        outputText(outputFileName, finalList);
    }

}
