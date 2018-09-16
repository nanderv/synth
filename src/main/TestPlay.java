package main;

import controlAdapters.FloatControlAdapter;
import controlAdapters.OscillatorFrequencyControlAdapter;
import synthModules.ConsumerModule;
import synthModules.modulators.TestModulator;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SineOscillator;
import synthModules.oscillators.SquareOscillator;
import synthModules.outputs.FloatControlOutput;
import synthModules.outputs.Speaker;

import static main.Config.FREQ_A;

public class TestPlay {
    
    public static void main(String[] args) {

        Oscillator osc = new SineOscillator(FREQ_A);
        Oscillator osc2 = new SquareOscillator(1);

        Speaker s = new Speaker();
        OscillatorFrequencyControlAdapter f = new OscillatorFrequencyControlAdapter(osc);
        FloatControlOutput flow = new FloatControlOutput(f);
        flow.listenTo(osc2);
        s.listenTo(osc);
        new Thread(flow).start();
        s.run();

    }
}