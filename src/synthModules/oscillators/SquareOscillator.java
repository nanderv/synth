package synthModules.oscillators;

public class SquareOscillator extends Oscillator implements Generator{
    public SquareOscillator(float freq, int SAMPLE_RATE) {
        super(freq, SAMPLE_RATE);
    }

    @Override
    public byte[] nextSample(int samples) {
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
        return Math.sin(phase) > 0.5d ? 1.0f : 0f;
    }
}
