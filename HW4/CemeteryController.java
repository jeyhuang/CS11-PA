/*****************************************************
CemeteryController.java
Author: JENNY HUANG
Creation Date: 30 January 2017
Last Modified: 30 January 2017
Description: Interface

  public void record(Goblin, boolean)
*****************************************************/
import objectdraw.*;
import java.awt.*;

public interface CemeteryController{
  public void record(Goblin wraith, boolean vaporized);
  public static final double PROBABILITY = 0.5;
  public static final int SCALE = 20;
  public static final double MAXV = 0.3;
  public static final int SIZE = 600; 
}
