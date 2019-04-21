package killawetz.uniq;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class UniqLauncher {

    @Option(name = "-o", metaVar = "ofile", usage = "Set output file name")
    private String outputFileName;

    @Option(name = "-i", metaVar = "ingoreC", usage = "Ignore case")
    private boolean ignoreCase;

    @Option(name = "-s", metaVar = "skipN", usage = "Skip first N chars in string")
    private int skipNChars;

    @Option(name = "-u", metaVar = "uniqStrings", usage = "Output only uniq strings")
    private boolean uniqStrings;

    @Option(name = "-c", metaVar = "numberOfStrings", usage = "Number of changed strings")
    private boolean numberOfStrings;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String inputFileName;


    public static void main(String[] args) throws IOException {
        new UniqLauncher().launch(args);
    }

    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage()); // получить сообщение об ошибке
            System.err.println("java -jar uniq.jar [-i] [-u] [-c] [-s num] [-o ofile] [file]");
            parser.printUsage(System.err); // получить список доступных команд
            return;
        }


        Uniq uniq = new Uniq(ignoreCase, skipNChars, uniqStrings, numberOfStrings);
        uniq.uniq(inputFileName, outputFileName);
    }
}