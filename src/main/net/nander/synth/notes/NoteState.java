package net.nander.synth.notes;

import net.nander.synth.utils.Note;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public interface NoteState {
    Iterator<Note> getNotes();
    Set<Note> getNotesAsSet();
    void addNote(Note n);


    public static NoteState getCMaj(){
        LinkedHashSet<Note> noteLinkedHashSet = new LinkedHashSet<>();
        noteLinkedHashSet.add(new Note(Note.Name.C, 4));
        noteLinkedHashSet.add(new Note(Note.Name.E, 4));
        noteLinkedHashSet.add(new Note(Note.Name.G, 4));
        return new LinkedHashSetNoteState(noteLinkedHashSet);
    }
}
