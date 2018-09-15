package synthModules.oscillators;

public class SynthOscillator extends Oscillator implements Generator{
    public SynthOscillator(float freq, int SAMPLE_RATE) {
        super(freq, SAMPLE_RATE);
    }

    public byte[] nextSample(int samples){
        float period = (float) SAMPLE_RATE / freq;
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
