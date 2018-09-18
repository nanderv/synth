package net.nander.synth.synthModules;

import net.nander.synth.scheduling.Task;

public interface ConsumerModule extends Task {
    public ModuleInput getModuleInput();
}
