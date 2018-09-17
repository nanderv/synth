package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class PulseOscillator extends Oscillator {

    private float width;

    public PulseOscillator() {
        super();
        this.width = 0.5f;
    }

    /**
     * Set pulse width between 0 and 1
     */
    public PulseOscillator setPulseWidth(float width){
        this.width = width;
        return this;
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = currentSample%period / period;
            float value = phase > width ? 1f : 0;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new PulseOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        new Thread(osc).start();
        new Thread(s).start();
    }
}
