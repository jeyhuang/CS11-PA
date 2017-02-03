/********************************************************
 * Cemetery.java 
 *
 * Author: JENNY HUANG
 * Creation Date: 31 January 2017
 * Last Modified: 02 February 2017
 * Description:  
 *	This is Cemetery that implements the CemeteryController interface
 *	that has functionality of launching goblins at a specific velocity
 *	based on a vector dependent upon a start X-coordinate and end X-
 *  and Y- coordinates of a mouse press and release.
 *
 * Build:   javac -classpath '*':'.' Cemetery.java
 * Dependencies:  objectdraw.jar, java.awt.*, Goblin.class, Tombstone.class
 * 
 * Public Methods Defined:
 *           Cemetery(int,Location, double, double, 
 *           	Tombstone, CemeteryController, DrawingCanvas)
 *           static void main(String[]))
 * 	    void record(Goblin, boolean))
 * 	    void onMouseClick(Location)
 * 	    void run() 
 * 
 * Interfaces Implemented:
 * 	CemeteryController
 *
 * Public Class Variables:
 *       None
 *
 ***********************************************************************/
import objectdraw.*;
import java.awt.*;
import java.util.Random;

public class Cemetery extends WindowController implements CemeteryController {
  private Goblin phantom;
  private Tombstone stone;
  private int count = 0;
  private int vaporizedCount = 0;
  private int passedCount = 0;
  private FilledOval theExit;
  private FilledRect vControl;
  private Random myRnd;
  private Image selectedImage;
  private Image ghost;
  private Image pumpkin;
  private Image skeleton;
  private Image tombstone;
  private double releaseX, releaseY;
  private Line vector;
  private Location init;
  private boolean initClick = false;
  private Text countText;

  private static final int SIZE = CemeteryController.SIZE;
  private static final int DIM = 20;
  private static final int INSTX = 10;
  private static final double MAXV = CemeteryController.MAXV;

  /* Begin method called when WindowController is constructed
   */
  public void begin() {       
    // display instructions
    new Text("Click to Create Goblins...", INSTX, INSTX, canvas);
    new Text("Click Square to change last goblin velocity...", 
        INSTX, 2*INSTX, canvas);
    new Text("Click Circle to  exit...", INSTX, 3*INSTX, canvas);
    vControl = new FilledRect(SIZE - DIM, DIM, DIM, DIM, canvas);
    theExit = new FilledOval(SIZE - DIM, 2*DIM, DIM, DIM, canvas);	
    myRnd = new Random();

    vector = null;
    ghost = getImage("ghost.jpg");
    pumpkin = getImage("pumpkin.jpg");
    skeleton = getImage("skeleton.gif");
    tombstone = getImage("tombstone.jpg");

    stone = new Tombstone( tombstone, new Location(0.0, SIZE * 0.25), 
        0.3, this, canvas);
    printCount();
  }

  // Method: onMouseClick() 
  public void onMouseClick(Location point) {
    double vx,vy;
    if (theExit.contains(point)) System.exit(0);
    //exit if circle is clicked
    if (vControl.contains(point) && phantom != null) {
      vx = (MAXV/2.0) - (myRnd.nextDouble() * MAXV);
      vy = (MAXV/2.0) - (myRnd.nextDouble() * MAXV);
      phantom.setVelocity(vx,vy); 
    }
  }

  public void onMousePress(Location point) {
    init = new Location( point.getX(), canvas.getHeight());
    if (!theExit.contains(point) && !vControl.contains(point)) {
      vector = new Line (init, point, canvas);
      vector.setColor(Color.PINK);
    }
  }

  public void onMouseDrag(Location point) {
    if (vector != null) vector.setEnd(point); 
  }

  public void onMouseRelease(Location point) {
    if (vector != null) {
      vector.removeFromCanvas();
      double x = point.getX();
      double y = point.getY();
      if (x > 0 && x < canvas.getWidth() &&
          y > 0 && y < canvas.getHeight()) {
        vector = null;
        double velX = MAXV *
          ( point.getX() - init.getX() ) / canvas.getWidth();
        double velY = MAXV *
          ( point.getY() - init.getY() ) / canvas.getHeight();
        double magV = Math.sqrt( Math.pow(velX, 2) + Math.pow(velY, 2));
        if (magV > Math.sqrt(2 * MAXV * MAXV)) {
          velX = Math.sqrt( 2 * MAXV * MAXV) * velX / magV;
          velY = Math.sqrt( 2 * MAXV * MAXV) * velY / magV;
        }
        switch (count++ % 3) {
          case 0: selectedImage = ghost;
                  break;
          case 1: selectedImage = pumpkin;
                  break;
          default: selectedImage = skeleton;
        }
        phantom = new Goblin (selectedImage, 
            new Location(init.getX(), canvas.getHeight()),
            velX, velY, (Tombstone) stone, this, canvas);
        printCount();
      }
    }
  }

  // Method: record() 
  // Implement for CemeteryController Interface Specification
  public void record(Goblin wraith, boolean vaporized) {
    if (vaporized) ++vaporizedCount;
    else ++passedCount;
    printCount();
  }

  // Method: 
  // Use to print count to screen
  private void printCount() {
    countText.setText("# of phantoms created: " + count +
        "\n# of phantoms vaporized: " + vaporizedCount +
        "\n# of phantoms passed: " + passedCount);
  }

  // Main Method
  static public void main(String[] args) {
    new Cemetery().startController(SIZE, SIZE);
  }
}
