package synthModules.oscillators;

import static main.Config.SAMPLING_RATE;

public class TriangleOscillator extends Oscillator implements Generator {

    public TriangleOscillator(){
        super();
    }

    public TriangleOscillator(float freq) {
        super(freq);
    }

    public byte[] nextSample(int samples){
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
        return Math.abs(4 * ((phase/2.28f - 0.25f)%1f) - 2f) - 1f;
    }
}
