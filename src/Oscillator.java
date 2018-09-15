public abstract class Oscillator {

    static float freq = Note.FREQ_A;
    int currentSample = 0;

    public abstract byte[] nextSample(int samples);

    public Oscillator(float freq){
        this.freq = freq;
    }

    public void setFreq(float freq){
        currentSample =  (int) (currentSample * this.freq / freq);
        this.freq = freq;
    }
}

