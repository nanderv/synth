package net.nander.synth.midi;

import net.nander.synth.utils.Note;

import java.util.Iterator;

public interface NoteState {
    Iterator<Note> getNotes();
}
