import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;

public class StudentSerialization {

    public static void write(Collection<Student> students, String path) {
        if (students != null) {
            try (OutputStream out = new FileOutputStream(path)) {
                ByteBuffer sizeBuffer = ByteBuffer.allocate(Integer.BYTES);
                sizeBuffer.putInt(students.size());
                out.write(sizeBuffer.array());
                out.flush();
                for (Student student : students) {
                    ByteBuffer studentBuffer = ByteBuffer.allocate(Character.BYTES * student.getName().length() +
                            2 * Integer.BYTES + Double.BYTES);
                    studentBuffer.putInt(student.getName().length());
                    for (int i = 0; i < student.getName().length(); i++) {
                        studentBuffer.putChar(student.getName().charAt(i));
                    }
                    studentBuffer.putInt(student.getGroup());
                    studentBuffer.putDouble(student.getRating());
                    out.write(studentBuffer.array());
                    out.flush();
                    studentBuffer.clear();
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new NullPointerException("Student collection points to zero.");
        }
    }

    public static Collection<Student> read(String path) {
        Collection<Student> students = new ArrayList<>();
        String name;
        int group;
        double rating;
        try (InputStream in = new FileInputStream(path)) {
            ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
            for (int i = 0; i < Integer.BYTES; i++) {
                buffer.put((byte) in.read());
            }
            buffer.rewind();
            int size = buffer.getInt();
            buffer.clear();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < Integer.BYTES; j++) {
                    buffer.put((byte) in.read());
                }
                buffer.rewind();
                int nameSize = buffer.getInt();
                buffer.clear();
                char[] studentName = new char[nameSize];
                for (int j = 0; j < nameSize; j++) {
                    buffer.put((byte) in.read());
                    buffer.put((byte) in.read());
                    buffer.rewind();
                    studentName[j] = buffer.getChar();
                    buffer.clear();
                }
                name = new String(studentName);
                for (int j = 0; j < Integer.BYTES; j++) {
                    buffer.put((byte) in.read());
                }
                buffer.rewind();
                group = buffer.getInt();
                buffer.clear();
                for (int j = 0; j < Double.BYTES; j++) {
                    buffer.put((byte) in.read());
                }
                buffer.rewind();
                rating = buffer.getDouble();
                buffer.clear();
                students.add(new Student(name, group, rating));
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

}
