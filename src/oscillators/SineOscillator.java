package oscillators;

public class SineOscillator extends Oscillator {
    public SineOscillator(int sampleRate, float freq) {
        super(sampleRate, freq);
    }

    public byte[] nextSample(int samples){
        float period = (float) sampleRate / freq;
        byte[] sampleArray = new byte[samples];


        for(int i=0; i<samples; i++) {
            currentSample++;
            float angle = 2.0f * 3.14f * currentSample / period;
            sampleArray[i] = ((byte) (Math.sin(angle) * 127f));
        }
        return sampleArray;
    }

}
