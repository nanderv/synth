package main;

import scheduling.Scheduler;
import synthModules.ConsumerModule;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SquareOscillator;
import synthModules.outputs.Speaker;

import static main.Config.FREQ_A;


public class MidiNoteIdExample {
    //
    private static final int SAMPLE_RATE = 44 * 1024;


    public static void main(String[] args) {

        Oscillator osc = new SquareOscillator().setFreq(FREQ_A);

        ConsumerModule m = new Speaker();


        osc.connect(m);

        Scheduler.getInstance().addTask(osc);
        Scheduler.getInstance().addTask(m);

//        w2.start();
        osc = new SquareOscillator().setFreq(FREQ_A);

        m = new Speaker();


        osc.connect(m);

        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(m);

        Scheduler.configFreeRun();
//        w2.start();
     //   control.run();


    }
}