package net.nander.synth.notes.modulators;


import net.nander.synth.notes.LinkedHashSetNoteState;
import net.nander.synth.notes.NoteState;
import net.nander.synth.notes.SynthBank;
import net.nander.synth.notes.SynthBankFactory;
import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.scheduling.Task;
import net.nander.synth.utils.Note;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Appegiator extends Modulator implements Task {
    private int i = 0;
    private long wait;
    private long time;
    private NoteState noteState;
    public Appegiator(int wait){
        this.wait = wait*20;
        time =java.lang.System.currentTimeMillis();
        noteState = new LinkedHashSetNoteState();
    }
    @Override
    public void setNoteState(NoteState noteState) {
        this.noteState = noteState;
    }



    @Override
    public void run() {
        System.out.println(i);
        int size = noteState.getNotesAsSet().size();

        time++;
        if(wait < time && size > 0){
            i=(i+1)%size;
            time = 0;
        }
        int j = i;
        for (Iterator<Note> it = noteState.getNotes(); it.hasNext(); ) {
            Note nextNote = it.next();
            j--;
            if (j<0) {
                LinkedHashSet<Note> news = new LinkedHashSet<>();
                news.add(nextNote);
                noteStateAcceptor.setNoteState(new LinkedHashSetNoteState(news));
                return;
            }
        }
    }
    public static void main(String[] args){

        Appegiator a = new Appegiator(300);
        Scheduler.addTaskDirectly(a);
        SynthBank b = SynthBankFactory.speakeredSynthBank(8);
        a.setNextNodeState(b);
        a.setNoteState(NoteState.getCMaj());

    }
}
