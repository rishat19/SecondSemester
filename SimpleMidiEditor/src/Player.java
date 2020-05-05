import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Player {

    private MidiChannel[] channels;
    private Synthesizer synthesizer;

    public Player() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();
        } catch (MidiUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setChannel(int channel, int instrument) {
        channels[channel].programChange(instrument);
    }

    public void close() {
        synthesizer.close();
    }

    public void playSound(int channel, int duration, int volume, int... notes) {
        for (int note : notes) {
            channels[channel].noteOn(note, volume);
        }
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int note : notes) {
            channels[channel].noteOff(note);
        }
    }

    public void playMusic(int instrument, Collection<Note> noteSheet) {
        setChannel(0, instrument);
        for (Note note : noteSheet) {
            playSound(0, note.getDuration(), note.getVolume(), note.getNotes());
        }
    }

    public void midiKeyboard() throws IOException {
        char key;
        while ((key = (char) System.in.read()) != 'z') {
            switch (key) {
                case '`':
                    playSound(0, 250, 100,59);
                    break;
                case '1':
                    playSound(0, 250, 100,60);
                    break;
                case '2':
                    playSound(0, 250, 100,61);
                    break;
                case '3':
                    playSound(0, 250, 100,62);
                    break;
                case '4':
                    playSound(0, 250, 100,63);
                    break;
                case '5':
                    playSound(0, 250, 100,64);
                    break;
                case '6':
                    playSound(0, 250, 100,65);
                    break;
                case '7':
                    playSound(0, 250, 100,66);
                    break;
                case '8':
                    playSound(0, 250, 100,67);
                    break;
                case '9':
                    playSound(0, 250, 100,68);
                    break;
                case '0':
                    playSound(0, 250, 100,69);
                    break;
                case '-':
                    playSound(0, 250, 100,70);
                    break;
                case '=':
                    playSound(0, 250, 100,71);
                    break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Arrays.equals(channels, player.channels) &&
                Objects.equals(synthesizer, player.synthesizer);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(synthesizer);
        result = 31 * result + Arrays.hashCode(channels);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "channels=" + Arrays.toString(channels) +
                ", synthesizer=" + synthesizer +
                '}';
    }

}
