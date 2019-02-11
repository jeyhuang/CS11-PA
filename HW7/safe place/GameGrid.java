/********************************************************************
/*                              GameGrid
/********************************************************************
/* Author:
/* Date Created: 12 February 2017
/* Last Modified: 
/* Description:
/*******************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class GameGrid /*extends JPanel*/ {
  private static Snake s;
  private static char[][] grid;
  private static int N;
  private static int M;
  private static int dir = 2;
  private static boolean isValid = true;

  public GameGrid(int N, int M) {
    grid = new char[N][M];
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < M; ++j) {
        grid[i][j] = '.';

        System.out.print(grid[i][j]);
      }
      System.out.println("");
    }
  }
  
  private static void resetGrid() {
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < M; ++j) {
        grid[i][j] = '.';
      }
    }
  }

  private static void update() {
    resetGrid();
    Coord[] snake = s.getSnake();
    
    grid[snake[0].getY()][snake[0].getX()] = 'H';
    for (int i = 1; i < s.getSize(); ++i) {
      grid[snake[i].getY()][snake[i].getX()] = '#';
    }
   
    System.out.println("\n");// for first stage codes only

    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < M; ++j) {
        System.out.print(grid[i][j]);
      }
      System.out.println("");
    }
  }

  private static void userInput(String input) {
    int up = 0;
    int right = 1;
    int down = 2;
    int left = 3;
    
    switch (input) {
      case "j": dir = (dir + left) % 4;
        if (!s.grow(dir)) System.exit(-1); // end game ;
          break;
      case "k": dir = (dir + right) % 4;
          if (!s.grow(dir)) System.exit(-1);
          break;
      default: if(!s.move(dir)) System.exit(-1);
    }
  }

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void main (String [] args) {
    N = Integer.parseInt(args[0]);
    M = Integer.parseInt(args[1]);
    String input = new String();
    new GameGrid (N, M);

    s = new Snake(N*M , new Coord(M/2, 0));
    update();

    Scanner scanner = new Scanner(System.in);

    while(scanner.hasNext() ) {
      // you can just do scanner.nextLine(), that returns a string
      input = new String(scanner.nextLine());
      if (input.equals("q")) return;
      clearScreen();
      userInput(input);
      update();
    }


  }

}

