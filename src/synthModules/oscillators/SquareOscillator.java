package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class SquareOscillator extends Oscillator {

    public SquareOscillator() {
        super();
    }

    public SquareOscillator(float freq) {
        super(freq);
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = 2.28f * currentSample / period;
            float value = Math.sin(phase) > 0.0d ? 1f : -1f;
            sampleArray[i] = (byte) (value * 127f);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SquareOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        new Thread(osc).start();
        new Thread(s).start();
    }
}
