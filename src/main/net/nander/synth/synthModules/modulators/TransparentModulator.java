package net.nander.synth.synthModules.modulators;

import net.nander.synth.synthModules.ModuleInput;

public class TransparentModulator extends Modulator{

    final ModuleInput input;

    public TransparentModulator(){
        input = new ModuleInput();
    }

    @Override
    public void run() {
        moduleOutput.write(input.readAndFlip());
    }

    @Override
    public ModuleInput getModuleInput() {
        return input;
    }
}
