package utils;


import java.util.InputMismatchException;

import static main.Config.FREQ_A;

public class MidiConverter {

    private float[] freq = new float[128];
    private Note.Name[] names = new Note.Name[12];

    public MidiConverter(){
        for (int midi = 0; midi < 127; midi++){
            freq[midi]  = toFreq(midi);
        }
        for (int midi = 0; midi < 12; midi++) {
            names[midi] = toNoteName(midi);
        }
    }

    public float toCachedFreq(int midi){
        return freq[midi];
    }

    public Note.Name toCachedNoteName(int midi){
        return names[midi%12];
    }

    public static int toOctave(int midi){
        return midi/12;
    }

    public static float toFreq(int midi){
        return new Float((FREQ_A / 32.0) * Math.pow(2.0, (midi - 9) / 12.0));
    }

    public static Note.Name toNoteName(int midi){
        switch(midi%12){
            case 0 : return Note.Name.C ;
            case 1 : return Note.Name.Db;
            case 2 : return Note.Name.D ;
            case 3 : return Note.Name.Eb;
            case 4 : return Note.Name.E ;
            case 5 : return Note.Name.F ;
            case 6 : return Note.Name.Gb;
            case 7 : return Note.Name.G ;
            case 8 : return Note.Name.Ab;
            case 9 : return Note.Name.A ;
            case 10: return Note.Name.Bb;
            case 11: return Note.Name.B ;
            default: throw new InputMismatchException();
        }
    }

    public static void main(String[] args){
        MidiConverter c = new MidiConverter();
        for(int i=0;i<128;i++) {
            System.out.println(i+" => "+c.toFreq(i));
        }
    }
}
