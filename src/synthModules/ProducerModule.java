package synthModules;

import java.io.*;

public abstract class ProducerModule implements Runnable {
    ConsumerModule connectedTo;
    protected PipedOutputStream stream;
    protected PipedInputStream otherStream;

    public ProducerModule(){
    }

    public void connect(ConsumerModule module){
        try {
            stream = new PipedOutputStream();
            otherStream = new PipedInputStream(stream, 256);
            module.setByteStream(otherStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectedTo = module;
    }

    public void disconnect(){
        connectedTo.disconnect();
        connectedTo = null;

    }
}
