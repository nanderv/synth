package net.nander.synth.synthModules.oscillators;

import net.nander.synth.Config;
import net.nander.synth.scheduling.Scheduler;
import net.nander.synth.synthModules.ConsumerModule;
import net.nander.synth.synthModules.outputs.Speaker;
import net.nander.synth.utils.Chord;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ChordOscillator extends Oscillator {

    private Chord chord;
    private float[] periods;
    private Oscillator oscillator;

    public ChordOscillator() {
        super();
        this.setChord(Chord.MAJOR);
        this.setOscillator(new SineOscillator());
    }

    public ChordOscillator setChord(Chord chord){
        this.chord = chord;
        return this;
    }

    public ChordOscillator setOscillator(Oscillator oscillator){
        this.oscillator = oscillator;
        return this;
    }

    @Override
    public ChordOscillator setFreq(float frequency){
            super.setFreq(frequency);
            periods = new float[12];

            //Frequency factors:
            //0: 1.000000f (root)
            //1: 1.059463f (minor second)
            //2: 1.122462f (major second)
            //3: 1.189207f (minor third)
            //4: 1.259921f (major third)
            //5: 1.334839f (perfect fourth)
            //6: 1.414213f (augmented fourth/diminished fifth)
            //7: 1.498307f (perfect fifth)
            //8: 1.587401f (minor sixth)
            //9: 1.681792f (major sixth)
            //10:1.781797f (minor seventh)
            //11:1.887749f (major seventh)

            float freq_factor = 1f;
            for(int i=0; i<12; i++){
                periods[i] = freq_factor;
                freq_factor *= Config.SEMITONE_FREQ_FACTOR;
            }
            return this;
    }

    public byte[] nextSample(int samples){
        byte[] sampleArray = new byte[samples];

        for(int i=0; i<samples; i++) {
            currentSample++;
            float phase = currentSample / period;

            //First note of chord is always the root.
            //Root has regular amplitude
            byte[] notes = this.chord.notes();
            float value = this.oscillator.generateValue(phase);

            for(int j=1;j<notes.length;j++){
                //TODO: keep frequencies separated instead of combining them, making filter implementation trivial
                //Other notes of chord have half the amplitude of the root
                value += 0.5f *this.oscillator.generateValue(phase* periods[notes[j]]);
            }
            sampleArray[i] = ((byte) (value * amplitude));
        }
        return sampleArray;
    }

    @Override
    public float generateValue(float phase) {
        throw new NotImplementedException(); //There is no single value, as there are multiple frequencies
    }

    public static void main(String[] args) {
        Oscillator osc = new ChordOscillator();
        ConsumerModule s = new Speaker();
        osc.connect(s);
        Scheduler.addTaskDirectly(osc);
        Scheduler.addTaskDirectly(s);
        Scheduler.configFreeRun();
    }
}
