package synthModules.oscillators;

import static main.Config.SAMPLING_RATE;

public class SquareOscillator extends Oscillator implements Generator{

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
            sampleArray[i] = (byte) (generate(phase) * 127f);
        }
        return sampleArray;
    }

    @Override
    public float generate(float phase) {
        return Math.sin(phase) > 0.0d ? 1.0f : -1.0f;
    }
}
