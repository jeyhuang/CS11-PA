/**************************************************************
/*                        Reverse.java
/**************************************************************
/* Author: JENNY HUANG
/* Date Created: 06 February 2017
/* Last Modified: 07 February 2017
/* Description: This program takes a singge argument on
/*    the command lin that is the name of a text file and
/*    and prints the file in reverse line order.
/*************************************************************/
import java.awt.*;
import java.util.Scanner;
import java.io.*;

public class Reverse {
  private static int count;
  private static String fileName;
  private static File file;

  public Reverse(String fileName) {
    fileName = fileName;
    file = new File(fileName);
    getNumLine();
    fileContent();
  }

  private static void getNumLine() {
    try {
      Scanner scanner = new Scanner(file);
      count = 0;
      while (scanner.hasNextLine()) {
        count++;
        scanner.nextLine();
      }

      scanner.close();
    } 

    catch(FileNotFoundException e) {
      System.out.println("Cannot open file: " + fileName);
    }
  } 

  private static void fileContent() {
    String[] content = new String[count];
    try {
      Scanner scanner = new Scanner(file);
      for (int i = 0; i < content.length ; i++) {
        content[(content.length - 1 - i)] = scanner.nextLine();
      }
      scanner.close();
    }
    catch(FileNotFoundException e) {
      System.out.println("Cannot open file: " + fileName);
    }
    printContent(content);
  }

  public static void printContent(String[] content) { 
    for (int p = 0; p < content.length; p++) {
      System.out.println(content[p]);
    }
  }
  
  public static void main(String[] args) {
    if(args.length != 1) {
      System.out.println("usage: Reverse fileName");
      System.exit(-1);
    }
    new Reverse(args[0]);
  }
}
