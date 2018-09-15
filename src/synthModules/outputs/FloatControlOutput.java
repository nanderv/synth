package synthModules.outputs;

import controlAdapters.FloatControlAdapter;
import synthModules.ConsumerModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;

public class FloatControlOutput implements ConsumerModule {
    InputStream stream;
    FloatControlAdapter adapter;
    @Override
    public void disconnect() {
        stream = null;
    }

    @Override
    public void setByteStream(PipedInputStream stream) {
        this.stream = stream;
    }

    public FloatControlOutput(FloatControlAdapter adapter){
        this.adapter = adapter;
    };

    @Override
    public void run() {
        byte[] bytes = new byte[1];
        while(true){
            if(stream != null) {
                try {
                    stream.read(bytes);
                    float val = ((float) bytes[0]+1)/2;
//                    System.out.println(val);
                    adapter.setValue(adapter.lowerBound + (adapter.upperBound - adapter.lowerBound) * val);
                    Thread.sleep(1);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
