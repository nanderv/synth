package net.nander.synth.notes;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.modulators.Mixer;
import net.nander.synth.synthModules.oscillators.Oscillator;
import net.nander.synth.synthModules.oscillators.SawtoothOscillator;
import net.nander.synth.synthModules.outputs.Speaker;

import java.util.HashSet;
import java.util.Set;

public class SynthBankFactory {
    public static SynthBank createTestSynthBank(int counts, ConsumerModule m){
        Set<Oscillator> oscs = new HashSet<>();

        for(int i=0;i<counts; i++){
            SawtoothOscillator o = new SawtoothOscillator();
            oscs.add(o);
            o.connect(m, i);
            o.setAmplitude(0);
            Scheduler.addTaskDirectly(o);
        }

        return new SynthBank(oscs);
    }

    public static SynthBank speakeredSynthBank(int counts){
        Mixer m = new Mixer();
        SynthBank b = SynthBankFactory.createTestSynthBank(8, m);

        ConsumerModule s = new Speaker();
        m.connect(s);
        Scheduler.addTaskDirectly(s);
        Scheduler.addTaskDirectly(m);
        Scheduler.configFreeRun();

        return b;
    }
}
