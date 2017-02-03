/************************************************************
			sumSquares.Java
*************************************************************
Author: JENNY HUANG
Course: CSE11
Assignment: Assignment 2

Date Written: 30 January 2017
Last Modified: 30 January 2017

Description: This program that takes two arguments  called
	low and high and computes the sume of the squares of
	the integer in the range [low, high].
************************************************************/
import java.lang.Math;

public class sumSquares {
  static int low;
  static int high;

  public static void calcSquares(int low, int high) {
    double sum = 0;
    for(int i = low; i <= high ; ++i ) {
      System.out.print(i + "^2 ");
      sum += Math.pow(i,2);
    }
    System.out.println("= " + sum);
  }

    public static void main(String [] args) {
      if (args.length > 2) {
        System.out.println("Usage: sumSquares (low,high)");
        System.exit(-1);
      }

      low = Integer.parseInt(args[0]);
      high = Integer.parseInt(args[1]);

      calcSquares(low, high);

    }
}
