/**************************************************************
/*                       ReverseBuf.java
/**************************************************************
/* Author: JENNY HUANG
/* Date Created: 07 February 2017
/* Last Modified: 07 February 2017
/* Description: This program takes a singge argument on
/*    the command lin that is the name of a text file and
/*    and prints the file in reverse line order.
/*************************************************************/
import java.awt.*;
import java.util.Scanner;
import java.io.*;

public class ReverseBuf {
  private static int BUFSIZE = 10;

  public ReverseBuf(String fileName) {
    String[] contentArray = new String[BUFSIZE];
    int i = 0;
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        if (i == contentArray.length) {
          contentArray = expandArray(contentArray, BUFSIZE);
        }
        contentArray[i] = scanner.nextLine();
        ++i;
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Cannot open file: " + fileName);
    }
    printContent(contentArray, i);
  }

  private static String [] expandArray(String [] array, int extend) {
    String[] retArray = new String[array.length + extend];
    for (int i = 0; i < array.length; i++) {
      retArray[i] = array[i];
    }
    return (retArray);
  }

  public static void printContent(String[] content, int i) { 
    for (int p = i - 1; p >= 0 ; --p) {
      System.out.println(content[p]);
    }
  }

  public static void main(String[] args) {
    if(args.length != 1) {
      System.out.println("usage: Reverse fileName");
      System.exit(-1);
    }
    new ReverseBuf(args[0]);
  }
}
