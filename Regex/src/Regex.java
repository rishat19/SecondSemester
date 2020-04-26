import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //1 task
        System.out.println("Enter a domain name:");
        String str = scanner.nextLine();
        if (isDomainName(str)) {
            System.out.println("Valid domain name");
        }
        else {
            System.out.println("Invalid domain name");
        }

        //2 task
        System.out.println("Enter text:");
        String text = scanner.nextLine();
        System.out.println("List of domain names found in the text:");
        for (String domainName : getAllDomainNamesFromText(text)) {
            System.out.println(domainName);
        }

        //3 task
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        for (Map.Entry<String, String> domainZone : getTopAndLowerLevelDomainZonesFromEmail(email).entrySet()) {
            System.out.println(domainZone.getKey() + ": " + domainZone.getValue());
        }

    }

    public static boolean isDomainName(String str) {
        return Pattern.matches("(^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$)|" +
                "(^((?!-)[А-Яа-я0-9-]{1,63}(?<!-)\\.)+[А-Яа-я]{2,6}$)", str);
    }

    public static ArrayList<String> getAllDomainNamesFromText(String text) {
        ArrayList<String> domainNames = new ArrayList<>();
        String regex = "(((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6})|(((?!-)[А-Яа-я0-9-]{1,63}(?<!-)\\.)+[А-Яа-я]{2,6})";
        Matcher matcher = Pattern.compile(regex).matcher(text);
        while (matcher.find()) {
            domainNames.add(matcher.group());
        }
        return domainNames;
    }

    public static Map<String, String> getTopAndLowerLevelDomainZonesFromEmail (String email) throws IllegalArgumentException {
        HashMap<String, String> domainZones = new HashMap<>();
        String emailRegex = "^(?![\\-\\.])[-0-9A-z\\.]+(?<![\\-\\.])@((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
        if (Pattern.matches(emailRegex, email)) {
            Matcher matcher = Pattern.compile("@(?!-)[A-Za-z0-9-]{1,63}(?<!-)").matcher(email);
            if (matcher.find()) {
                domainZones.put("Lower level domain zone", matcher.group().substring(1));
            }
            matcher = Pattern.compile("[A-Za-z]{2,6}$").matcher(email);
            if (matcher.find()) {
                domainZones.put("Top-level domain zone", matcher.group());
            }
        }
        else {
            throw new IllegalArgumentException("Invalid email");
        }
        return domainZones;
    }

}
