import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StudentDataOutputStream extends OutputStream {

    private DataOutputStream out;

    public StudentDataOutputStream(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public void writeStudent(Student student) {
        try {
            int group = student.getGroup();
            out.writeUTF(student.getName());
            out.writeInt(student.getGroup());
            out.writeDouble(student.getRating());
            out.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
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

}
