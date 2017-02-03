/********************************************************
* Tombstone.java 
*
* Author: JENNY HUANG
* Creation Date: 01 February 2017
* Last Modified: 01 February 2017
* Description:  Creates a pixel image of a tombstone.
* 		The tombstone moves across the cemetery at
* 		a fixed Y coordinate.
*
* 		It is an ActiveObject 
*
* Build:   javac -classpath '*':'.' Tombstone.java
* Dependencies:  objectdraw.jar, java.awt.*
* 
* Public Methods Defined:
*           Tombstone(Location, double, double, 
*           	Drawable2DInterface, SpaceController, DrawingCanvas)
*           void setVelocity(double velX)
*           void enter(Goblin wraith)
*           void run()
*           boolean overlaps(Drawable2DInterface region)
* 
* Public Class Variables:
*       None
*
***********************************************************************/
import objectdraw.*;
import java.awt.*;
import java.util.Random;
public class Tombstone extends ActiveObject
{
	private VisibleImage stone;
	private Image stoneGraphic;
	private double vx; 	// Current speed of stone in pixels/ms
	private DrawingCanvas canvas;
	private CemeteryController myController;
	private Random myRnd;

	private static final String GRAPHIC = "tombstone.jpg";
	
	// Note how the constant in CemeteryController is referenced.
	private static final int SCALE = CemeteryController.SCALE;
  private static final double PROB = CemeteryController.PROBABILITY;
	
	/***** Constructor 
	 *         Location initial 	Where to create the stone on the canvas
	 *         double velX  	initial velocity x (pixels/ms)	
	 *         CemeteryController control   my controller 
	 *         DrawingCanvas canvas The objectdraw canvas
	 *****/ 
	public Tombstone(Image stoneImage, Location initial, double velX, 
			 CemeteryController control, DrawingCanvas aCanvas)	{
		// Load the image
		stone = new VisibleImage(stoneImage, initial, aCanvas);
		canvas = aCanvas;
    scaleAndPlace();
		
    vx = velX;
		myController  = control;
		myRnd = new Random();
  
		start(); // For ActiveObjects, this will eventually call run()
   	}
   
	/***** Method: scaleAndPlace()
	* Scale the image so that its width is 1/SCALE of the canvas width
	* Scale the height by the same factor 
	*****/ 
	private void scaleAndPlace()
	{
		double stoneWidth = stone.getWidth();
    double stoneHeight = stone.getHeight();
		// IMPLEMENT: Calculate scale factor and scale
    stone.setWidth( canvas.getWidth() / SCALE );
    stone.setHeight( stone.getWidth() * stoneHeight / stoneWidth );
	}	


	/***** Method: void moveIt(double)
	*  Move the tombstone, based on elapsed time and speed.
	*  roll around edges of canvas
	*	double elapsed -- elapsed time in ms since last update
	*****/ 
	private void moveIt(double elapsed)
	{ 
		double stoneWidth;
		double x,y, newX, dx;
		stoneWidth = stone.getWidth();
		x = stone.getLocation().getX();
		y = stone.getLocation().getY();
		// IMPLEMENT 
		//
		// Figure out the new  location based upon
		// elapsed time and speed.

		// make sure to "wrap" at the right and left side of the canvas 
		
		// Move the stone the calculated position
    stone.move(vx * elapsed, 0.0);
    newX = stone.getX();
    if (x + (vx * elapsed) > canvas.getWidth()){
      stone.moveTo( canvas.getWidth() - stone.getWidth(), y);
      stone.moveTo( 0 - stoneWidth, y);
    }
	}

	/***** Method: void setVelocity(vx)
	* 	Set the velocity	
	*****/ 
	public void setVelocity (double velX) 
	{
		vx = velX; 
	}

	/***** Method: public void enter(Goblin wraith))
	*  
	*****/ 
	// Decide what to do when a goblin intersects us 
	public void enter(Goblin wraith)
	{	
		// Make sure that this is random action in terms of when
		// to vaporize the wraith.
    double i = myRnd.nextDouble();
    System.out.println("chance: " + i);
    if (i < PROB) wraith.vaporize();
  }	

	/***** Method: public void run()
	* ActiveObject run() method
	*****/ 
	public void run() 
	{
        	double prevClock, now, elapsed;
		double tmpvx; 
		double delay; 
		double thresh=1e-2;

		// calculate the pause delay time based upon speed
		// want to pause no more than ~10 milliseconds, update more 
		// quickly if velocity is faster
		
		tmpvx = Math.abs(vx);
		if (tmpvx < thresh) 
			tmpvx = thresh;
		delay = 1/tmpvx;

		System.out.println("delay = " + delay );
        	prevClock = System.currentTimeMillis();
		
		// Note: infinite loop. Will exit when main program 
		// (controller) exits.
		while (true)
		{
			// calc elapsed time, update, and moveto new position
			// check if we entered the intersect regioon
			now = System.currentTimeMillis();
			elapsed = now - prevClock;
			prevClock = now;
			moveIt(elapsed);
			pause(delay);
			
		}
	}

	// Method: determine if a Drawable2Dinterface intersects the stone.
	public boolean overlaps(Drawable2DInterface region)
	{
		
    return stone.overlaps(region); 
	}
}
