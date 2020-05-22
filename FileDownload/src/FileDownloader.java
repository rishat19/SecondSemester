import java.util.Scanner;

public class FileDownloader {

    private static DownloadThread thread;
    private static Scanner scanner;

    public static void main(String[] args) {
        String command;
        scanner = new Scanner(System.in);
        while (!(command = scanner.nextLine()).equals("exit")) {
            runCommand(command);
        }
    }

    public static void runCommand(String command) {
        try {
            String commandName = command.trim().split("\\s")[0].toLowerCase();
            switch (commandName) {
                case "download":
                    if (thread == null || thread.getState().equals(Thread.State.TERMINATED)) {
                        System.out.println("Enter URI:");
                        thread = new DownloadThread(scanner.next());
                        thread.start();
                    }
                    break;
                case "status":
                    if (thread == null) {
                        System.out.println("Download has not yet begun.");
                    } else {
                        System.out.println(thread.calculateDownloadPercentage() + "%");
                    }
                    break;
                case "stop":
                    if (thread != null) {
                        thread.interrupt();
                        System.out.println("Download interrupted.");
                    } else {
                        System.out.println("Download has not yet begun.");
                    }
                    break;
                default:
                    System.out.println("The name \"" + commandName + "\" is not recognized as the name of the command. Try again.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
