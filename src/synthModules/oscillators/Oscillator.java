package synthModules.oscillators;

import synthModules.ModuleOutput;
import synthModules.ProducerModule;

import java.io.IOException;

import static main.Config.FREQ_A;
import static main.Config.SAMPLES_PER_TICK;
import static main.Config.SAMPLING_RATE;

public abstract class Oscillator extends ProducerModule {
    float freq;
    int currentSample;
    float period;
    float amplitude;


    public abstract byte[] nextSample(int samples);

    public Oscillator(){
        setFreq(FREQ_A);
        setAmplitude(127f);
    }

    public Oscillator setFreq(float freq){
        this.currentSample =  (int) (currentSample* this.freq / freq);
        this.freq = freq;
        this.period = (float) SAMPLING_RATE / freq;
        return this;
    }

    /**
     * @requires amplitude >= 0f && amplitude <= 127f
     */
    public Oscillator setAmplitude(float amplitude){
        this.amplitude = amplitude;
        return this;
    }

    @Override
    public void run() {
            moduleOutput.write(nextSample(SAMPLES_PER_TICK));
        }

}

