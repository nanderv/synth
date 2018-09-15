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
            float angle = -1+ 2* (currentSample%period) / period;
            sampleArray[i] = ((byte) (angle * 127f));
        }
        return sampleArray;
    }


}
