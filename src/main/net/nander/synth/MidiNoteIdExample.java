package net.nander.synth;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.oscillators.Oscillator;
import net.nander.synth.synthModules.oscillators.SquareOscillator;
import net.nander.synth.synthModules.outputs.Speaker;

import static net.nander.synth.Config.FREQ_A;

public class MidiNoteIdExample {

    public static void main(String[] args) {

        Oscillator osc = new SquareOscillator().setFreq(FREQ_A);
        ConsumerModule m = new Speaker();
        osc.connect(m);
        Scheduler.getInstance().addTask(osc);
        Scheduler.getInstance().addTask(m);
        osc = new SquareOscillator().setFreq(FREQ_A);
        m = new Speaker();
        osc.connect(m);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(m);
        Scheduler.configFreeRun();
    }
}