package main;

import synthModules.ConsumerModule;
import synthModules.modulators.TestModulator;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.SineOscillator;
import synthModules.outputs.Speaker;

import static main.Config.FREQ_A;

public class TestPlay {
    
    public static void main(String[] args) {

        Oscillator osc = new SineOscillator(FREQ_A);
        Oscillator osc2 = new SineOscillator(FREQ_A/24);
        Oscillator osc3 = new SineOscillator(0.05f);
        TestModulator p = new TestModulator();
        TestModulator p2 = new TestModulator();
        ConsumerModule s = new Speaker();
        osc.connect(p);
        osc2.connect(p);
        p.connect(p2);
        osc3.connect(p2);
        p2.connect(s);

        new Thread(osc).start();
        new Thread(osc2).start();
        new Thread(osc3).start();

        new Thread(p).start();
        new Thread(p2).start();
        new Thread(s).start();

    }
}