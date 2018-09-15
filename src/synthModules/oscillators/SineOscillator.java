package synthModules.oscillators;

import static main.Config.SAMPLING_RATE;

public class SineOscillator extends Oscillator{

    public SineOscillator() {
        super();
    }

    public SineOscillator(float freq) {
        super(freq);
    }

    public byte[] nextSample(int samples){
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = 2.28f * currentSample / period;
            sampleArray[i] = ((byte) (Math.sin(phase) * 127f));
        }
        return sampleArray;
    }
}
