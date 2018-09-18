package synthModules;

import scheduling.Task;


public abstract class ProducerModule implements Task {
    public ModuleOutput moduleOutput;

    public ProducerModule(){
        moduleOutput = new ModuleOutput();
    }

    public void connect(ConsumerModule module){
        moduleOutput.setModuleInput(module.getModuleInput());
    }

    public void disconnect(){
        moduleOutput.setModuleInput(null);
    }
}
