package synthModules.oscillators;

import scheduling.Scheduler;
import scheduling.Worker;
import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class SawtoothOscillator extends Oscillator  {

    private boolean inverted;

    public SawtoothOscillator() {
        super();
        this.inverted = false;
    }

    public SawtoothOscillator setInverted(boolean inverted){
        this.inverted = inverted;
        return this;
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for (int i = 0; i < samples; i++) {
            currentSample++;
            float phase = currentSample / period; //NOTE: no tau used here
            float value = inverted
                ?  1 - 2*phase //  |\|\|\|\
                : -1 + 2*phase //  |/|/|/|/
            ;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SawtoothOscillator().setInverted(true);
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.getInstance().addTask(osc);
        Scheduler.getInstance().addTask(s);
        Worker w = new Worker();
        Worker w2 = new Worker();
        w.start();
    }
}