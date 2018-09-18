package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;

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
            float phase = currentSample%period / period; //NOTE: no tau used here
            float value = inverted
                ?  1 - 2*phase //  |\|\|\|\
                : -1 + 2*phase //  |/|/|/|/
            ;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
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