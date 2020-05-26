import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.regex.Pattern;

public class SiteDownloader {

    private static int count = 1;
    private static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        String url = "https://hcsalavat.ru/players/salavat-yulaev/";
        download(url);
    }

    public static void download(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");
            for (org.jsoup.nodes.Element link : links) {
                Pattern pattern = Pattern.compile("https://hcsalavat.ru/players/salavat-yulaev[a-zA-Z0-9./\\-]+");
                String currentLink = link.attr("abs:href");
                if (pattern.matcher(currentLink).find()) {
                    try {
                        Thread.sleep(100);
                        if (set.add(currentLink)) {
                            URL currentUrl = new URL(currentLink);
                            try (InputStream in = currentUrl.openStream()) {
                                Files.copy(in, Paths.get("src\\HTML").toAbsolutePath().resolve(count + ".html").normalize());
                                count++;
                                download(currentLink);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}