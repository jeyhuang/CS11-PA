/********************************************************************
/*                              Snake.java
/*******************************************************************/
/* Author: JENNY HUANG
/* Date Created: 10 Febuary 2017
/* Last Modified: 
/* Description:
/*******************************************************************/
public class Snake {
  private int size; //counter for how many times snake has grown
  private Coord[] snake;

  public Snake(int maxLength, Coord initial) {
    snake = new Coord[maxLength];
    snake[0] = initial;
    size = 1;
  }

  public Coord[] getSnake() {
    return snake;
  }

  public int getSize() {
    return size;
  }

  public boolean grow(int dir) {
    Coord temp = snake [size - 1];
    if (!move(dir)) return false;
    snake[++size - 1] = temp;
    return true;
  }

  public boolean move(int dir) {
    Coord xy;
    switch (dir) {
      case 0: xy = new Coord( 0 , -1 );
        break;
      case 1: xy = new Coord( 1 , 0 );
        break;
      case 2: xy = new Coord( 0 , 1 ); 
        break;
      case 3: xy = new Coord( -1 , 0 );
        break;
      default: return false;
    }

    Coord n = new Coord(snake[0].getX() + xy.getX() ,
        snake[0].getY() + xy.getY());
    if (!isValid(n)) return false;

    for(int i = size - 1 ; i > 0 ; --i) {
      snake[i] = snake[i - 1];
    }
    snake[0] = n; // new head of snake
    return true;
  }

  private boolean isValid(Coord n) {
    for (int i = 0; i < size; ++i) {
      if (snake[i].equals(n)) return false;
    }
    return true;
  }
}
