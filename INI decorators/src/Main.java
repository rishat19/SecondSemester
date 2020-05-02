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
        testIniLineWriter(student);
        testIniWriter(student);
        student.clear();
        student = testIniLineReader();
        for (Map.Entry<String, String> entry : student.entrySet()) {
            System.out.println(entry.getKey() + '=' + entry.getValue());
        }
        student.clear();
        System.out.println();
        student = testIniReader();
        for (Map.Entry<String, String> entry : student.entrySet()) {
            System.out.println(entry.getKey() + '=' + entry.getValue());
        }
    }

    public static void testIniLineWriter(Map<String, String> keysAndValues) {
        try (IniWriter out = new IniWriter(new FileWriter("src\\test1.ini"))) {
            for (Map.Entry<String, String> entry : keysAndValues.entrySet()) {
                out.writeINILine(entry);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> testIniLineReader() {
        try (IniReader in = new IniReader(new FileReader("src\\test1.ini"))) {
            Map<String, String> map = new TreeMap<>();
            Map.Entry<String, String> entry;
            while ((entry = in.readINILine()) != null) {
                map.put(entry.getKey(), entry.getValue());
            }
            return map;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void testIniWriter(Map<String, String> keysAndValues) {
        try (IniWriter out = new IniWriter(new FileWriter("src\\test2.ini"))) {
            out.writeINI(keysAndValues);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> testIniReader() {
        try (IniReader in = new IniReader(new FileReader("src\\test2.ini"))) {
            return in.readINI();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
