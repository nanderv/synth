package SynthModules.Oscillators;

import main.TestPlay;

public class SineOscillator extends Oscillator {
    public SineOscillator(float freq, final int SAMPLE_RATE) {
        super(freq, SAMPLE_RATE);
    }

    public byte[] nextSample(int samples){
        float period = (float) SAMPLE_RATE / freq;
        byte[] sampleArray = new byte[samples];


        for(int i=0; i<samples; i++) {
            currentSample++;
            float angle = 2.0f * 3.14f * currentSample / period;
            sampleArray[i] = ((byte) (Math.sin(angle) * 127f));
        }
        return sampleArray;
    }

}
