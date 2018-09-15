package oscillators;

public class TriangleOscillator extends Oscillator {
    public TriangleOscillator(int sampleRate, float freq) {
        super(sampleRate, freq);
    }

    public byte[] nextSample(int samples){
        float period = (float) sampleRate / freq;
        byte[] sampleArray = new byte[samples];


        for(int i=0; i<samples; i++) {
            currentSample++;
            float angle = -1 + 2 * (currentSample % period) / period;
            sampleArray[i] = ((byte) (angle * 127f));
        }
        return sampleArray;
    }

}
