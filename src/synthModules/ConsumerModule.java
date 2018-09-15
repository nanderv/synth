package synthModules;

import java.io.PipedInputStream;

public interface ConsumerModule extends Runnable {
    void disconnect();
    void setByteStream(PipedInputStream stream);
}
