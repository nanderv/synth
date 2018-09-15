package synthModules.outputs;

import synthModules.ConsumerModule;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.InputStream;
import java.io.PipedInputStream;

import static main.Config.SAMPLING_RATE;


public class Speaker implements ConsumerModule {
    InputStream stream;

    @Override
    public void disconnect() {
        stream = null;
    }

    @Override
    public void setByteStream(PipedInputStream stream) {
        this.stream = stream;
    }

    @Override
    public void run() {
        SourceDataLine line = null;

        try {
            final AudioFormat af = new AudioFormat(SAMPLING_RATE, 8, 1, true, true);
            line = AudioSystem.getSourceDataLine(af);

        line.open(af, SAMPLING_RATE);
        line.start();
        while(true){
            byte[] bytes = new byte[8];
            if(stream != null) {
                stream.read(bytes);
                line.write(bytes,0,8);

            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        line.drain();
        line.close();
    }
}
