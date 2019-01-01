package net.nander.synth.utils;

public enum Chord {

    //notes are given by byte[] with each byte representing a note, with the semisteps from the root as the value
    //E.g:  note: C  Db D  Eb E  F  Gb G  Ab A  Bb B
    //      byte: 0  1  2  3  4  5  6  7  8  9  10 11
    MAJOR       (new byte[]{0, 4, 7}, "M"),
    MINOR       (new byte[]{0, 3, 7}, "m"),
    DIMINISHED  (new byte[]{0, 3, 6}, "dim"),
    AUGMENTED   (new byte[]{0, 4, 8}, "+"),
    SUSPENDED   (new byte[]{0, 2, 7}, "sus"),
    ;

    private final byte[] notes;
    private final String notation;

    Chord(byte[] notes, String notation){
        this.notes = notes;
        this.notation = notation;
    }

    public byte[] notes(){
        return this.notes;
    }

    public String notation(){
        return this.notation;
    }
}
