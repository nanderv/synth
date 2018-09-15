import oscillators.Oscillator;
import oscillators.TriangleOscillator;
import utils.Note;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class TestPlay {
    //
    protected static final int SAMPLE_RATE = 16 * 1024;


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



    public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, SAMPLE_RATE);
        line.start();
        Oscillator osc = new TriangleOscillator(SAMPLE_RATE, Note.FREQ_A);

        boolean forwardNotBack = true;
        float freq = 110;

        for (int midinr = 48; midinr<6000; midinr++ ) {
            osc.setFreq(freq);
            for (int i = 0; i < 40; i++) {
                line.write(osc.nextSample(8), 0, 8);
            }
            freq++;
        }

        line.drain();
        line.close();
    }
}