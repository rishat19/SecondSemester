import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextFilePrinter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the file: ");
        Path path = Paths.get(scanner.nextLine()).toAbsolutePath().normalize();
        if (Files.exists(path)) {
            System.out.print("Enter the encoding: ");
            String encoding = scanner.nextLine();
            if (Charset.isSupported(encoding)) {
                try {
                    printTextFile(encoding, path);
                } catch (IOException io){
                    System.out.println("Unable to read file.");
                }
            } else {
                System.out.println("Incorrect encoding.");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }

    public static void printTextFile(String encoding, Path path) throws IOException {
        Charset charset = Charset.forName(encoding);
        for (String line : Files.readAllLines(path, charset)){
            System.out.println(new String(line.getBytes(charset), Charset.forName("Cp866")));
        }
    }

}