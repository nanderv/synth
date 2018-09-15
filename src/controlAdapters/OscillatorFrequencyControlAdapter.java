package controlAdapters;

import synthModules.oscillators.Oscillator;

public class OscillatorFrequencyControlAdapter extends FloatControlAdapter {
    private Oscillator oscillator;
    public OscillatorFrequencyControlAdapter(Oscillator o)
    {
        super(300,500);
        oscillator = o;
    }
    @Override
    void setValueSafely(float value) {
        oscillator.setFreq(value);
    }
}
