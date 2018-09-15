package main;

import control.BasicFloatTerminalControl;
import control.NoteNamesTerminalControl;
import control.PrefixedTerminalMultiControl;
import controlAdapters.OscillatorFrequencyControlAdapter;
import synthModules.ConsumerModule;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SquareOscillator;
import synthModules.outputs.Speaker;

import java.io.IOException;

import static main.Config.FREQ_A;


public class MidiNoteIdExample {
    //
    private static final int SAMPLE_RATE = 44 * 1024;


    public static void main(String[] args) throws IOException {

        Oscillator osc = new SquareOscillator().setFreq(FREQ_A);
//        Oscillator osc2 = new SineOscillator(FREQ_A, SAMPLE_RATE);
//        Modulator mod = new AddModule();

//        osc2.connect(mod);

        ConsumerModule m = new Speaker();
        osc.connect(m);


        new Thread(osc).start();
        new Thread(m).start();
        OscillatorFrequencyControlAdapter f = new OscillatorFrequencyControlAdapter(osc);
        f.setValuePercentage(10);
        PrefixedTerminalMultiControl control = new PrefixedTerminalMultiControl();
        control.addControl("f", new BasicFloatTerminalControl(f));
        control.addControl("n", new NoteNamesTerminalControl(f));
        control.run();
    }
}