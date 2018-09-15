package synthModules.outputs;

import synthModules.ConsumerModule;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.io.InputStream;
import java.io.PipedInputStream;

import static main.Config.SAMPLING_RATE;


public class Speaker extends ConsumerModule implements Runnable {
    @Override
    public void run() {
        SourceDataLine line = null;
        long samples = 0;
        try {
            final AudioFormat af = new AudioFormat(SAMPLING_RATE, 8, 1, true, true);
            line = AudioSystem.getSourceDataLine(af);

            line.open(af, SAMPLING_RATE);
            line.start();
            long t = System.nanoTime();
            long c = 8*((long)1000000000)/(SAMPLING_RATE );
            do {
                    if(System.nanoTime() > t + c*samples-2000000) {
                        samples = samples + 1;
                        line.write(this.getSample(8), 0, 8);
                    } else {
//                        System.out.println(c*samples + ": "+ (System.nanoTime() - t));
                    }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
