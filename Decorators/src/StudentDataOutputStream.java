import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class StudentDataOutputStream extends OutputStream {

    private DataOutputStream out;

    public StudentDataOutputStream(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public void writeStudent(Student student) throws IOException {
        try {
            out.writeUTF(student.getName());
            out.writeInt(student.getGroup());
            out.writeDouble(student.getRating());
        }
        catch (IOException e) {
            throw new IOException("Unable to write student", e);
        }
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    //Delegated methods

    public void writeBoolean(boolean v) throws IOException {
        out.writeBoolean(v);
    }

    public void writeByte(int v) throws IOException {
        out.writeByte(v);
    }

    public void writeShort(int v) throws IOException {
        out.writeShort(v);
    }

    public void writeChar(int v) throws IOException {
        out.writeChar(v);
    }

    public void writeInt(int v) throws IOException {
        out.writeInt(v);
    }

    public void writeLong(long v) throws IOException {
        out.writeLong(v);
    }

    public void writeFloat(float v) throws IOException {
        out.writeFloat(v);
    }

    public void writeDouble(double v) throws IOException {
        out.writeDouble(v);
    }

    public void writeBytes(String s) throws IOException {
        out.writeBytes(s);
    }

    public void writeChars(String s) throws IOException {
        out.writeChars(s);
    }

    public void writeUTF(String str) throws IOException {
        out.writeUTF(str);
    }

    public int size() {
        return out.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDataOutputStream that = (StudentDataOutputStream) o;
        return Objects.equals(out, that.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(out);
    }

    @Override
    public String toString() {
        return "StudentDataOutputStream{" +
                "out=" + out +
                '}';
    }

}
