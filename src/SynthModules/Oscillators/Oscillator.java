package SynthModules.Oscillators;

import SynthModules.ProducerModule;

import java.io.IOException;

public abstract class Oscillator extends ProducerModule {
    static float freq;
    int currentSample = 0;
    final int SAMPLE_RATE;

    public abstract byte[] nextSample(int samples);

    public Oscillator(float freq, final int SAMPLE_RATE){
        this.freq = freq; this.SAMPLE_RATE = SAMPLE_RATE;
    }

    public void setFreq(float freq){
        currentSample =  (int) (currentSample* this.freq / freq);
        this.freq = freq;
    }
    @Override
    public void run() {
        byte[] bytes;
        while(true){


            if(stream != null) {
                try {
                    //System.out.println(otherStream.available());

                    int produce = 16;
                    bytes = nextSample(produce);

                    stream.write(bytes,0,produce);
//                    System.out.println("here");
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }

    }
}

