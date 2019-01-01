package net.nander.synth.notes;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.modulators.Mixer;
import net.nander.synth.synthModules.oscillators.Oscillator;
import net.nander.synth.synthModules.oscillators.SawtoothOscillator;
import net.nander.synth.synthModules.outputs.Speaker;
import net.nander.synth.utils.Note;

import java.util.*;

public class SynthBank{

    private Set<Oscillator> freeOscillators;
    private Map<Note, Oscillator> usedOscillators;
    public SynthBank(Set<Oscillator> freeOscillators){
        usedOscillators = new HashMap<>();
        this.freeOscillators = freeOscillators;
    }

    public void setNoteState(NoteState n){
        System.out.println("A");
        for(Note note: usedOscillators.keySet()){
            if (! n.getNotesAsSet().contains(note))
            {
                System.out.println("A");
                Oscillator o = usedOscillators.get(note);
                freeOscillators.add(o);
                usedOscillators.remove(note);
                o.setAmplitude(0);

            }
        }
        System.out.println(n.getNotesAsSet().size());

        for (Iterator<Note> it = n.getNotes(); it.hasNext(); ) {
            Note note = it.next();
            System.out.println("AAAA");
            if (usedOscillators.containsKey(note)){
                System.out.println("Skipping");

                continue;
            }
            if(!freeOscillators.isEmpty()) {
                System.out.println("Adding");
                Oscillator o = freeOscillators.iterator().next();
                freeOscillators.remove(o);
                usedOscillators.put(note, o);
                o.setFreq(note.toFreq());
                o.setAmplitude(32f);
            } else {
                System.out.println("No oscillators left to use!");
            }
        }
    }

}
