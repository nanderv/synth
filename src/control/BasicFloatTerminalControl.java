package control;

import controlAdapters.FloatControlAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BasicFloatTerminalControl implements StringBasedControl {
    private FloatControlAdapter floatControl;
    public BasicFloatTerminalControl(FloatControlAdapter f){
        floatControl = f;

    }

    @Override
    public void doAction(String line) {
        float f = Float.parseFloat(line);
        floatControl.setValue(f);

    }
    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = in.readLine();
            doAction(line);
        }
    }

}
