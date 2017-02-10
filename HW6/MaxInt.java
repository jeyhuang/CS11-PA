/*************************************************************
/*                        MaxInt.java
/************************************************************/
/* Author: Jenny Huang
/* Date Created: 08 February 2017
/* Last Modified: 09 February 2017
/* Description: MaxInt reads a file of integers from a file
/*    and prints out the maximum integer in the file and the 
/*    number of times the maximum occurs.
 *************************************************************/
import java.awt.*;
import java.util.Scanner;
import java.io.*;

public class MaxInt {
  private static String fileName;
  private static int max;
  private static int BUFSIZE = 10;
  private static String output = "";

  private static int[] contentArray = new int[0];
  private static int i = 0; // array length

  public MaxInt() {
    contentArray = readFileInt();
    if (contentArray.length != 0) getMaxInt(contentArray);
    else output = "This file does not contain any integers.";
    System.out.println(output);
    printContent(); 
  }

  private static int[] readFileInt() {
    try {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        if (i == contentArray.length) {
          contentArray = expandArray(contentArray, BUFSIZE);
        }
        if (scanner.hasNextInt()) {
          String temp = scanner.nextLine();
          if (temp.equals("")) continue;
          else {
            contentArray[i] = Integer.parseInt(temp);
            ++i;
          }
        } else {
          scanner.nextLine();
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Cannot open file: " + fileName);
    }
    int[] content = new int[i];
    for (int j = 0; j < i; ++j) {
      content[j] = contentArray[j];
    }
    return content;
  }

  public static void printContent() { 
    for (int p = 0; p < i ; p++) {
      System.out.println(contentArray[p]);
    }
  }

  private static void getMaxInt(int[] content) { 
    int count = 0;
    int init = content[0];
    max = init;
    
    for (int j = 1; j < i ; j++) {

      if (max < content[j]) {
        max = content[j];
        count = 1;
      }
      else if  (max == content[j] ) {
        count += 1;
      }
    }
    if(init == max) count +=1;
    output = "Max integer " + max + " occurs " + count + " time(s)";
  }

  private static int[] expandArray(int [] array, int extend) {
    int[] retArray = new int[array.length + extend];
    for (int i = 0; i < array.length; i++) {
      retArray[i] = array[i];
    }
    return (retArray);
  }

  public static void main(String[] args) {
    if(args.length != 1) {
      System.out.println("usage: MaxInt fileName");
      System.exit(-1);
    }
    fileName = args[0];
    new MaxInt();
  }
}

