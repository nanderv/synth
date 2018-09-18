package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;

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
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}
