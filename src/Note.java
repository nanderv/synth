public class Note {

    public static final float FREQ_A = 440;

    public enum Name {
        C, Db, D, Eb, E, F, Gb, G, Ab, A, Bb, B
    }

    public final Name name;
    public final int octave;

    public Note(Name name, int octave){
        this.name = name;
        this.octave = octave;
    }

    public Note(int midi){
        this.octave = midi/12;
        this.name = MidiConverter.toNoteName(midi);
    }
}