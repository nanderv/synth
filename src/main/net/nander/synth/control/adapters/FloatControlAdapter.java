package net.nander.synth.control.adapters;

public abstract class FloatControlAdapter {
    public final float lowerBound;
    public final float upperBound;
    public FloatControlAdapter(final float lowerBound, final float upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    abstract void setValueSafely(float value);

    public void setValue(float value){
        this.setValueSafely(Math.max(Math.min(upperBound, value), lowerBound));
    }
    public void setValuePercentage(float percentage) {
        setValue(lowerBound + (upperBound-lowerBound)*percentage/100);
    }
}
