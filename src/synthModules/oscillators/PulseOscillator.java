package synthModules.oscillators;

import synthModules.ConsumerModule;
import synthModules.outputs.Speaker;

public class PulseOscillator extends Oscillator {

    private float dutyCycle;

    public PulseOscillator() {
        super();
        this.dutyCycle = 0.5f;
    }

    /**
     * Set duty cycle between 0 and 1
     */
    public PulseOscillator setDutyCycle(float dutyCycle){
        this.dutyCycle = dutyCycle;
        return this;
    }

    @Override
    public byte[] nextSample(int samples) {
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = currentSample%period / period;
            float value = phase > dutyCycle ? 1f : 0f;
            sampleArray[i] = (byte) (value * amplitude);
        }
        return sampleArray;
    }

    public static void main(String[] args) {
        Oscillator osc = new PulseOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        new Thread(osc).start();
        new Thread(s).start();
    }
}
