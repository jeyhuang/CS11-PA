/************************************************************************
/*                              Coord.java
/***********************************************************************/
/* Author: JENNY HUANG
/* Date Created: 10 February 2017
/* Last Modified: 
/* Description: 
 ************************************************************************/
public class Coord {
  private int x;
  private int y;
  
  public Coord( int i , int j ) {
    x = i;
    y = j;
  }

  public Coord( Coord initial ) {
    x = initial.getX();
    y = initial.getY();
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }

  public boolean equals(Coord c) {
    return x == c.getX() && y == c.getY();
  }

  public String toString() {
    return "(" + getX() + " , " + getY() + ")";
  }
}
