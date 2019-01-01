package net.nander.synth.synthModules.modulators;

import net.nander.synth.Config;
import net.nander.synth.synthModules.ModuleInput;

import java.util.HashMap;
import java.util.Map;

public class Mixer extends Modulator {


    private Map<Integer, ModuleInput> inputs;

    public Mixer() {
        inputs = new HashMap<>();
    }

    @Override
    public void run() {
        byte[] out = new byte[Config.SAMPLES_PER_TICK];
        for (ModuleInput input : inputs.values()) {
            byte[] temp =  input.readAndFlip();
            for(int i=0;i<out.length;i++){
                out[i] += temp[i];
            }
        }
        moduleOutput.write(out);
    }

    @Override
    public ModuleInput getModuleInput(int addr) {
        if (inputs.containsKey(addr)) {
            return inputs.get(addr);
        } else {
            ModuleInput newInput = new ModuleInput();
            inputs.put(addr, newInput);
            return newInput;
        }
    }
}
