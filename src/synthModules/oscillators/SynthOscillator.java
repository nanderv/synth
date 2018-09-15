package synthModules.oscillators;

import static main.Config.SAMPLING_RATE;

public class SynthOscillator extends Oscillator implements Generator{

    public SynthOscillator(){
        super();
    }

    public SynthOscillator(float freq) {
        super(freq);
    }

    public byte[] nextSample(int samples){
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = (currentSample%period) / period;
            sampleArray[i] = (byte) (generate(phase) * 127f);
        }
        return sampleArray;
    }

    @Override
    public float generate(float phase) {
        return (float) (Math.sin(phase) + 0.5f *Math.sin(phase/2f) + 0.3f*Math.sin(phase/4f));
    }
}
