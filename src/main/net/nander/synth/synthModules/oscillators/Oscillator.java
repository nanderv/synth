package net.nander.synth.synthModules.oscillators;


import net.nander.synth.Config;
import net.nander.synth.synthModules.ProducerModule;

import static net.nander.synth.Config.SAMPLES_PER_TICK;
import static net.nander.synth.Config.SAMPLING_RATE;

public abstract class Oscillator extends ProducerModule {
    float freq;
    int currentSample;
    float period;
    float amplitude;

    /**
     * @requires phase >=0 && phase < 1
     * @ensures result >= 0 && result < 1
     * @param phase Phase of wave
     * @return signal value that should be multiplied by <code>amplitude</code>
     */
    public abstract float generateSignal(float phase);

    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = (currentSample / period ) % 1f;
            float signal = generateSignal(phase);
            sampleArray[i] = (byte) (signal * amplitude);
        }
        return sampleArray;
    }

    public Oscillator(){
        setFreq(Config.FREQ_A);
        setAmplitude(64f);
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