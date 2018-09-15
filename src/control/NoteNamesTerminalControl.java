package control;

import controlAdapters.FloatControlAdapter;
import utils.MidiConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NoteNamesTerminalControl implements StringBasedControl {
    private FloatControlAdapter floatControl;
    public NoteNamesTerminalControl(FloatControlAdapter f){
        floatControl = f;

    }
    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = in.readLine();
            doAction(line);
            System.out.println("OK");

        }

    }

    @Override
    public void doAction(String line) {
        float f = MidiConverter.toFreq(Integer.parseInt(line));
        floatControl.setValue(f);
    }
}
