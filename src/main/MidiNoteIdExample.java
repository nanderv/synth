package main;

import control.BasicFloatTerminalControl;
import control.NoteNamesTerminalControl;
import control.PrefixedTerminalMultiControl;
import controlAdapters.OscillatorFrequencyControlAdapter;
import scheduling.Scheduler;
import scheduling.Worker;
import synthModules.ConsumerModule;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SquareOscillator;
import synthModules.outputs.Speaker;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

import static main.Config.FREQ_A;


public class MidiNoteIdExample {
    //
    private static final int SAMPLE_RATE = 44 * 1024;


    public static void main(String[] args) throws IOException, LineUnavailableException {

        Oscillator osc = new SquareOscillator().setFreq(FREQ_A);

        ConsumerModule m = new Speaker();


        osc.connect(m);

        Scheduler.getInstance().addTask(osc);
        Scheduler.getInstance().addTask(m);
        Worker w = new Worker();
        Worker w2 = new Worker();
        w.start();
//        w2.start();
     //   control.run();


    }
}