/**************************************************************
/*                       SwapBytes.java
/**************************************************************
/* Author: JENNY HUANG
/* Date Created: 07 February 2017
/* Last Modified: 09 February 2017
/* Description: This program takes a singge argument on
/*    the command lin that is the name of a text file and
/*    and prints the file in reverse line order.
/*************************************************************/
import java.awt.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class SwapBytes {
  private static int BUFSIZE = 10;
  private static int size = 0;
  private static String fileName;

  public SwapBytes(int[] index) {
    String[] content = getFileContent();
    int length = 0; //length of string line
    int maxLength = 0; // longest line of char in string[]
    char[][] contentChar = getCharArray(content);
    if (index.length > 0 ) {
      char[][] swappedChar = swapChar(contentChar,index);
      //if (
      printContent(swappedChar);
    }
    else printContent(contentChar);
  }

  private static String[] getFileContent() {
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
    size = i;
    return contentArray;
  }

  private static String [] expandArray(String [] array, int extend) {
    String[] retArray = new String[array.length + extend];
    for (int i = 0; i < array.length; i++) {
      retArray[i] = array[i];
    }
    return (retArray);
  }

  private static char[][] getCharArray( String[] content ) {
    char[][] ca = new char[size][];
    for (int i = 0; i < size; ++i) {
      ca[i] = new char[content[i].length()];
      for (int j = 0 ; j < content[i].length(); ++j) {
        ca[i][j] = content[i].charAt(j);
      }
    }
    return ca;
  }

  private static char[][] swapChar(char[][] ca, int[] index) {
    char a;
    for (int i = 0; i < index.length; ++i) {
      int count = 0;
      if (index[i] < 0) continue;
      for (int j = 0; j < ca.length; ++j) {
        if ( index[i] > ca[j].length - 1 + count ) {
          count += ca[j].length;
        }
        else if (index[i] == ca[j].length - 1  + count) break;
        else {
          count = Math.abs(count - index[i]);
          a = ca[j][count];
          ca[j][count] = ca[j][count + 1];
          ca[j][count + 1] = a;
          break;
        }
      }
    }
    return ca;
  }

  public static void printContent(char[][] content) {
    for (int p = 0; p < size; p++) {
      System.out.println(content[p]);
    }
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: SwapBytes fileName indices " +
          "(separated by a space)");
      System.exit(-1);
    }
    fileName = new String(args[0]);
    int[] index = new int[args.length - 1];
    try { 
      for(int i = 0; i < index.length; ++i) {
        index[i] = Integer.parseInt(args[i+1]);
      }
    } catch (NumberFormatException e) {
      System.out.println("indices must be of type int");
      System.exit(-1);
    }

    new SwapBytes(index);
  }
}
