package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;

public class PulseOscillator extends Oscillator{

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
    public float generateValue(float phase) {
        return (phase % 1f) <= dutyCycle ? 1f : 0f;
    }

    public static void main(String[] args) {
        Oscillator osc = new PulseOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}
