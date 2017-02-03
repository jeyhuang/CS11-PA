/********************************************************
 * Goblin.java -- This class needs to be implemented
 *
 * Author: JENNY HUANG
 * Creation Date: 30 January 2017 
 * Last Modified: 31 January 2017
 * Description:  Creates a visible image of a goblin. 
 *    The goblin is launched and bounces from the top of the 
 * 		canvas. It calls back to the controller when it is either
 * 		vaporized or exits the canvas.
 *
 *               This is intended to be called by a CemeteryController.
 *
 * Build:   javac -classpath '*':'.'Goblin.java
 * Dependencies:  objectdraw.jar, java.awt.*
 * 
 * Public Methods Defined:
 *           Goblin(Image,Location, double, double, 
 *           	Tombstone, CemeteryController, DrawingCanvas)
 *           void vaporize()
 * 	    void setVelocity(double, double))
 * 	    void run() 
 * 
 * Public Class Variables:
 *       None
 *
 ***********************************************************************/
import objectdraw.*;
import java.awt.*;
import java.lang.Math;
import java.util.Random;

// Create a Goblin that bounces off the top row of the canvas 
// If the Goblin intersects this tombstone, it calls the enter() method of
// its  Tombstone.   When the goblin exits its run loop, it calls the record()
// method of the CemeteryController to tell it whether or not it has been
// vaporized. 

// Goblins at an initial velocities in the X and Y direction.
// Velocities are approximately pixels per ms

public class Goblin extends ActiveObject {
  // Some suggested instance variables. Others may still be needed
  private VisibleImage wraith;
  private Image wraithGraphic;
  private double vx, vy; 	// Current speed of wraith pixels/ms
  private DrawingCanvas canvas;
  private boolean vaporized = false;
  private boolean onCanvas = true;
  private Tombstone tombstone;
  private CemeteryController myController;
  private static double locationNow;
  private static double locationPast;
  private RandomDoubleGenerator rngVel;
  private Random rngProb = new Random(1);

  private static double cHeight;
  private static double cWidth;
  private static double widthScale;

  /***** Constructor 
   *         int selection	which image to load
   *         Location initial 	Where to create the goblin on the canvas
   *         double velX  	initial velocity x (pixels/ms)	
   *         double velY  	initial velocity y (pixels/ms)	
   *         Tombstone aStone     the tombstone 
   *         CemeteryController control   my controller 
   *         DrawingCanvas canvas The objectdraw canvas
   ****/ 
  public Goblin(Image selection, Location initial, 
      double velX, double velY, 
      Tombstone aStone, CemeteryController control,
      DrawingCanvas aCanvas) {
    vx = velX;
    vy = velY;
    tombstone = aStone;
    myController = control;
    cHeight = aCanvas.getHeight();
    cWidth = aCanvas.getWidth();
    widthScale = control.SCALE;
    rngVel = new RandomDoubleGenerator(-control.MAXV, control.MAXV);
    wraith = new VisibleImage(selection, initial, aCanvas);
    scaleAndPlace();
    start();
  }

  /***** Method: scaleAndPlace()
   * Scale the image so that its width is 1/SCALE of the canvas width
   * Scale the height by the same factor 
   *****/ 
  private void scaleAndPlace() {
    double goblinWidth = wraith.getWidth();
    double goblinHeight = wraith.getHeight();
    wraith.setWidth( cWidth / widthScale );
    wraith.setHeight( wraith.getWidth() * goblinHeight / goblinWidth);
  }

  /***** Method: void moveIt(double)
   *  Move the Goblin, based on elapsed time and speed.
   *  Bounce off the top edge of the canvas
   *	double elapsed -- elapsed time in ms since last update
   *****/ 
  private void moveIt(double elapsed)	{ 
    double oldX = wraith.getX();
    double oldY = wraith.getY();
    wraith.move(vx * elapsed, vy * elapsed);
    if ( oldY + (vy * elapsed) < 0 ) { 
      wraith.moveTo( oldX + (vx * elapsed) , 0 );
      vy = -vy; 
    }
  }

  /***** Method: void setVelocity(vx,vy)
                Set the velocity *****/ 
  public void setVelocity (double velX, double velY) {
    vx = rngVel.nextValue();
    vy = rngVel.nextValue();
  }

  /***** Method: public void vaporize()
   *  
   *****/ 
  // Tell the run loop to exit
  public void vaporize() {
    vaporized = true;
  }	

  /***** Method: public void run()
   * ActiveObject run() method
   *****/ 
  public void run() {
    double now, past, dt;
    boolean overlap = false;
    past = System.currentTimeMillis();
    double delay = 1 / Math.abs(Math.sqrt( Math.pow(vx, 2) + 
          Math.pow(vy, 2)));
    System.out.println(delay);

    while (onCanvas && !vaporized ) {
      now = System.currentTimeMillis();
      dt = now - past;
      past = now;
      moveIt(dt);
      pause(delay);

      if (wraith.getX() > cWidth ||
          (wraith.getX() + wraith.getWidth()) < 0 || 
          wraith.getY() > cHeight) {
        onCanvas = false;
      }

      if ((overlap == false) && tombstone.overlaps(wraith)) {
        tombstone.enter(this); 
        overlap = true;
      }
    }
    wraith.removeFromCanvas();
    myController.record(this, vaporized);
  }
}
