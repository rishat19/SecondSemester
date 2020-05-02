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
        try (IniOutputStream out = new IniOutputStream(new FileOutputStream("src\\test1.ini"))) {
            for (Map.Entry<String, String> entry : keysAndValues.entrySet()) {
                out.writeINILine(entry);
                out.flush();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> testIniLineReader() {
        try (IniInputStream in = new IniInputStream(new FileInputStream("src\\test1.ini"))) {
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
        try (IniOutputStream out = new IniOutputStream(new FileOutputStream("src\\test2.ini"))) {
            out.writeINI(keysAndValues);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> testIniReader() {
        try (IniInputStream in = new IniInputStream(new FileInputStream("src\\test2.ini"))) {
            return in.readINI();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
