import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MidiConverter {

    private static final int a = 440;// a is 440 hz
    private float[] freq = new float[128];
    private Note.Name[] names = new Note.Name[12];

    public MidiConverter(){
        for (int x = 0; x < 127; ++x)
        {
            freq[x] = (a / 32) * (2 ^ ((x - 9) / 12));
        }
        
        names[0] = Note.Name.C;
        names[1] = Note.Name.Db;
        names[2] = Note.Name.D;
        names[3] = Note.Name.Eb;
        names[4] = Note.Name.E;
        names[5] = Note.Name.F;
        names[6] = Note.Name.Gb;
        names[7] = Note.Name.G;
        names[8] = Note.Name.Ab;
        names[9] = Note.Name.A;
        names[10]= Note.Name.Bb;
        names[11]= Note.Name.B;

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
        return (a / 32) * (2 ^ ((midi - 9) / 12));
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
            default: throw new NotImplementedException();
        }
    }
}
