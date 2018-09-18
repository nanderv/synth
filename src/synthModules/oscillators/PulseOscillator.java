package synthModules.oscillators;

import scheduling.Scheduler;
import scheduling.Worker;
import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class PulseOscillator extends Oscillator {

    private float dutyCycle;

    public PulseOscillator() {
        super();
        this.dutyCycle = 0.5f;
    }

    /**
     * Set duty cycle between 0 and 1
     */
    public PulseOscillator setDutyCycle(float dutyCycle){
        this.dutyCycle = dutyCycle;
        return this;
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = currentSample%period / period;
            float value = phase > dutyCycle ? 1f : 0f;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new PulseOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.getInstance().addTask(osc);
        Scheduler.getInstance().addTask(s);
        Worker w = new Worker();
        Worker w2 = new Worker();
        w.start();

    }
}
