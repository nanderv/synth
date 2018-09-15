package synthModules.oscillators;

/**
 * Converts a phase, between 0 and TAU to a value between 0 and 1
 */
public interface Generator {

    float generate(float phase);
}
