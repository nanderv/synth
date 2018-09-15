package main;

import control.BasicFloatTerminalControl;
import control.NoteNamesTerminalControl;
import control.PrefixedTerminalMultiControl;
import controlAdapters.OscillatorFrequencyControlAdapter;
import synthModules.ConsumerModule;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SineOscillator;
import synthModules.oscillators.SquareOscillator;
import synthModules.outputs.Speaker;

import java.io.IOException;

import static main.Config.FREQ_A;


public class MidiNoteIdExample {
    //
    private static final int SAMPLE_RATE = 44 * 1024;


    public static void main(String[] args) throws IOException {

        Oscillator osc = new SquareOscillator(FREQ_A);
       Oscillator osc2 = new SineOscillator(FREQ_A);
//        Modulator mod = new AddModule();

//        osc2.connect(mod);

        Speaker m = new Speaker();
        m.listenTo(osc);

        OscillatorFrequencyControlAdapter f = new OscillatorFrequencyControlAdapter(osc);
        PrefixedTerminalMultiControl control = new PrefixedTerminalMultiControl();
        control.addControl("f", new BasicFloatTerminalControl(f));
        control.addControl("n", new NoteNamesTerminalControl(f));

        new Thread(m).start();
        control.run();
    }
}