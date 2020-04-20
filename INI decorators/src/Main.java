import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Map<String, String> student = new TreeMap<>();
        student.put("name", "Rishat");
        student.put("surname", "Ganiev");
        student.put("gender", "male");
        student.put("group", "11-902");
        testIniWriter(student);
        student.clear();
        student = testIniReader();
        for (Map.Entry<String, String> entry : student.entrySet()) {
            System.out.println(entry.getKey() + '=' + entry.getValue());
        }
    }

    public static void testIniWriter(Map<String, String> keysAndValues) {
        try (IniWriter out = new IniWriter(new FileWriter("src\\test.ini"))) {
            out.writeINI(keysAndValues);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> testIniReader() {
        try (IniReader in = new IniReader(new FileReader("src\\test.ini"))) {
            return in.readINI();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
