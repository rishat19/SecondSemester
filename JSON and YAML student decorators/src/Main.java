import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("JSON test");
        testJsonStudent();
        System.out.println("YAML test");
        testYamlStudent();
    }

    public static void testJsonStudent() {
        Student rishat = new Student("Ганиев Ришат", 902, 95);
        Student angelina = new Student("Евсикова Ангелина", 902, 86);
        Student alexandr = new Student("Динер Александр", 902, 85);
        Student olivia = new Student("Волкова Оливия", 902, 71.125);
        ArrayList<Student> students = new ArrayList<>();
        students.add(angelina);
        students.add(alexandr);
        students.add(olivia);
        try (JsonStudentOutputStream out = new JsonStudentOutputStream(new FileOutputStream("src\\test1.json"))) {
            out.writeStudent(rishat);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (JsonStudentOutputStream out = new JsonStudentOutputStream(new FileOutputStream("src\\test2.json"))) {
            out.writeAllStudents(students);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (JsonStudentInputStream in = new JsonStudentInputStream(new FileInputStream("src\\test1.json"))) {
            System.out.print(in.readStudent().toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (JsonStudentInputStream in = new JsonStudentInputStream(new FileInputStream("src\\test2.json"))) {
            for (Student student : in.readAllStudents()) {
                System.out.print(student.toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void testYamlStudent() {
        Student rishat = new Student("Ганиев Ришат", 902, 95);
        Student angelina = new Student("Евсикова Ангелина", 902, 86);
        Student alexandr = new Student("Динер Александр", 902, 85);
        Student olivia = new Student("Волкова Оливия", 902, 71.125);
        ArrayList<Student> students = new ArrayList<>();
        students.add(angelina);
        students.add(alexandr);
        students.add(olivia);
        try (YamlStudentOutputStream out = new YamlStudentOutputStream(new FileOutputStream("src\\test1.yaml"))) {
            out.writeStudent(rishat);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (YamlStudentOutputStream out = new YamlStudentOutputStream(new FileOutputStream("src\\test2.yaml"))) {
            out.writeAllStudents(students);
            out.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (YamlStudentInputStream in = new YamlStudentInputStream(new FileInputStream("src\\test1.yaml"))) {
            System.out.print(in.readStudent().toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (YamlStudentInputStream in = new YamlStudentInputStream(new FileInputStream("src\\test2.yaml"))) {
            for (Student student : in.readAllStudents()) {
                System.out.print(student.toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
