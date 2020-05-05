import org.ho.yaml.Yaml;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Objects;

public class YamlNoteSheetOutputStream extends OutputStream {

    private OutputStream out;

    public YamlNoteSheetOutputStream(OutputStream out) {
        this.out = out;
    }

    public void writeNotes(Collection<Note> notes) throws IOException {
        Yaml.dump(notes, out);
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
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YamlNoteSheetOutputStream that = (YamlNoteSheetOutputStream) o;
        return Objects.equals(out, that.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(out);
    }

    @Override
    public String toString() {
        return "YamlNoteOutputStream{" +
                "out=" + out +
                '}';
    }

}
