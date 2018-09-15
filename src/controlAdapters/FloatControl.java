package controlAdapters;

public abstract class FloatControl {
    public final float lowerBound;
    public final float upperBound;
    public FloatControl(final float lowerBound, final float upperBound){
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
