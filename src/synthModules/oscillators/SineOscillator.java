package synthModules.oscillators;

import scheduling.Scheduler;
import scheduling.Worker;
import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class SineOscillator extends Oscillator{

    public SineOscillator() {
        super();
    }

    public byte[] nextSample(int samples){
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = 6.28f * currentSample / period;
            float value = (float) Math.sin(phase);
            sampleArray[i] = ((byte) (value * amplitude));
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SineOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.getInstance().addTask(osc);
        Scheduler.getInstance().addTask(s);
        Worker w = new Worker();
        Worker w2 = new Worker();
        w.start();
    }
}
