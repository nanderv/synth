package synthModules;

import java.io.PipedInputStream;

public abstract class ConsumerModule {
    ProducerModule source;
    public byte[] getSample(int size) {
        return source.request(size);
    }
    public void listenTo(ProducerModule listener){
        this.source = listener;
    }
}
