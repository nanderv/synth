package net.nander.synth.notes;

import net.nander.synth.utils.Note;

import java.util.Iterator;
import java.util.Set;

public interface NoteState {
    Iterator<Note> getNotes();
    Set<Note> getNotesAsSet();
    void addNote(Note n);
}
