package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;

public class TriangleOscillator extends Oscillator{

    public TriangleOscillator(){
        super();
    }

    @Override
    public float generateValue(float phase) {
        return 1f - Math.abs(4 * ((phase + 0.25f)%1f) - 2f);
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
