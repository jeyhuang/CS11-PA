/*************************************************************************
/* WeightBox.java 
 * Author: Philip Papadopoulos
 * Course: CSE11 Fall 2013
 * Assignment: Program #1
 *
 * Date Written: 1  October 2013
 * Last Modified: 1 October 2013
 *
 * Description: 
 *		Implements the WeightBox class, a box at the end of a rope
 *		that can be raised or lowered	
 *
 *
 * Build:  javac -classpath '*':'.' WeightBox.java
 * Dependencies:  objectdraw.jar
 *
 * Public methods defined:
 * 		public void hoist (double l)
 * 		public void setColor (Color newColor)
 * 		public double getRopeLength()
 *
 * Public class variables:
 * 	None
 *
 **********************************************************************/
import objectdraw.*;
import java.awt.*;

public class WeightBox  {

private Line boxLine;
private FilledRect box;
private double lineX;
private double lineStartY;
private double lineEndY;

  // Constructor
  public WeightBox(Location anchorPoint, double ropeLength,
      double boxSize, DrawingCanvas canvas) {
    lineX = anchorPoint.getX();
    lineStartY = anchorPoint.getY();
    lineEndY = lineStartY + ropeLength;

    boxLine = new Line(lineX, lineStartY, lineX, lineEndY , canvas);
    box = new FilledRect(lineX - boxSize/2, lineEndY, boxSize,
      boxSize, canvas);
  }

  // Method: hoist(double deltaY)
  // 	raise (or lower) the box deltaY
  // 	do not raise above the anchorpoint
  public void hoist(double deltaY) {
      if ((lineEndY > lineStartY) || (deltaY > 0)) {
        lineEndY += deltaY;
        boxLine.setEnd(lineX, lineEndY);
        box.move(0, deltaY);
    }
      if ((
  }

  // Method: double getRopeLength() 
  //  	Return current length of the rope	
  public double getRopeLength() {
    return (lineEndY - lineStartY);
  } 

  // Method: setColor(Color newColor)
  // 	recolor the box
  public void setColor(Color newColor) {
    box.setColor(newColor);
  }

}
