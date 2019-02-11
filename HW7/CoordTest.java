import java.util.Random;

public class CoordTest {
  private static Random rnd = new Random();
  private static Coord[] c = new Coord[10];

  public static void main(String[] args) {
    c[0] = new Coord(0, 0);
    for (int i = 1; i < c.length; ++i) {
      c[i] = new Coord(rnd.nextInt(3), rnd.nextInt(3));
    }

    for (int i = 0; i < c.length; ++i) {
      System.out.println(c[i].getX() + " " + c[i].getY());
      System.out.println(c[i].toString());
    }
    for (int i = 1; i < c.length; ++i) {
      String s = new String("");
      if (c[0].equals(c[i])) s = "equals";
      else s = "not equals";
      System.out.println(s);
    }
  }
}
