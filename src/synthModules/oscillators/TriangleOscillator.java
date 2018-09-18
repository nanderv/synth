package synthModules.oscillators;

import scheduling.Scheduler;
import scheduling.Worker;
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
            float value = Math.abs(4 * ((phase - 0.25f)%1f) - 2f) - 1f;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new TriangleOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.getInstance().addTask(osc);
        Scheduler.getInstance().addTask(s);
        Worker w = new Worker();
        Worker w2 = new Worker();
        w.start();
    }
}
