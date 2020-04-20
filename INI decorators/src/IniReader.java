import java.io.*;
import java.nio.CharBuffer;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

public class IniReader extends Reader {

    private BufferedReader in;

    public IniReader(Reader in) {
        this.in = new BufferedReader(in);
    }

    public Map<String, String> readINI() throws IOException {
        try {
            Map<String, String> keysAndValues = new TreeMap<>();
            String line = readLine();
            while (line != null) {
                String[] keyAndValue = line.split("=");
                keysAndValues.put(keyAndValue[0], keyAndValue[1]);
                line = readLine();
            }
            return keysAndValues;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new IOException("Unable to read INI file", e);
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return in.read();
    }

    public String readLine() throws IOException {
        return in.readLine();
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public boolean ready() throws IOException {
        return in.ready();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        in.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        in.reset();
    }

    public Stream<String> lines() {
        return in.lines();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public int read(CharBuffer target) throws IOException {
        return in.read(target);
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        return in.read(cbuf);
    }

}
