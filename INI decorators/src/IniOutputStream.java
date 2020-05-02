import java.io.*;
import java.util.Map;
import java.util.Objects;

public class IniOutputStream extends OutputStream {

    private BufferedWriter out;

    public IniOutputStream(OutputStream out) {
        this.out = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void writeINILine(Map.Entry<String, String> keyAndValue) throws IOException {
        out.write(keyAndValue.getKey());
        out.write('=');
        out.write(keyAndValue.getValue());
        out.write('\n');
    }

    public void writeINI(Map<String, String> keysAndValues) throws IOException {
        for (Map.Entry<String, String> keyAndValue : keysAndValues.entrySet()) {
            writeINILine(keyAndValue);
        }
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IniOutputStream that = (IniOutputStream) o;
        return Objects.equals(out, that.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(out);
    }

    @Override
    public String toString() {
        return "IniOutputStream{" +
                "out=" + out +
                '}';
    }

}
