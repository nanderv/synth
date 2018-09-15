package controlAdapters;

import synthModules.oscillators.Oscillator;

public class OscillatorFrequencyControl extends FloatControl {
    private Oscillator oscillator;
    public OscillatorFrequencyControl(Oscillator o)
    {
        super(20,20000);
        oscillator = o;
    }
    @Override
    void setValueSafely(float value) {
        oscillator.setFreq(value);
    }
}
