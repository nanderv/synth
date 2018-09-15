package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class SynthOscillator extends Oscillator {

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
            float phase = 6.28f * currentSample / period;
            float value = (float) (Math.sin(phase) + 0.5f *Math.sin(phase/2f) + 0.3f*Math.sin(phase/4f));
            sampleArray[i] = (byte) (value * 127f);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SynthOscillator();
        Speaker s = new Speaker();
        s.listenTo(osc);
        s.run();
    }
}
