package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class SineOscillator extends Oscillator{

    public SineOscillator() {
        super();
    }

    public SineOscillator(float freq) {
        super(freq);
    }

    public byte[] nextSample(int samples){
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = 2.28f * currentSample / period;
            float value = (float) Math.sin(phase);
            sampleArray[i] = ((byte) (value * 127f));
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SineOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        new Thread(osc).start();
        new Thread(s).start();
    }
}
