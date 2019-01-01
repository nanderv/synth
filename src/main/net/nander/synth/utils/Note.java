package net.nander.synth.utils;

import static net.nander.synth.Config.FREQ_A;

public class Note {

    public enum Name {
        C, Db, D, Eb, E, F, Gb, G, Ab, A, Bb, B
    }

    public final Name name;
    public final int octave;

    public Note(Name name, int octave){
        this.name = name;
        this.octave = octave;
    }
    public float toFreq(){
        return new Float((FREQ_A / 32.0) * Math.pow(2.0, (octave + (name.ordinal() - 9) / 12.0)));
    }

    public Note(int midi){
        this.octave = midi/12;
        this.name = MidiConverter.toNoteName(midi);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * (octave+7)*5;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Note){
            Note b = (Note) obj;
            return b.name.equals(name) && b.octave == octave;
        }
        return false;
    }

    public String toString(){
        return this.name + Integer.toString(octave);
    }
}