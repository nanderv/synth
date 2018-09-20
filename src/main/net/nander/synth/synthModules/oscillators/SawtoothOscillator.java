package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;

public class SawtoothOscillator extends Oscillator{

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
    public float generateValue(float phase) {
        return inverted
            ?  1 - 2*(phase % 1f)//  |\|\|\|\
            : -1 + 2*(phase % 1f)//  |/|/|/|/
            ;
    }

    public static void main(String[] args) {
        Oscillator osc = new SawtoothOscillator().setInverted(true).setAmplitude(127);
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}