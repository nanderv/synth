package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class SawtoothOscillator extends Oscillator  {

    public SawtoothOscillator() {
        super();
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for (int i = 0; i < samples; i++) {
            currentSample++;
            float phase = currentSample / period; //NOTE: no tau used here
            float value = -1 + 2 * phase;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SawtoothOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        new Thread(osc).start();
        new Thread(s).start();
    }
}