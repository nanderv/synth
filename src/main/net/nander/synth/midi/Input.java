package net.nander.synth.midi;
import net.nander.synth.notes.LinkedHashSetNoteState;
import net.nander.synth.notes.NoteState;
import net.nander.synth.notes.SynthBank;
import net.nander.synth.utils.MidiConverter;
import net.nander.synth.utils.Note;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.sound.midi.ShortMessage.NOTE_OFF;
import static javax.sound.midi.ShortMessage.NOTE_ON;

public class Input implements Receiver {
    public String name;

    public LinkedHashSet<Note> notes = new LinkedHashSet<>();
    private SynthBank synthBank;
    public Input(SynthBank b, String name){
        this.synthBank = b;
        this.name = name;
        System.out.println(b);
        System.out.println("DEBUG");
    }

    public void send(MidiMessage message, long timeStamp) {
        synchronized (synthBank) {
            if (message instanceof ShortMessage) {
                ShortMessage sm = (ShortMessage) message;
                if (sm.getCommand() == NOTE_ON) {
                    int key = sm.getData1();
                    int octave = (key / 12) - 1;
                    int note = key % 12;
                    int velocity = sm.getData2();

                    if (velocity == 0) {
                        notes.remove(new Note(MidiConverter.toNoteName(note), octave));
                    } else {
                        notes.add(new Note(MidiConverter.toNoteName(note), octave));
                    }
                    NoteState n = new LinkedHashSetNoteState(notes);
                    if (synthBank == null) {
                        System.out.println("DAFUQ");
                    } else {
                        synthBank.setNoteState(n);
                    }
                } else if (sm.getCommand() == NOTE_OFF) {
                    int key = sm.getData1();
                    int octave = (key / 12) - 1;
                    int note = key % 12;

                    int velocity = sm.getData2();
                } else {
                }
            } else {
                System.out.println("Other message: " + message.getClass());
            }
        }
    }
    public void close() {}
}
