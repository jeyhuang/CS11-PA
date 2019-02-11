/****************************************************************
/*                          Integrate
 *****************************************************************
/* Author: JENNY HUANG
/* Date Created: 05 February 2017
/* Last Modified: 05 February 2017
/* Description: this program calculates the area under a curve
using trapezoidal integration
 ****************************************************************/
public class Integrate {
  private static double area1 = 0.0;
  private static double area2 = 0.0;

  private static void trapInt(double B, int n) {
    double dx = (B - 0.0) / n;
    double x;

    for (double i = 0; i < B ; i += dx) {
      x = i;
      double eval = Function.eval1(x);
      area1 += 1.0 / 2 * dx * 
        ( Function.eval1(x + dx) + Function.eval1(x));
      area2 += 1.0 / 2 * dx * 
        ( Function.eval2(x + dx) + Function.eval2(x));
    }
      System.out.println ( "int(x^2 + 3) |0," + B +
          " = " + area1);
      System.out.println ( "int(-x^2 + x) |0," + B +
          " = " + area2);
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println( "usage: Integrate upperLim numOfTraps");
      System.exit (-1);
    }
    double upperLim = Double.parseDouble(args[0]);
    int numOfTraps = Integer.parseInt(args[1]);
    if (upperLim <= 0 || numOfTraps < 0) {
      System.out.println("arguments must be positive");
      System.exit (-1);
    }
      Integrate.trapInt(upperLim, numOfTraps);
  }

}

class Function {
  public static double eval1(double x) {
    return (x*x + 3);
  }

  public static double eval2(double x) {
    return (- x*x + x);
  }
}
