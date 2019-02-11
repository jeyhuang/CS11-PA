/*******************************************************************
/*                              isPrime
/********************************************************************
/* Author: JENNY HUANG
/* Date Created: 04 February 2017
/* Last Modified: 04 February 2017
/* Description: This program takes an integer argument from the 
/*   command line and determines if a number is prime.
 *******************************************************************/
import java.lang.Math;

public class isPrime {
  private int check;
  private boolean prime = false;
  
  public isPrime(int input) {
     double count = Math.ceil(Math.sqrt(input));
  
    for (int i = 2; i <= count; i++) {
      check = input % i;
      if (check == 0) {
        prime = true;
        System.out.println(input + " is not a prime number");
        return;
      }
    }
    if (prime == false) {
      System.out.println(input + " is a prime number");
    }  
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("usage: isPrime int");
      System.exit(-1);
    }
    int input = Integer.parseInt(args[0]);
    if (input < 0) {
      System.out.println("Enter a positive integer");
      System.exit(-1);
    }
    new isPrime(input);
  }
}
