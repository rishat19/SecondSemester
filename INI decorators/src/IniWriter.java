import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Objects;

public class IniWriter extends Writer {

    private BufferedWriter out;

    public IniWriter(Writer out) {
        this.out = new BufferedWriter(out);
    }

    public void writeINI(Map<String, String> keysAndValues) throws IOException {
        for (Map.Entry<String, String> keyAndValue : keysAndValues.entrySet()) {
            write(keyAndValue.getKey());
            write('=');
            write(keyAndValue.getValue());
            write('\n');
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

    @Override
    public void write(int c) throws IOException {
        out.write(c);
    }

    @Override
    public void write(String s, int off, int len) throws IOException {
        out.write(s, off, len);
    }

    public void newLine() throws IOException {
        out.newLine();
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        out.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str);
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return out.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return out.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return out.append(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IniWriter iniWriter = (IniWriter) o;
        return Objects.equals(out, iniWriter.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(out);
    }

    @Override
    public String toString() {
        return "IniWriter{" +
                "out=" + out +
                '}';
    }

}
