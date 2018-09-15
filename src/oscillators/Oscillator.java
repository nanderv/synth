package oscillators;

import utils.Note;

public abstract class Oscillator {

    static float freq = Note.FREQ_A;
    int currentSample = 0;
    int sampleRate;

    public abstract byte[] nextSample(int samples);

    public Oscillator(int sampleRate, float freq){
        this.freq = freq;
        this.sampleRate = sampleRate;
    }

    public void setFreq(float freq){
        currentSample =  (int) (currentSample * this.freq / freq);
        this.freq = freq;
    }
}

