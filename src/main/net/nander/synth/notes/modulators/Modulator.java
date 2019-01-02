package net.nander.synth.notes.modulators;

import net.nander.synth.notes.NoteStateAcceptor;

public abstract class Modulator implements NoteStateAcceptor {
    NoteStateAcceptor noteStateAcceptor;
    public void setNextNodeState(NoteStateAcceptor n){
        noteStateAcceptor = n;
    }
}
