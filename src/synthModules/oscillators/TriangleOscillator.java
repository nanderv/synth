package synthModules.oscillators;

import scheduling.Scheduler;
import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class TriangleOscillator extends Oscillator {

    public TriangleOscillator(){
        super();
    }

    public byte[] nextSample(int samples){
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = currentSample / period;
            float value = 1f - Math.abs(4 * ((phase + 0.25f)%1f) - 2f);
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new TriangleOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}
