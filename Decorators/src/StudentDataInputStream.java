import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StudentDataInputStream extends InputStream {

    private DataInputStream in;

    public StudentDataInputStream(InputStream in) {
        this.in = new DataInputStream(in);
    }

    public Student readStudent() {
        try {
            String name = in.readUTF();
            int group = in.readInt();
            double rating = in.readDouble();
            return new Student(name, group, rating);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    //Delegated methods

    public void readFully(byte[] b) throws IOException {
        in.readFully(b);
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        in.readFully(b, off, len);
    }

    public int skipBytes(int n) throws IOException {
        return in.skipBytes(n);
    }

    public boolean readBoolean() throws IOException {
        return in.readBoolean();
    }

    public byte readByte() throws IOException {
        return in.readByte();
    }

    public int readUnsignedByte() throws IOException {
        return in.readUnsignedByte();
    }

    public short readShort() throws IOException {
        return in.readShort();
    }

    public int readUnsignedShort() throws IOException {
        return in.readUnsignedShort();
    }

    public char readChar() throws IOException {
        return in.readChar();
    }

    public int readInt() throws IOException {
        return in.readInt();
    }

    public long readLong() throws IOException {
        return in.readLong();
    }

    public float readFloat() throws IOException {
        return in.readFloat();
    }

    public double readDouble() throws IOException {
        return in.readDouble();
    }

    @Deprecated
    public String readLine() throws IOException {
        return in.readLine();
    }

    public String readUTF() throws IOException {
        return in.readUTF();
    }

    public static String readUTF(DataInput in) throws IOException {
        return DataInputStream.readUTF(in);
    }

}
