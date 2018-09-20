package net.nander.synth.synthModules.oscillators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SynthOscillator extends Oscillator {

    public SynthOscillator(){
        super();
    }

    public byte[] nextSample(int samples){
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = 6.28f * currentSample / period;
            //TODO: keep frequencies separated instead of combining them, making filter implementation trivial
            float value = (float) (Math.sin(phase) + 0.5f *Math.sin(phase/2f) + 0.3f*Math.sin(phase/4f));
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    @Override
    public float generateValue(float phase) {
        throw new NotImplementedException(); //There is no single value, as there are multiple frequencies
    }

    public static void main(String[] args) {
        Oscillator osc = new SynthOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}
