import java.io.*;

public class Main {

    public static void main(String[] args) {
        int number = 1025;
        char c = 'ы';
        write(number, c);
        read();
    }

    public static void write(int number, char c) {
        try (OutputStream out = new FileOutputStream("src\\test.txt")) {
            out.write(number >>> 24 & 0xFF);
            out.write(number >>> 16 & 0xFF);
            out.write(number >>> 8 & 0xFF);
            out.write(number >>> 0 & 0xFF);
            out.write(c >>> 8 & 0xFF);
            out.write(c >>> 0 & 0xFF);
            out.flush();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try (InputStream in = new FileInputStream("src\\test.txt")) {
            int n1 = in.read();
            int n2 = in.read();
            int n3 = in.read();
            int n4 = in.read();
            int c1 = in.read();
            int c2 = in.read();
            System.out.println("Integer from file: " + ((n1 << 24) + (n2 << 16) + (n3 << 8) + (n4 << 0)));
            System.out.println("Char from file: " + (char) ((c1 << 8) + (c2 << 0)));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}