import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            //task1
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter URI:");
            downloadFile(new URI(scanner.next()), "src", "newFile1");
            //task2
            System.out.println("Enter URI:");
            URI uri = new URI(scanner.next());
            System.out.println("Number of div tags = " + divCounter(uri));

        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

    }

    public static File downloadFile(URI uri, String path, String name) {
        try {
            URLConnection connection = uri.toURL().openConnection();
            String mimeType = connection.getContentType().split(";")[0];
            String fileExtension = MimeTypes.getDefaultMimeTypes().forName(mimeType).getExtension();
            BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
            Files.copy(in, new File(path + "\\" + name + fileExtension).toPath());
            return new File(path + "\\" + name + fileExtension);
        } catch (IOException | MimeTypeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int divCounter(URI uri) {
        try {
            int openDivCounter = 0;
            int closeDivCounter = 0;
            File file = downloadFile(uri, "src", "newFile2");
            String[] str = file.getName().split("\\.");
            String fileExtension = str[str.length - 1];
            if (fileExtension.equals("html") || fileExtension.equals("htm")) {
                BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == '<') {
                            if (i + 1 < line.length() && line.charAt(i + 1) != '/') {
                                if (i + 4 < line.length() && line.substring(i + 1, i + 4).equals("div")) {
                                    openDivCounter++;
                                }
                            } else {
                                if (i + 5 < line.length() && line.substring(i + 2, i + 5).equals("div")) {
                                    closeDivCounter++;
                                }
                            }
                        }
                    }
                }
            }
            return Integer.min(openDivCounter, closeDivCounter);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

}
