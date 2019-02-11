public class SnakeTest {
  private static Snake s;

  private static void printCoord() {
    Coord[] snake = s.getSnake();
    for(int i = 0; i < s.getSize(); ++i) { 
      System.out.println(snake[i].toString());
    }
  }

  public static void main(String [] args) {
    Coord initial = new Coord(20, 0);
    int up = 0 ;
    int right = 1 ;
    int down = 2 ;
    int left = 3 ;

    s = new Snake(60, initial);
    printCoord();

    for (int i = 0 ; i < 4 ; ++i) {
      System.out.println(s.grow(down));
      printCoord();
    }

    System.out.println(s.grow(right));
    printCoord();

    for (int i = 0; i < 3; ++i) {
      System.out.println(s.move(up));
        printCoord();
    
    }

    System.out.println(s.grow(left));
    printCoord();

    System.out.println(s.move(down));
    printCoord();
  }

    
}
