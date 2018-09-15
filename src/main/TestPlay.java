package main;

import SynthModules.ConsumerModule;
import SynthModules.Oscillators.Oscillator;
import SynthModules.Oscillators.SineOscillator;
import SynthModules.Oscillators.TriangleOscillator;
import SynthModules.Outputs.Speaker;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.function.Consumer;

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