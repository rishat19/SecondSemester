import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Note implements Serializable {

    private int duration;
    private int volume;
    private int[] notes;

    public Note() {
        this.duration = 0;
        this.volume = 0;
        this.notes = null;
    }

    public Note(int duration, int volume, int... notes) throws IllegalArgumentException {
        if (duration >= 0 && volume >= 0) {
            this.duration = duration;
            this.volume = volume;
            this.notes = new int[notes.length];
            for (int i = 0; i < notes.length; i++) {
                if (notes[i] >= 0 && notes[i] < 132) {
                    this.notes[i] = notes[i];
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int[] getNotes() {
        return notes;
    }

    public void setNotes(int[] notes) {
        this.notes = new int[notes.length];
        System.arraycopy(notes, 0, this.notes, 0, notes.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return duration == note.duration &&
                volume == note.volume &&
                Arrays.equals(notes, note.notes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(duration, volume);
        result = 31 * result + Arrays.hashCode(notes);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder letter = new StringBuilder();
        for (int note : notes) {
            if (note % 12 == 0) {
                letter.append("C");
            } else if (note % 12 == 1) {
                letter.append("C#");
            } else if (note % 12 == 2) {
                letter.append("D");
            } else if (note % 12 == 3) {
                letter.append("D#");
            } else if (note % 12 == 4) {
                letter.append("E");
            } else if (note % 12 == 5) {
                letter.append("F");
            } else if (note % 12 == 6) {
                letter.append("F#");
            } else if (note % 12 == 7) {
                letter.append("G");
            } else if (note % 12 == 8) {
                letter.append("G#");
            } else if (note % 12 == 9) {
                letter.append("A");
            } else if (note % 12 == 10) {
                letter.append("A#");
            } else {
                letter.append("B");
            }
            if (note / 12 == 1) {
                letter.append("0 ");
            } else if (note / 12 == 2) {
                letter.append("1 ");
            } else if (note / 12 == 3) {
                letter.append("2 ");
            } else if (note / 12 == 4) {
                letter.append("3 ");
            } else if (note / 12 == 5) {
                letter.append("4 ");
            } else if (note / 12 == 6) {
                letter.append("5 ");
            } else if (note / 12 == 7) {
                letter.append("6 ");
            } else if (note / 12 == 8) {
                letter.append("7 ");
            } else if (note / 12 == 9) {
                letter.append("8 ");
            } else {
                letter.append(" ");
            }
        }
        return letter.toString();
    }

}
