/*************************************************************
/*                        MatMul.java
/************************************************************/
/* Author: Jenny Huang
/* Date Created: 09 February 2017
/* Last Modified: 09 February 2017
/* Description: MatMul takes two randomly-generated square
/*		matrices of N dimensions and multiplies them together.
 *************************************************************/
import java.awt.*;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class MatMul {

  public MatMul(int n, double max) {
    double[][] A = randomMatrix(n, max);
    double[][] B = randomMatrix(n, max);
    double[][] C = multMatrix(n, A, B);
    double[] RowSum = SumRows(C);
    double[] ColSum = SumCols(C);
    double DiagSum = SumDiag(C); 

    //print to screen
    System.out.println("A = ");
    printMatrix(A);

    System.out.println("B = ");
    printMatrix(B);

    System.out.println("C = ");
    printMatrix(C);

    System.out.println("RowSum = ");
    printByRow(RowSum);

    System.out.println("ColSum = ");
    printByCol(ColSum);

    System.out.printf("DiagSum = %.3f\n" , DiagSum);

  }

  private static double[][] randomMatrix(int n, double max) {
    Random d = new Random();
    double[][] matrix = new double[n][n];
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[i].length; ++j ) {
        matrix[i][j] = (max - -max)*d.nextDouble() - max;
      }
    }
    return matrix;
  }

  private static double[][] multMatrix(int n, double[][] A, 
      double[][] B)  {
    double[][] C = new double[n][n];
    double sum = 0;
    for (int i = 0; i < C.length; ++ i ) {
      for (int j = 0; j < C[i].length; ++ j) {
        for (int k = 0; k < C.length; ++k) {
          sum += A[i][k]*B[k][j];
        }
        C[i][j] = sum;
        sum = 0;
      }
    }
    return C;
  }

  private static double[] SumRows(double[][] C) {
    double sum = 0;
    double[] rs = new double[C[0].length];

    for (int i = 0; i < C.length; ++i) {
      for (int j = 0; j < C[i].length; ++j) {
        sum += C[i][j];
      }
      rs[i] = sum; 
      sum = 0;
    }
    return rs;
  }

  private static double[] SumCols(double[][] C) {
    double sum = 0;
    double[] cs = new double[C.length];

    for (int i = 0; i < C.length; ++i) {
      for (int j = 0; j < C[i].length; ++j) {
        sum += C[j][i];
      }
      cs[i] = sum; 
      sum = 0;
    }
    return cs;
  }

  private static double SumDiag(double[][] C) {
    double sum = 0;
    for (int i = 0; i < C.length; ++i) {
      sum += C[i][i];
    }
    return sum;
  }

  private static void printMatrix(double[][] m) {
    for (int i = 0; i < m.length; ++ i) {
      for (int j = 0; j < m[i].length; ++j) {
        System.out.printf("\t%7.3f", m[i][j]);
      }
      System.out.println("");
    }
  }

  private static void printByRow(double[] r) {
    for (int i = 0; i < r.length; ++i) {
      System.out.printf("\t%7.3f\n", r[i]);
    }
  }

  private static void printByCol(double[] c) {
    for (int i = 0; i < c.length; ++i) {
      System.out.printf("\t%7.3f" , c[i]);
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    int N;
    double max;
    if (args.length != 2) {
      System.out.println("Usage: MatMul < N > < max >");
      System.exit(-1);
    }
    try {
      N = Integer.parseInt(args[0]);
      max = Double.parseDouble(args[1]);
      new MatMul(N, max);
    } catch (NumberFormatException e) {
      System.out.println("Usage: MatMul <int> <double>");
    }
  }
}
