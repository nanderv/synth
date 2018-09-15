package main;

import synthModules.ConsumerModule;
import synthModules.oscillators.Oscillator;
import synthModules.oscillators.TriangleOscillator;
import synthModules.outputs.Speaker;

import static utils.Note.FREQ_A;

public class TestPlay {
    //
    private static final int SAMPLE_RATE = 44 * 1024;


    public static void main(String[] args) {

         Oscillator osc = new TriangleOscillator(FREQ_A, SAMPLE_RATE);
         ConsumerModule s = new Speaker(SAMPLE_RATE);
         osc.connect(s);
         new Thread(osc).start();
         new Thread(s).start();

    }
}