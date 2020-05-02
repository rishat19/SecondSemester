import java.io.*;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class IniInputStream extends InputStream {

    private BufferedReader in;

    public IniInputStream(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
    }

    public Map.Entry<String, String> readINILine() throws IOException {
        try {
            String line = in.readLine();
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
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return super.skip(n);
    }

    @Override
    public int available() throws IOException {
        return super.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
    }

    @Override
    public boolean markSupported() {
        return super.markSupported();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IniInputStream that = (IniInputStream) o;
        return Objects.equals(in, that.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(in);
    }

    @Override
    public String toString() {
        return "IniInputStream{" +
                "in=" + in +
                '}';
    }

}
