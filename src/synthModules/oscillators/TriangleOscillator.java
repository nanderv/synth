package synthModules.oscillators;

public class TriangleOscillator extends Oscillator {
    public TriangleOscillator(float freq, int SAMPLE_RATE) {
        super(freq, SAMPLE_RATE);
    }

    public byte[] nextSample(int samples){
        float period = (float) SAMPLE_RATE / freq;
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
