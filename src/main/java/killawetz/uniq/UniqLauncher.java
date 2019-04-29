/* Вариант 10 — uniq
        Объединение последовательностей одинаковых идущих подряд строк в файле в одну:
        file задаёт имя входного файла. Если параметр отсутствует, следует считывать текст с консоли.
        Флаг -o ofile задаёт имя выходного файла. Если параметр отсутствует, следует выводить результаты на консоль.
        Флаг -i означает, что при сравнении строк следует не учитывать регистр символов.
        Флаг -s N означает, что при сравнении строк следует игнорировать первые N символов каждой строки. Выводить нужно первую строку.
        Флаг -u означает, что следует выводить в качестве результата только уникальные строки.
        Флаг -с означает, что перед каждой строкой вывода следует вывести количество строк, которые были заменены данной (т.е. если во входных данных было 2 одинаковые строки, в выходных данных должна быть одна с префиксом “2”).

        Command line: uniq [-i] [-u] [-c] [-s num] [-o ofile] [file]

        В случае, когда какое-нибудь из имён файлов указано неверно, следует выдать ошибку.

        Кроме самой программы, следует написать автоматические тесты к ней.
*/
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