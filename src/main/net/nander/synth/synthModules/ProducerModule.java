package net.nander.synth.synthModules;

import net.nander.synth.scheduling.Task;


public abstract class ProducerModule implements Task {
    public ModuleOutput moduleOutput;

    public ProducerModule(){
        moduleOutput = new ModuleOutput();
    }

    public void connect(ConsumerModule module){
        moduleOutput.setModuleInput(module.getModuleInput(0));
    }
    public void connect(ConsumerModule module, int addr){
        moduleOutput.setModuleInput(module.getModuleInput(addr));
    }

    public void disconnect(){
        moduleOutput.setModuleInput(null);
    }
}
