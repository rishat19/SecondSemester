import java.util.Scanner;

public class FileDownloader {

    private static DownloadThread thread;

    public static void main(String[] args) {
        String command;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command:");
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
                        thread = new DownloadThread(command.trim().split("\\s")[1]);
                        thread.start();
                    }
                    break;
                case "status":
                    if (thread == null) {
                        System.out.println("Download has not yet begun.");
                    } else if (!thread.isFileExist()) {
                        System.out.println("Download has not yet begun.");
                    } else {
                        System.out.printf("%.2f%2s", thread.calculateDownloadPercentage(), "%\n");
                    }
                    break;
                case "stop":
                    if (thread != null) {
                        thread.deleteFile();
                        if (thread.isInterrupted()) {
                            thread.interrupt();
                        }
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
            System.out.println("Error.");
        }
    }

}
