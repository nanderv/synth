package synthModules;

public class ModuleOutput {
    ModuleInput moduleInput;
    public void setModuleInput(ModuleInput input){
        this.moduleInput = input;
    }

    public void write(byte[] data){
        if(moduleInput != null)
            moduleInput.writeAndFlip(data);
    }
}
