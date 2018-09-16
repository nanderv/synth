package synthModules.outputs;

import controlAdapters.FloatControlAdapter;
import synthModules.ConsumerModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;

import static main.Config.SAMPLING_RATE;

public class FloatControlOutput extends ConsumerModule implements Runnable {

    FloatControlAdapter adapter;


    public FloatControlOutput(FloatControlAdapter adapter){
        this.adapter = adapter;
    };

    @Override
    public void run() {
        long c = 16*((long)1000000000)/(SAMPLING_RATE );

        long t = System.nanoTime();
        long samples = 0;
        while(true) {
            if (System.nanoTime() > t + c * samples) {
                samples++;

                float val = (((float) getSample(1)[0] + 128))/256;
                adapter.setValue(adapter.lowerBound + (adapter.upperBound - adapter.lowerBound) * val);
            }
        }
    }
}
