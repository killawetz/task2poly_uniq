package killawetz.uniq;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void uniq(InputStream in, OutputStream out) throws IOException {
        if (in != null) {
            try (InputStreamReader reader = new InputStreamReader(in)) {
                try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                } // дописать для файла
            }
        } else {
            Scanner input = new Scanner(System.in);
            System.out.println("Напишите" + endOfConsole + "когда закончите ввод");
            while(!input.nextLine().equals(endOfConsole)) {
                inputStrings.add(input.nextLine());
            }
            in.close();
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

    public void countStr(ArrayList<String> inputStr){
        int num = 1;
        for (int i = 0; i < inputStr.size() - 1 ; i++) {
            if(comparisonIgnoreCase(inputStr.get(i), inputStr.get(i+1))) {
        }
    }
    }


    public void union(ArrayList<String> inputStrings) {
        for (int i = 0; i < inputStrings.size(); i++) {
            if(ignoreCase || ignoreNSymbols > 0) {
                if(comparisonIgnoreCase(inputStrings.get(i), inputStrings.get(i+1)) ||
                        comparisonWithoutNSymbols(inputStrings.get(i), inputStrings.get(i+1))) {
                    finalList.add(inputStrings.get(i));
        }
    }

}
