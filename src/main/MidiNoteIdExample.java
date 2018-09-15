package main;

import control.BasicFloatTerminalControl;
import control.NoteNamesTerminalControl;
import control.PrefixedTerminalMultiControl;
import controlAdapters.OscillatorFrequencyControl;
import synthModules.ConsumerModule;
import synthModules.modulators.AddModule;
import synthModules.modulators.Modulator;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SawtoothOscillator;
import synthModules.oscillators.SineOscillator;
import synthModules.oscillators.SquareOscillator;
import synthModules.outputs.Speaker;

import java.io.IOException;

import static utils.Note.FREQ_A;

public class MidiNoteIdExample {
    //
    private static final int SAMPLE_RATE = 44 * 1024;


    public static void main(String[] args) throws IOException {

        Oscillator osc = new SquareOscillator(FREQ_A, SAMPLE_RATE);
//        Oscillator osc2 = new SineOscillator(FREQ_A, SAMPLE_RATE);
//        Modulator mod = new AddModule();

//        osc2.connect(mod);

        ConsumerModule m = new Speaker(SAMPLE_RATE);
        osc.connect(m);


        new Thread(osc).start();
        new Thread(m).start();
        OscillatorFrequencyControl f = new OscillatorFrequencyControl(osc);
        f.setValue(220);
        f.setValuePercentage(20);
        PrefixedTerminalMultiControl control = new PrefixedTerminalMultiControl();
        control.addControl("f", new BasicFloatTerminalControl(f));
        control.addControl("n", new NoteNamesTerminalControl(f));
        control.run();
    }
}