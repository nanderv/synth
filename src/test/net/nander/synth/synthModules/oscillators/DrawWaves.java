package net.nander.synth.synthModules.oscillators;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawWaves {
    static final String FILETYPE = "png";
    static final String OUTPUT_FOLDER = "./out/";

    public static void draw(Oscillator o, String filename) throws IOException {
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

        String path = OUTPUT_FOLDER + filename +"."+FILETYPE;
        File outputfile = new File(path);
        System.out.println("Exporting "+path);
        ImageIO.write(image, FILETYPE, outputfile);
    }

    @Test
    public void createImages(){
        try {
            draw(new PulseOscillator(),     "pulse");
            draw(new SawtoothOscillator(),  "sawtooth");
            draw(new SawtoothOscillator()
                    .setInverted(true),     "sawtooth inverted");
            draw(new SineOscillator(),      "sine");
            draw(new SquareOscillator(),    "square");
            draw(new SynthOscillator(),     "synth");
            draw(new TriangleOscillator(),  "triangle");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
