package controlAdapters;

import synthModules.oscillators.Oscillator;

public class OscillatorFrequencyControlAdapter extends FloatControlAdapter {
    private Oscillator oscillator;
    public OscillatorFrequencyControlAdapter(Oscillator o)
    {
        super(40,6000);
        oscillator = o;
    }
    @Override
    void setValueSafely(float value) {
        oscillator.setFreq(value);
    }
}
