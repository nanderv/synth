package synthModules.modulators;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.LinkedList;
import java.util.Queue;

public class AddModule extends Modulator {
    PipedInputStream inputStream;
    PipedInputStream secondStream;
    Queue<Byte> queue = new LinkedList<>();
    @Override
    public void disconnect() {
        inputStream = null;
        secondStream = null;

    }

    @Override
    public void setByteStream(PipedInputStream stream) {
        if(inputStream == null) {
            inputStream = stream;
        } else {
            secondStream = stream;
        }
    }

    @Override
    public void run() {
        byte[] bytes = new byte[1];
        byte[] bytes2 = new byte[1];
        while(true) {
            try {
                if (inputStream != null) {
                    inputStream.read(bytes);
                    byte samp = (bytes[0]);
                    if(secondStream != null) {
                        secondStream.read(bytes2);
                        samp /= 2;
                        samp += bytes2[0] / 2; //(byte) ((bytes[0] + 128 ) * 2 % 256 - 128);
                    }
                    stream.write(samp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
