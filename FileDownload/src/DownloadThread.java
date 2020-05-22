import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadThread extends Thread {

    private URLConnection connection;
    private BufferedInputStream in;
    private int size;
    private Path filePath;

    public DownloadThread(String uriString) {
        try {
            URI uri = new URI(uriString);
            connection = uri.toURL().openConnection();
            in = new BufferedInputStream(connection.getInputStream());
            size = connection.getContentLength();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String mimeType = connection.getContentType().split(";")[0];
            String fileExtension = MimeTypes.getDefaultMimeTypes().forName(mimeType).getExtension();
            System.out.println("Start downloading a file...");
            filePath = Paths.get("").toAbsolutePath().resolve("file" + fileExtension).normalize();
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.copy(in, filePath);
            in.close();
        } catch (MimeTypeException | IOException e) {
            e.printStackTrace();
        }
    }

    public double calculateDownloadPercentage() {
        if (Thread.currentThread().getState().equals(State.TERMINATED)) {
            return 100;
        }
        try {
            return (double) (Files.size(filePath) / size) * 100;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
