package controlAdapters;

public abstract class FloatControl {
    float lowerBound;
    float upperBound;
    public FloatControl(float lowerBound, float upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    abstract void setValueSafely(float value);

    public void setValue(float value){
        this.setValueSafely(Math.max(Math.min(upperBound, value), lowerBound));
    }

}
