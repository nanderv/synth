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

public class TestPlay {
    //
    public static final int SAMPLE_RATE = 44 * 1024;


    public static byte[] createSinWaveBuffer(double freq, int ms) {
        int samples = (int)((ms * SAMPLE_RATE) / 1000);
        byte[] output = new byte[samples];
        //
        double period = (double)SAMPLE_RATE / freq;
        for (int i = 0; i < output.length; i++) {
            double angle = 2.0 * Math.PI * i / period;
            output[i] = (byte)(Math.sin(angle) * 127f);  }

        return output;
    }



    public static void main(String[] args) throws LineUnavailableException, InterruptedException {

         Oscillator osc = new TriangleOscillator(440);
         ConsumerModule s = new Speaker();
         osc.connect(s);
         new Thread(osc).start();
         new Thread(s).start();

    }
}