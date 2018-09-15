package synthModules;

import java.io.InputStream;

public interface ConsumerModule extends Runnable {
    void disconnect();
    void setByteStream(InputStream stream);
}
