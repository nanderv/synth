package main;

import controlAdapters.FloatControlAdapter;
import controlAdapters.OscillatorFrequencyControlAdapter;
import synthModules.ConsumerModule;
import synthModules.modulators.TestModulator;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SineOscillator;
import synthModules.outputs.FloatControlOutput;
import synthModules.outputs.Speaker;

import static main.Config.FREQ_A;

public class TestPlay {
    
    public static void main(String[] args) {

        Oscillator osc = new SineOscillator(FREQ_A);
        Oscillator osc2 = new SineOscillator(FREQ_A/24);

        ConsumerModule s = new Speaker();
        OscillatorFrequencyControlAdapter f = new OscillatorFrequencyControlAdapter(osc);
        FloatControlOutput flow = new FloatControlOutput(f);

        osc2.connect(flow);
        osc.connect(s);
        new Thread(osc).start();
        new Thread(osc2).start();
        new Thread(flow).start();
        new Thread(s).start();

    }
}