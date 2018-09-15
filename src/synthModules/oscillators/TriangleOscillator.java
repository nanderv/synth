package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class TriangleOscillator extends Oscillator {

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
            float phase = currentSample / period;
            float value = Math.abs(4 * ((phase - 0.25f)%1f) - 2f) - 1f;
            sampleArray[i] = (byte) (value * 127f);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new TriangleOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        new Thread(osc).start();
        new Thread(s).start();
    }
}
