package synthModules;

import static main.Config.SAMPLES_PER_TICK;

public class ModuleInput {
    int currentSlot = 0;
    byte[][] slots;
    final Object lock = new Object();
    public ModuleInput(){
        slots = new byte[2][SAMPLES_PER_TICK];
    }
    public void write(byte[] data){
        synchronized(lock) {
            slots[(currentSlot + 1) % 2] = data;
        }
    }
    public byte[] readAndFlip(){
        synchronized(lock) {
            byte[] data = slots[currentSlot];
            currentSlot = (currentSlot + 1) % 2;
            return data;
        }
    }
}
