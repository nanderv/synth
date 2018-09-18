package synthModules.outputs;

import synthModules.ConsumerModule;
import synthModules.ModuleInput;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static main.Config.SAMPLES_PER_TICK;
import static main.Config.SAMPLING_RATE;


public class Speaker implements ConsumerModule {
    public ModuleInput input;

    private SourceDataLine line;

    public Speaker()  {
        input = new ModuleInput();
        final AudioFormat af = new AudioFormat(SAMPLING_RATE, 8, 1, true, true);
        try {
            line = AudioSystem.getSourceDataLine(af);
            line.open(af, SAMPLING_RATE);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        line.start();
    }
    @Override
    public void run() {
            byte[] data = input.readAndFlip();
            line.write(data,0,SAMPLES_PER_TICK);
    }

    @Override
    public ModuleInput getModuleInput() {
        return input;
    }
}
