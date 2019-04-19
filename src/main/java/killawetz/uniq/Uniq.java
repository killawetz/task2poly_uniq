package killawetz.uniq;

import java.io.*;

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

    public void uniq(InputStream in, OutputStream out) throws IOException {
    try (InputStreamReader reader = new InputStreamReader(in)) {
        try (OutputStreamWriter writer = new OutputStreamWriter(out)) {


}
