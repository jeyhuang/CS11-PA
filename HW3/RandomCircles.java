import objectdraw.*;
import java.awt.*;

public class RandomCircles extends WindowController { 
  static int ncircles;
  static int size;

  public void begin() {
    int diam = size / 10;
    RandomIntGenerator rngloc = new RandomIntGenerator(0, size - diam);
    RandomIntGenerator rngRGB = new RandomIntGenerator(0, 255);

    Location l; 
    Color c;
    for (int i = 0; i < ncircles; ++i) {
      c = new Color(rngRGB.nextValue(), rngRGB.nextValue(), rngRGB.nextValue());
      l = new Location(rngloc.nextValue(), rngloc.nextValue());
      new FilledOval(l, diam, diam, canvas).setColor(c);
    }
  }           

  public static void main(String [] args ) {   
    if (args.length > 2) {
      System.out.println("usage: RandomCircles numCircles canvasSize");
      System.exit(-1);
    }

    ncircles = Integer.parseInt(args[0]); 
    size = Integer.parseInt(args[1]);

    new RandomCircles().startController(size, size);
  }
}
