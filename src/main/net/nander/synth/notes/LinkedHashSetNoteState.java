package net.nander.synth.midi;

import net.nander.synth.utils.Note;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class LinkedHashSetNoteState implements NoteState {
    LinkedHashSet<Note> notes;

    public LinkedHashSetNoteState(){
        notes = new LinkedHashSet<>();
    }

    public void addNote(Note n){
        if (notes.contains(n)){
            return;
        }
        notes.add(n);
    }

    @Override
    public Iterator<Note> getNotes() {
        return notes.iterator();
    }
}
