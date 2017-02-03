/************************************************************
			sumSquares.Java
*************************************************************
Author: JENNY HUANG
Course: CSE11
Assignment: Assignment 2

Date Written: 30 January 2017
Last Modified: 30 January 2017

Description: This program takes two command line arguments 
	and draws the number of (randomly-colored) circles 
	indicaed by the first argument onto a square canvas 
	size defined by the second argument.
	
************************************************************/
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
