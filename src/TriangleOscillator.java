
public class TriangleOscillator extends Oscillator {
    public TriangleOscillator(float freq) {
        super(freq);
    }

    public byte[] nextSample(int samples){
        float period = (float)TestPlay.SAMPLE_RATE / freq;
        byte[] sampleArray = new byte[samples];


        for(int i=0; i<samples; i++) {
            currentSample++;
            float angle = -1 + 2 * (currentSample % period) / period;
            sampleArray[i] = ((byte) (angle * 127f));
        }
        return sampleArray;
    }

}
