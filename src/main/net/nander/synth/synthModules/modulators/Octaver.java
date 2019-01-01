package net.nander.synth.synthModules.modulators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.ModuleInput;
import net.nander.synth.synthModules.oscillators.Oscillator;
import net.nander.synth.synthModules.oscillators.SineOscillator;
import net.nander.synth.synthModules.outputs.Speaker;

public class Octaver extends Modulator{

    final ModuleInput input;

    public Octaver(){
        input = new ModuleInput();
    }

    @Override
    public void run() {
        byte[] in = input.readAndFlip();
        for(int i=0;i<in.length;i++){
            in[i] = (byte) Math.abs(in[i]);
        }
        moduleOutput.write(in);
    }

    @Override
    public ModuleInput getModuleInput(int addr) {
        return input;
    }

    public static void main(String[] args){
        Oscillator osc = new SineOscillator();
        Octaver oct = new Octaver();
        ConsumerModule s = new Speaker();
        osc.connect(oct);
        oct.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(oct);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}
