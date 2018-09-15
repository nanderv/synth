package controlAdapters;

import synthModules.oscillators.Oscillator;

public class OscillatorFrequencyControlAdapter extends FloatControlAdapter {
    private Oscillator oscillator;
    public OscillatorFrequencyControlAdapter(Oscillator o)
    {
        super(20,20000);
        oscillator = o;
    }
    @Override
    void setValueSafely(float value) {
        oscillator.setFreq(value);
    }
}
