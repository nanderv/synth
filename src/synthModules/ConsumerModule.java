package synthModules;

import scheduling.Task;

import java.io.PipedInputStream;

public interface ConsumerModule extends Task {
    public ModuleInput getModuleInput();
}
