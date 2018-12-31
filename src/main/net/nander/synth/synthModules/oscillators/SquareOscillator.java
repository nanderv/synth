package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;

public class SquareOscillator extends Oscillator{

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
    public float generateSignal(float phase) {
        return phase > dutyCycle ? -1f : 1f;
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
