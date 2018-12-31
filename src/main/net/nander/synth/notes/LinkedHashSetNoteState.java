package net.nander.synth.notes;

import net.nander.synth.utils.Note;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetNoteState implements NoteState {
    LinkedHashSet<Note> notes;

    public LinkedHashSetNoteState(){
        notes = new LinkedHashSet<>();
    }

    public LinkedHashSetNoteState(LinkedHashSet notes){
        this.notes = notes;
    }
    public void addNote(Note n){
        if (notes.contains(n)){
            System.out.println("Added already");
            return;
        }
        notes.add(n);
    }

    @Override
    public Iterator<Note> getNotes() {
        return notes.iterator();
    }

    @Override
    public Set<Note> getNotesAsSet() {
        return notes;
    }
}
