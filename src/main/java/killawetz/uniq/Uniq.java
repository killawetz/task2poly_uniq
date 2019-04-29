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
        } else {
            Scanner scanInput = new Scanner(System.in);
            System.out.println("Write " + endOfConsole + " when you finish typing");
            String currentLine = scanInput.nextLine();
            while (!currentLine.equals(endOfConsole)) {
                inputStrings.add(currentLine);
                currentLine = scanInput.nextLine();
            }
            scanInput.close();
        }
    }

    public void union(ArrayList<String> inputs) {
        int count = 1;
        String str1;
        String str2 = inputs.get(0);
        String strBuffer;
        String firstSimilarity = inputs.get(0);
        for (int i = 1; i < inputs.size(); i++) {
            strBuffer = inputs.get(i);
            str1 = str2;
            str2 = strBuffer;
            if (comparison(str1, str2)) {
                if (ignoreCase) {
                    firstSimilarity = str1;
                    str2 = str2.toLowerCase();
                }
                count++;
            } else {
                firstSimilarity = str2;
                finalAdding(str1, count);
                count = 1;
            }
        }
        finalAdding(firstSimilarity, count);

    }

    public void finalAdding(String str, int count) {
        if (onlyUniq && count == 1) {
            finalList.add(str);
        }
        if (!onlyUniq) {
            if (numberStrings) {
                if (count > 1) {
                    finalList.add(count + " " + str);
                } else {
                    finalList.add(str);
                }
            } else {
                finalList.add(str);
            }
        }
    }

    public boolean comparison(String string1, String string2) {
        boolean equal = false;
        if (string1.equals(string2)) {
            equal = true;
        }
        if (ignoreCase) {
            equal = string1.equalsIgnoreCase(string2);
        }
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


    public void outputText(String outputFileName, ArrayList<String> data) throws IOException {
        String lineSeparator = System.getProperty("line.separator");
        if (outputFileName != null) {
            FileWriter writer = new FileWriter(outputFileName);
            for (String aData : data) {
                writer.write(aData + lineSeparator);
            }
            writer.close();
        } else {
            for (String aData : data) {
                System.out.println(aData);
            }
        }
    }

    public void uniq(String inputFileName,
                     String outputFileName) throws IOException {
        scanText(inputFileName);
        if (ignoreCase && onlyUniq || ignoreNSymbols > 0 && onlyUniq || numberStrings && onlyUniq) {
            throw new IllegalArgumentException("The -u flag cannot be used with the -i, -s, -c flags");
        }
        union(inputStrings);
        outputText(outputFileName, finalList);
    }

}
