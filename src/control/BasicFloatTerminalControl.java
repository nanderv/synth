package control;

import controlAdapters.FloatControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Float.parseFloat;

public class BasicFloatTerminalControl {
    private FloatControl floatControl;
    public BasicFloatTerminalControl(FloatControl f){
        floatControl = f;

    }
    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String line = in.readLine();
            float f = Float.parseFloat(line);
            floatControl.setValue(f);

        }

    }

}
