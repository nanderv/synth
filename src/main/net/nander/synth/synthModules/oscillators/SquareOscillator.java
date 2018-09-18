package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;

public class SquareOscillator extends Oscillator {

    private float dutyCycle;

    public SquareOscillator() {
        super();
        this.dutyCycle = 0.5f;
    }

    /**
     * Set pulse width between 0 and 1
     */
    public SquareOscillator setDutyCycle(float dutyCycle){
        this.dutyCycle = dutyCycle;
        return this;
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = currentSample%period / period;
            float value = phase > dutyCycle ? -1f : 1f;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new SquareOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}
