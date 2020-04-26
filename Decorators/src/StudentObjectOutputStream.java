import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Objects;

public class StudentObjectOutputStream extends OutputStream {

    private ObjectOutputStream out;

    public StudentObjectOutputStream(OutputStream out) throws IOException {
        this.out = new ObjectOutputStream(out);
    }

    public void writeStudent(Student student) throws IOException {
        try {
            out.writeObject(student);
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

    public void useProtocolVersion(int version) throws IOException {
        out.useProtocolVersion(version);
    }

    public void writeObject(Object obj) throws IOException {
        out.writeObject(obj);
    }

    public void writeUnshared(Object obj) throws IOException {
        out.writeUnshared(obj);
    }

    public void defaultWriteObject() throws IOException {
        out.defaultWriteObject();
    }

    public ObjectOutputStream.PutField putFields() throws IOException {
        return out.putFields();
    }

    public void writeFields() throws IOException {
        out.writeFields();
    }

    public void reset() throws IOException {
        out.reset();
    }

    public void writeBoolean(boolean val) throws IOException {
        out.writeBoolean(val);
    }

    public void writeByte(int val) throws IOException {
        out.writeByte(val);
    }

    public void writeShort(int val) throws IOException {
        out.writeShort(val);
    }

    public void writeChar(int val) throws IOException {
        out.writeChar(val);
    }

    public void writeInt(int val) throws IOException {
        out.writeInt(val);
    }

    public void writeLong(long val) throws IOException {
        out.writeLong(val);
    }

    public void writeFloat(float val) throws IOException {
        out.writeFloat(val);
    }

    public void writeDouble(double val) throws IOException {
        out.writeDouble(val);
    }

    public void writeBytes(String str) throws IOException {
        out.writeBytes(str);
    }

    public void writeChars(String str) throws IOException {
        out.writeChars(str);
    }

    public void writeUTF(String str) throws IOException {
        out.writeUTF(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentObjectOutputStream that = (StudentObjectOutputStream) o;
        return Objects.equals(out, that.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(out);
    }

    @Override
    public String toString() {
        return "StudentObjectOutputStream{" +
                "out=" + out +
                '}';
    }

}
