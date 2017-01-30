/* WeightTest.java 
 * Author: Philip Papadopoulos
 * Course: CSE11 Fall 2013
 * Assignment: Program #1
 *
 * Date Written: 1  October 2013
 * Last Modified: 1 October 2013
 *
 * Description: A test program that can be used to test the WeigthBox class 
 *		The animation is driven by clicking the mouse button.
 *
 *		extends WindowController from objectdraw.
 *
 * Build:  javac -classpath '*':'.' WeightTest.java
 * Dependencies:  WeightBox.java, objectdraw.jar
 *
 * Public methods defined:
 * 	main (String[]) - main program method
 * 	begin() - initialize a weight box 
 * 	onMouseClick(Location) - action when mouse is pressed. Moves box 
 *
 * Public class variables:
 * 	None
 *
 **********************************************************************/
import objectdraw.*;
import java.awt.*;

public class WeightTest  extends WindowController
{

	// *** Constants defined by this class ***
	public static final int CANVAS_HEIGHT = 600;
	public static final int CANVAS_WIDTH = 400;
	public static final int BOXWIDTH = 40;
	public static final int STEP = 10;

	// *** Instance Variables ***  
	private WeightBox box;	 	
	private Text instructions;  	// Display of instructions
	
	// *** Methods ***
	// Method: begin
	// Make a WeightBox instance in the middle of canvas 
	public void begin() 
	{
		int midx = CANVAS_WIDTH/2;	// Middle of the Canvas 
		Location anchor = new Location(midx,10);
		double ropeLength = CANVAS_HEIGHT/4;
		box = new WeightBox( anchor,ropeLength,BOXWIDTH,canvas);
		box.setColor(Color.BLUE);
		instructions = new Text( "Click in top half to move box upwards", 
						20, CANVAS_HEIGHT - 100, canvas);
	}

	// Method: onMouseClick
	// Move the Box up or down a bit each time the mouse is clicked
	public void onMouseClick(Location point) 
	{
		instructions.hide();
		double delta = STEP;
		// Test if we clicked in the top half of the canvas
		// If so, raise the box instead of lowering it
		if ( point.getY() <= (CANVAS_HEIGHT)/2 )
			delta = -delta;
		box.hoist(delta);
	}
	
	// Method: onMouseExit
	//  	Show instructions
	// 
	public void onMouseExit(Location point)
	{
		instructions.show();
	}

	// Method: main
	// Make this a Java Application
	public static void main (String[] args) 
	{
		new WeightTest().startController(CANVAS_WIDTH,CANVAS_HEIGHT); 
	}
}
