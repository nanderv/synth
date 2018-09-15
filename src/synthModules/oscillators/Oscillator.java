package synthModules.oscillators;

import synthModules.ProducerModule;

import java.io.IOException;

import static main.Config.FREQ_A;
import static main.Config.SAMPLING_RATE;

public abstract class Oscillator implements ProducerModule {
    float freq;
    int currentSample;
    float period;

    public abstract byte[] nextSample(int samples);

    public Oscillator(){
        setFreq(FREQ_A);
    }

    public Oscillator(float freq){
        setFreq(freq);
    }

    public Oscillator setFreq(float freq){
        this.currentSample =  (int) (currentSample* this.freq / freq);
        this.freq = freq;
        this.period = (float) SAMPLING_RATE / freq;
        return this;
    }
    public byte[] request(int size){
        return nextSample(size);
    }
}

