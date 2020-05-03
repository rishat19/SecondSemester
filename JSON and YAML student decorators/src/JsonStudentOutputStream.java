import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Collection;
import java.util.Objects;

public class JsonStudentOutputStream extends OutputStream {

    private BufferedWriter out;

    public JsonStudentOutputStream(OutputStream out) {
        this.out = new BufferedWriter(new OutputStreamWriter(out));
    }

    public void writeStudent(Student student) throws IOException {
        JSONObject JSONStudent = new JSONObject();
        JSONStudent.put("name", student.getName());
        JSONStudent.put("group", student.getGroup());
        JSONStudent.put("rating", student.getRating());
        out.write(JSONStudent.toJSONString());
    }

    public void writeAllStudents(Collection<Student> students) throws IOException {
        JSONArray JSONStudents = new JSONArray();
        for (Student student : students) {
            JSONObject JSONStudent = new JSONObject();
            JSONStudent.put("name", student.getName());
            JSONStudent.put("group", student.getGroup());
            JSONStudent.put("rating", student.getRating());
            JSONStudents.add(JSONStudent);
        }
        out.write(JSONStudents.toJSONString());
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
        JsonStudentOutputStream that = (JsonStudentOutputStream) o;
        return Objects.equals(out, that.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(out);
    }

    @Override
    public String toString() {
        return "JsonStudentWriter{" +
                "out=" + out +
                '}';
    }

}
