package net.nander.synth.midi;
import net.nander.synth.notes.SynthBank;
import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.modulators.Mixer;
import net.nander.synth.synthModules.oscillators.Oscillator;
import net.nander.synth.synthModules.oscillators.SawtoothOscillator;
import net.nander.synth.synthModules.outputs.Speaker;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.*;
import java.util.Set;

public class MidiHandler {

    public MidiHandler(SynthBank bank) {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                //does the device have any transmitters?
                //if it does, add it to the device list
                System.out.println(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter

                for (int j = 0; j < transmitters.size(); j++) {
                    //create a new receiver
                    transmitters.get(j).setReceiver(
                            //using my own MidiInputReceiver
                            new Input(bank, device.getDeviceInfo().toString())
                    );
                }

                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new Input(bank, device.getDeviceInfo().toString()));

                //open each device
                device.open();
                //if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo() + " Was Opened");


            } catch (MidiUnavailableException e) {
            }
        }


    }
    public static void main(String[] args){
        Set<Oscillator> oscs = new HashSet<>();
        Mixer m = new Mixer();
        for(int i=0;i<8; i++){
            SawtoothOscillator o = new SawtoothOscillator();
            oscs.add(o);
            o.connect(m, i);
            o.setAmplitude(0);
            Scheduler.addTaskDirectly(o);
        }

        ConsumerModule s = new Speaker();
        m.connect(s);
        Scheduler.addTaskDirectly(s);
        Scheduler.addTaskDirectly(m);
        SynthBank b = new SynthBank(oscs);


        new MidiHandler(b);
        Scheduler.configFreeRun();

    }
}