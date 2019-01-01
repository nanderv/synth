package net.nander.synth.synthModules.modulators;

import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.ModuleInput;
import net.nander.synth.synthModules.oscillators.Oscillator;
import net.nander.synth.synthModules.oscillators.SineOscillator;
import net.nander.synth.synthModules.outputs.Speaker;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Queue;

public class OctaverDown extends Modulator{

    final ModuleInput input;



    public OctaverDown(){
        input = new ModuleInput();
    }
    private Queue<Byte> buffer = new ArrayDeque<>();
    private byte prevIn = 0;
    private byte prevOut = 0;
    private boolean useBuffer = true;
    private boolean first = true;

    int x = 0;
    BufferedImage image = new BufferedImage(512,255, BufferedImage.TYPE_INT_RGB);

    @Override
    public void run() {
        byte[] in = input.readAndFlip();
        for(int i=0;i<in.length;i++) {

            //clear buffer at end of second input phase
            //This should match up with a single phase an octave down
            if (prevIn < 0 && in[i] >= 0) {
                prevIn = 0;
                if (!first) {
                    buffer.clear();
                    first = true;
                } else {
                    first = false;
                }
            }

            prevIn = in[i];
            buffer.add(in[i]);

            if(useBuffer){
                in[i] = buffer.poll();
                prevOut = in[i];
                useBuffer = false;
            }else{
                //Calculate average between previous and next buffer value
                in[i] = (byte) ((prevOut + buffer.peek()) /2);
                useBuffer = true;
            }
        }
        moduleOutput.write(in);
    }

    @Override
    public ModuleInput getModuleInput(int addr) {
        return input;
    }

    public static void main(String[] args){
        Oscillator osc = new SineOscillator().setFreq(880f);
        OctaverDown oct = new OctaverDown();
        ConsumerModule s = new Speaker();
        osc.connect(oct);
        oct.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(oct);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }

}
