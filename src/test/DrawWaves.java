import net.nander.synth.synthModules.oscillators.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawWaves {
    static final String FILETYPE = "png";
    static final String OUTPUT_FOLDER = "./out/";

    public static void draw(Oscillator o) throws IOException {
        final int BLACK = 0;
        final int WHITE = Integer.MAX_VALUE;
        final int RED = 16711680;

        //Create image of 512 by 255 pixels
        BufferedImage image = new BufferedImage(512,255, BufferedImage.TYPE_INT_RGB);

        //set frequency to one period per 512 samples
        //o.setFreq(main.net.nander.synth.Config.SAMPLING_RATE/512);

        for(int x=0;x<512;x++){
            byte sample = o.nextSample(1)[0];
            for(int y=0;y<255;y++){
                image.setRGB(x,254-y, (y-127 == sample)
                    ? BLACK                    //value of sample
                    : (y == 127)
                        ? RED                  //y axis
                        : (x == 255)
                            ? RED              //centre x axis
                            : WHITE
                );
            }
        }

        String filename = OUTPUT_FOLDER + o.getClass().getSimpleName()+"."+FILETYPE;
        File outputfile = new File(filename);
        System.out.println("Exporting "+filename);
        ImageIO.write(image, FILETYPE, outputfile);
    }

    public static void createImages(){
        try {
            draw(new PulseOscillator());
            draw(new SawtoothOscillator());
            draw(new SineOscillator());
            draw(new SquareOscillator());
            draw(new SynthOscillator());
            draw(new TriangleOscillator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        DrawWaves d = new DrawWaves();
        d.createImages();
    }
}
