package synthModules;

import static main.Config.SAMPLES_PER_TICK;

public class ModuleInput {
    int readIndex = 0;
    int writeIndex = 1;
    byte[][] slots;
    final Object lock = new Object();
    public ModuleInput(){
        slots = new byte[2][SAMPLES_PER_TICK];
    }
    public void writeAndFlip(byte[] data){
            slots[writeIndex] = data;
            writeIndex = (writeIndex + 1) % 2;
    }
    public byte[] readAndFlip(){
            byte[] data = slots[readIndex];
            readIndex = (readIndex + 1) % 2;
            return data;
    }
}
