import killawetz.uniq.Uniq;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UniqTest {
    private String lineSeparator = System.getProperty("line.separator");

    // сравнение содержимого файла
    private void assertFileContent(String outputName, String expectedContent) throws IOException {
        ArrayList<String> inputStrings = new ArrayList<>();
        FileReader reader = new FileReader(outputName);
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine()) {
            inputStrings.add(scan.nextLine());
        }
        reader.close();
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < inputStrings.size() - 1; i++) {
           content.append(inputStrings.get(i)).append(lineSeparator);
        }
        content.append(inputStrings.get(inputStrings.size()-1));
        assertEquals(expectedContent, content.toString());
    }

    @Test
    public void ignoreCase() throws IOException {
    new Uniq(true, 0, false, false).uniq("input\\input1.txt",
            "output\\output1.txt");
    assertFileContent("output\\output1.txt", "ignorecase");
    }


    @Test
    public void ignoreNSymbols() throws IOException {
        new Uniq(false, 2, false, false).uniq("input\\input2.txt",
                "output\\output2.txt");
        assertFileContent("output\\output2.txt", "12kotik");
    }

    @Test
    public void onlyUniq() throws IOException {
        new Uniq(false, 0, true, false).uniq("input\\input3.txt",
                "output\\output3.txt");
        assertFileContent("output\\output3.txt", "dadada" + lineSeparator + "yayaya" );
    }

    @Test
    public void numberStrings() throws IOException {
        new Uniq(false, 0, false, true).uniq("input\\input4.txt",
                "output\\output4.txt");
        assertFileContent("output\\output4.txt", "2 flazhochek" + lineSeparator + "3 stol" );
    }

    @Test
    public void ignoreUniq() throws IOException {
        assertThrows( IllegalArgumentException.class, () -> new Uniq(true, 0, true, false).uniq("input\\input1.txt",
                "output\\output1.txt"));
    }
}
