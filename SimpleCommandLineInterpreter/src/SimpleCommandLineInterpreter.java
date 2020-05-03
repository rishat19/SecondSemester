import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.Objects;
import java.util.Scanner;

public class SimpleCommandLineInterpreter {

    private static Path currentPath = Paths.get(".").normalize().toAbsolutePath();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.print(currentPath + ">: ");
        while (!(command = scanner.nextLine()).equals("exit")) {
            runCommand(command);
            System.out.print(currentPath + ">: ");
        }
    }

    private static void runCommand(String command) {
        try {
            String commandName = command.trim().split("\\s")[0].toLowerCase();
            switch (commandName) {
                case "cd":
                    changeDirectory(command.trim().substring(2).trim());
                    break;
                case "ls":
                    listComputerFiles(command.trim().substring(2).trim());
                    break;
                case "cp":
                    copyFileOrDirectory(command.trim().substring(2).trim());
                    break;
                case "rm":
                    removeFileOrDirectory(command.trim().substring(2).trim());
                    break;
                default:
                    if (!openFile(command.trim())) {
                        printCommandNameError(command);
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeDirectory(String directoryPath) {
        if (directoryPath.length() > 0) {
            Path newCurrentPath = Paths.get(directoryPath).normalize();
            if (newCurrentPath.toString().charAt(0) == '\\') {
                newCurrentPath = newCurrentPath.toAbsolutePath().normalize();
            } else {
                newCurrentPath = Paths.get(currentPath.toString(), newCurrentPath.toString()).toAbsolutePath().normalize();
            }
            if (Files.exists(newCurrentPath)) {
                currentPath = newCurrentPath;
            } else {
                System.out.println("The directory does not exist.");
            }
        }
    }

    private static void listComputerFiles(String directoryPath) {
        Path newCurrentPath = currentPath;
        if (directoryPath.length() > 0) {
            newCurrentPath = Paths.get(directoryPath).normalize();
            if (newCurrentPath.toString().charAt(0) == '\\') {
                newCurrentPath = newCurrentPath.toAbsolutePath().normalize();
            } else {
                newCurrentPath = Paths.get(currentPath.toString(), newCurrentPath.toString()).toAbsolutePath().normalize();
            }
        }
        if (Files.exists(newCurrentPath)) {
            printFiles(newCurrentPath);
        } else {
            System.out.println("The directory does not exist.");
        }
    }

    private static void printFiles(Path directoryPath) {
        try {
            System.out.println("Directory: " + directoryPath.toString());
            System.out.println("Mode          LastWriteTime              Length    Name");
            System.out.println("----          -------------              ------    ----");
            for (File file : Objects.requireNonNull(new File(directoryPath.toString()).listFiles())) {
                if (file.isDirectory()) {
                    System.out.print("dir     ");
                } else {
                    System.out.print("file    ");
                }
                FileTime time = Files.getLastModifiedTime(Paths.get(file.getPath()));
                System.out.print(time.toString().substring(0, 10) + "    ");
                System.out.print(time.toString().substring(11, 16) + " ");
                System.out.format("%19d", file.length());
                System.out.print("    ");
                System.out.println(file.getName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void copyFileOrDirectory(String path) {
        String[] paths = path.split("\\s");
        if (paths.length == 2) {
            try {
                File file1;
                if (paths[0].charAt(0) == '\\') {
                    file1 = new File(paths[0]);
                } else {
                    file1 = new File(currentPath.toString() + "\\" + paths[0]);
                }
                File file2;
                if (paths[1].charAt(0) == '\\') {
                    file2 = new File(paths[1]);
                } else {
                    file2 = new File(currentPath.toString() + "\\" + paths[1]);
                }
                if (Files.exists(file1.toPath())) {
                    if (file2.isDirectory()) {
                        Files.copy(file1.toPath(), new File(file2.getPath() + "\\" + file1.getName()).toPath());
                    } else {
                        Files.copy(file1.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                else {
                    System.out.println("The file/directory does not exist");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid parameters");
        }
    }

    private static void removeFileOrDirectory(String directoryPath) {
        if (directoryPath.length() > 0) {
            File file;
            if (directoryPath.charAt(0) == '\\') {
                file = new File(directoryPath);
            } else {
                file = new File(currentPath.toString() + "\\" + directoryPath);
            }
            if (file.delete()) {
                System.out.println("File/directory deleted.");
            } else {
                System.out.println("The file does not exist or the directory is not empty.");
            }
        } else {
            System.out.println("Missing file/directory name.");
        }
    }

    private static boolean openFile(String filePath) {
        if (filePath.length() > 0) {
            try {
                File file;
                if (filePath.charAt(0) == '\\') {
                    file = new File(filePath);
                } else {
                    file = new File(currentPath.toString() + "\\" + filePath);
                }
                if (Files.exists(file.toPath()) && !Files.isDirectory(file.toPath())) {
                    Desktop.getDesktop().open(file);
                } else {
                    return false;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            return false;
        }
        return true;
    }

    private static void printCommandNameError(String commandName) {
        System.out.println("The name \"" + commandName + "\" is not recognized as the name of the command. Try again.");
    }

}
