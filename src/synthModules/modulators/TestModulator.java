package synthModules.modulators;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.LinkedList;
import java.util.Queue;
public class TestModulator extends Modulator {

    @Override
    public byte[] request(int size) {
        return request(0);
    }
}