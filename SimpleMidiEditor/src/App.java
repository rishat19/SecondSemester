import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private Scanner scanner;
    private String command;
    private ArrayList<Note> noteSheet;

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
    }

    private void init() {
        scanner = new Scanner(System.in);
        command = null;
        noteSheet = null;
    }

    private void start() {
        printGreeting();
        System.out.print("♫ ♪ Enter the command: ");
        while (!(command = scanner.nextLine()).equals("exit")) {
            switch (command) {
                case "create":
                    create();
                    break;
                case "save":
                    save();
                    break;
                case "play":
                    play();
                    break;
                case "midiKeyboard":
                    runMidiKeyboard();
                    break;
                case "help":
                    runHelp();
                    break;
                default:
                    System.out.println("♫ ♪ Invalid command. Try again.");
                    break;
            }
            System.out.print("♫ ♪ Enter the command: ");
        }
    }

    private void create() {
        if (noteSheet != null) {
            System.out.println("♫ ♪ You have already created music.");
            System.out.println("♫ ♪ If you want to save the current track, enter the command \"save\".");
            System.out.println("♫ ♪ To continue without saving, enter the command \"continue\".");
            System.out.print("♫ ♪ Enter the command: ");
            command = scanner.nextLine();
            if (command.equals("save")) {
                save();
            } else if (command.equals("continue")) {
                noteSheet = null;
            } else {
                System.out.println("♫ ♪ Invalid command. Try again.");
            }
            create();
        }
        printNoteTable();
        System.out.println("♫ ♪ Enter notes in the format:");
        System.out.println("noteDuration noteVolume noteNumbers...");
        System.out.println("♫ ♪ You can see the table with note numbers above. To stop, enter \"stop\".");
        String note;
        String[] noteProperties;
        noteSheet = new ArrayList<>();
        while (!(note = scanner.nextLine()).equals("stop")) {
            noteProperties = note.split("\\s+");
            try {
                int[] notes = new int[noteProperties.length - 2];
                for (int i = 0; i < notes.length; i++) {
                    notes[i] = Integer.parseInt(noteProperties[i + 2]);
                }
                Note newNote = new Note(Integer.parseInt(noteProperties[0]), Integer.parseInt(noteProperties[1]), notes);
                noteSheet.add(newNote);
                System.out.println(newNote.toString());
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Error. Note entered incorrectly.");
            }
        }
        if (!noteSheet.isEmpty()) {
            System.out.println("♫ ♪ Music successfully created");
        } else {
            noteSheet = null;
        }
    }

    private void save() {
        if (noteSheet != null) {
            System.out.println("♫ ♪ Enter the absolute path:");
            Path path = Paths.get(scanner.nextLine()).toAbsolutePath().normalize();
            System.out.println("♫ ♪ Enter file name:");
            String fileName = scanner.nextLine();
            if (!Files.exists(path)) {
                System.out.println("The specified directory does not exist.");
                return;
            }
            path = Paths.get(path.toString(), fileName);
            if (Files.exists(path)) {
                System.out.println("A file with the same name already exists.");
                return;
            }
            try (YamlNoteSheetOutputStream out = new YamlNoteSheetOutputStream(new FileOutputStream(path.toString()))) {
                out.writeNotes(noteSheet);
                out.flush();
                System.out.println("♫ ♪ Music successfully saved");
                noteSheet = null;
            } catch (IOException e) {
                System.out.println("Failed to save file.");
            }
        } else {
            System.out.println("♫ ♪ Music has not been created yet.");
        }
    }

    private void play() {
        System.out.println("♫ ♪ Enter the absolute path to the file:");
        Path path = Paths.get(scanner.nextLine()).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            System.out.println("The specified file does not exist or an invalid path was entered.");
            return;
        }
        System.out.format("%20s%6s%1s", "Acoustic piano -", "1", "\n");
        System.out.format("%20s%6s%1s", "Accordion -", "22", "\n");
        System.out.format("%20s%6s%1s", "Steel Guitar -", "26", "\n");
        System.out.format("%20s%6s%1s", "Clean Guitar -", "28", "\n");
        System.out.format("%20s%6s%1s", "Violin -", "41", "\n");
        System.out.format("%20s%6s%1s", "Trumpet -", "57", "\n");
        System.out.format("%20s%6s%1s", "Soprano Sax -", "65", "\n");
        System.out.format("%20s%6s%1s", "Flute -", "74", "\n");
        System.out.format("%20s%6s%1s", "Steel Drums -", "115", "\n");
        System.out.format("%20s%6s%1s", "Other instruments -", "1-128", "\n");
        System.out.println("♫ ♪ Choose a musical instrument:");
        int instrument = Integer.parseInt(scanner.nextLine());
        if (instrument < 1 || instrument > 128) {
            System.out.println("The specified musical instrument does not exist. The default instrument is a steel guitar.");
            instrument = 26;
        }
        try (YamlNoteSheetInputStream in = new YamlNoteSheetInputStream(new FileInputStream(path.toString()))) {
            Player player = new Player();
            player.playMusic(instrument, in.readNotes());
        } catch (IOException e) {
            System.out.println("Failed to open file.");
        }
    }

    private void runMidiKeyboard() {
        System.out.println("♫ ♪ Simple midi keyboard.");
        System.out.println("♫ ♪ Press the keys corresponding to the notes. To stop, enter \"z\".");
        System.out.format("%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%1s",
                "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "\n");
        System.out.format("%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%1s",
                "`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "\n");
        Player player = new Player();
        try {
            player.midiKeyboard();
        } catch (IOException e) {
            System.out.println("Input Error.");
        }

    }

    private void runHelp() {
        System.out.println("♫ ♪ Available commands: create, save, play, midiKeyboard, help, exit.");
    }

    private void printGreeting() {
        System.out.println("████████▀░░░▀██████████████████████████████████████████████████████████████\n" +
                "███████▀░▄▄░░▀█████████████████████████████████████████████████████████████\n" +
                "███████░░███░▄█████████████████████████████████████████████████████████████\n" +
                "██████░░██░░░███████████ Welcome to Rishat's Simple Midi Editor! ██████████\n" +
                "████████▀░░░███████████████████████████████████████████████████████████████\n" +
                "███████░░░░████████████████████████████████████████████████████████████████\n" +
                "█████░░░▄██████████████████████████████████████████████████████████████████\n" +
                "███▀░░▄█▀███████████████ Choose a command █████████████████████████████████\n" +
                "██░░░██░░░░░░░█████████████████████████████████████████████████████████████\n" +
                "█▀░░██░░░░█▄░░▀█████████ Available commands: ████ create ██████████████████\n" +
                "█▄░███░█░░███░░██████████████████████████████████ save ████████████████████\n" +
                "██░░██▄░█░▄██░░██████████████████████████████████ play ████████████████████\n" +
                "███▄░▀███░░█▄▄███████████████████████████████████ midiKeyboard ████████████\n" +
                "█████████░░░█████████████████████████████████████ help ████████████████████\n" +
                "██████████░██████████████████████████████████████ exit ████████████████████\n" +
                "████▀░░░▀█░░███████████████████████████████████████████████████████████████\n" +
                "████░░░░░░░░███████████████████████████████████████████████████████████████\n" +
                "████▄░░░░░▄████████████████████████████████████████████████████████████████");
    }

    private void printNoteTable() {
        System.out.format("%6s%17s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%1s",
                "octave", "octave", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "\n");
        System.out.format("%6s%17s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%4s%1s", "------", "----------------",
                "---", "---", "---", "---", "---", "---", "---", "---", "---", "---", "---", "---", "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                -1, "-", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                0, "subcounteroctave", 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                1, "counteroktave", 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                2, "big", 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                3, "small", 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                4, "first", 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                5, "second", 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                6, "third", 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                7, "fourth", 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                8, "fifth", 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, "\n");
        System.out.format("%6d%17s%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%4d%1s",
                9, "-", 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, "\n");
    }

}
