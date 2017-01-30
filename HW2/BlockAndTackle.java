/* BlockAndTackle.java 
 * Author: Philip Papadopoulos
 * Course: CSE11 Fall 2013
 * Assignment: Program #1
 *
 * Date Written: 1  October 2013
 * Last Modified: 1 October 2013
 *
 * Description: A program that produces an animation of two weight blocks
 * 		on either side of a pulley. When you click on the left side
 * 		of the canvas, the left weight goes down, right side up. Vice
 * 		versa if you click on the right side  
 *
 *		The animation is driven by clicking the mouse button.
 *
 *		extends WindowController from objectdraw.
 *
 * Build:  javac -classpath '*':'.' BlockAndTackle.java
 * Dependencies:  WeightBox.java, objectdraw.jar
 *
 * Public methods defined:
 * 	main (String[]) - main program method
 * 	begin() - initialize the weight boxes and pulleys 
 * 	onMouseClick(Location) - action when mouse is pressed. Moves weights 
 *
 * Public class variables:
 * 	None
 *
 **********************************************************************/
import objectdraw.*;
import java.awt.*;

public class BlockAndTackle extends WindowController {

  public static final int CANVAS_HEIGHT = 600;
  public static final int CANVAS_WIDTH = 400;
  public static final int BOXWIDTH = 65;
  public static final int DIAM = 100;
  public static final int STEP = 33;

  // *** Instance Variable ***
  private Text instructions;
  private WeightBox box;
  WeightBox box1;
  WeightBox box2;
  int ropeLength = CANVAS_HEIGHT/4;

  // *** Methods ***
  // Method: begin
  // Make the pulley, put the blocks in the right place 
  public void begin() {
    Location anchor1 = new Location(CANVAS_WIDTH/2 - DIAM/2, DIAM);
    Location anchor2 = new Location(CANVAS_WIDTH/2 + DIAM/2, DIAM);
    instructions = new Text("Click box to add weight! :)",
      20, CANVAS_HEIGHT - 50, canvas);
    
    new FilledOval(CANVAS_WIDTH/2 - DIAM/2, DIAM/2, DIAM, DIAM, canvas);
    box1 = new WeightBox(anchor1, ropeLength, BOXWIDTH, canvas);
    box2 = new WeightBox(anchor2, ropeLength, BOXWIDTH, canvas);
    box1.setColor(Color.PINK);
    box2.setColor(Color.GREEN);
  }
  // Method: onMouseClick
  // Move  each box depending on whether we clicked left or right side of
  // canvas 
  public void onMouseClick(Location point) {
    instructions.hide();
    double delta = STEP;
    if (point.getX() >= CANVAS_WIDTH/2 && 
        box2.getRopeLength() < CANVAS_HEIGHT/2 &&
        box1.getRopeLength() > DIAM/2 + STEP) {
      delta = -delta;
      box1.hoist(delta);
      box2.hoist(-delta);
    }

    if (point.getX() <= CANVAS_WIDTH/2 &&
        box1.getRopeLength()  < CANVAS_HEIGHT/2 &&
        box2.getRopeLength() > DIAM/2 + STEP) {
      delta = -delta;
      box1.hoist(-delta);
      box2.hoist(delta);
    }
  }

  // Method: onMouseExit
  // Reset the location of the Suns 
  // 
  public void onMouseExit(Location point) {
  }

  // Method: main
  // Make this a Java Application
  public static void main (String[] args) {
    new BlockAndTackle().startController(CANVAS_WIDTH, CANVAS_HEIGHT);
  }
}
