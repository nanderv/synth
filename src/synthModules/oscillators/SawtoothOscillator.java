package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

import static main.Config.FREQ_A;
import static main.Config.SAMPLING_RATE;

public class SawtoothOscillator extends Oscillator implements Generator {

    public SawtoothOscillator() {
        super();
    }

    public SawtoothOscillator(float freq) {
        super(freq);
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for (int i = 0; i < samples; i++) {
            currentSample++;
            float phase = 2.28f * currentSample / period;
            sampleArray[i] = (byte) (generate(phase) * 127f);
        }
        return sampleArray;
    }

    @Override
    public float generate(float phase) {
        return (float) (2 * (phase / 2.28f - (Math.floor(phase / 2.28f + 0.5))));
    }
}