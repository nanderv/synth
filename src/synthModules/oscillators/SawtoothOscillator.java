package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class SawtoothOscillator extends Oscillator  {

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
            float phase = currentSample / period; //NOTE: no tau used here
            float value = -1 + 2 * phase;
            sampleArray[i] = (byte) (value * 127f);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SawtoothOscillator();
        Speaker s = new Speaker();
        s.listenTo(osc);
        s.run();
    }
}