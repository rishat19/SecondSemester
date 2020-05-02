import java.io.*;
import java.nio.CharBuffer;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Stream;

public class IniReader extends Reader {

    private BufferedReader in;

    public IniReader(Reader in) {
        this.in = new BufferedReader(in);
    }

    public Map.Entry<String, String> readINILine() throws IOException {
        try {
            String line = readLine();
            if (line != null) {
                String[] keyAndValue = line.split("=", 2);
                return new AbstractMap.SimpleEntry<>(keyAndValue[0], keyAndValue[1]);
            }
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IOException("Unable to read INI file", e);
        }
    }

    public Map<String, String> readINI() throws IOException {
        Map<String, String> keysAndValues = new TreeMap<>();
        Map.Entry<String, String> entry;
        while ((entry = readINILine()) != null) {
            keysAndValues.put(entry.getKey(), entry.getValue());
        }
        return keysAndValues;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IniReader iniReader = (IniReader) o;
        return Objects.equals(in, iniReader.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(in);
    }

    @Override
    public String toString() {
        return "IniReader{" +
                "in=" + in +
                '}';
    }

}
