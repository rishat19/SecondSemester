import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        studentDataInputOutputTest();
        System.out.println();
        studentObjectInputOutputTest();
    }

    public static void studentDataInputOutputTest() {
        Student rishat = new Student("Ганиев Ришат", 902, 95);
        Student angelina = new Student("Евсикова Ангелина", 902, 86);
        Student alexandr = new Student("Динер Александр", 902, 85);
        Student olivia = new Student("Волкова Оливия", 902, 71.125);
        try (StudentDataOutputStream out = new StudentDataOutputStream(new FileOutputStream("src\\test.txt"))) {
            out.writeStudent(rishat);
            out.writeStudent(angelina);
            out.writeStudent(alexandr);
            out.writeStudent(olivia);
            out.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (StudentDataInputStream in = new StudentDataInputStream(new FileInputStream("src\\test.txt"))) {
            System.out.print(in.readStudent().toString());
            System.out.print(in.readStudent().toString());
            System.out.print(in.readStudent().toString());
            System.out.print(in.readStudent().toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void studentObjectInputOutputTest() {
        Student rishat = new Student("Ганиев Ришат", 902, 95);
        Student angelina = new Student("Евсикова Ангелина", 902, 86);
        Student alexandr = new Student("Динер Александр", 902, 85);
        Student olivia = new Student("Волкова Оливия", 902, 71.125);
        try (StudentObjectOutputStream out = new StudentObjectOutputStream(new FileOutputStream("src\\test.txt"))) {
            out.writeStudent(rishat);
            out.writeStudent(angelina);
            out.writeStudent(alexandr);
            out.writeStudent(olivia);
            out.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (StudentObjectInputStream in = new StudentObjectInputStream(new FileInputStream("src\\test.txt"))) {
            System.out.print(in.readStudent().toString());
            System.out.print(in.readStudent().toString());
            System.out.print(in.readStudent().toString());
            System.out.print(in.readStudent().toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
