/********************************************************************
/*                              GameGrid
/********************************************************************
/* Author:
/* Date Created: 12 February 2017
/* Last Modified: 15 February 2017 
/* Description:
/*******************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameGrid extends JPanel {
  private static Snake s;
  private static int N; // number of squares in y-direction
  private static int M; // number of squares in x-direction
  private static int dir = 2;
  private static int sS; // size of square
  private static int x;
  private static int y;

  public GameGrid(int w, int h, Snake s, int size) {
    this.s = s;
    sS = size;
    N = h / sS - 1;
    M = w / sS - 2;
    x = (w % sS + sS*2) / 2;
    y = (h % sS + sS) / 2;
   
    setPreferredSize(new Dimension(w, h));

    setFocusable(true);
    setVisible(true);
    requestFocusInWindow();
    
    repaint();
  }
  
  private static void resetGrid(Graphics g) {
    Coord[] sArray = s.getSnake();
    // snake's body
    for (int i = 1; i < s.getSize(); ++i) {
      drawSquares(sArray[i].getX(), sArray[i].getY(), Color.BLACK, g);
    }
    drawSquares(sArray[0].getX(), sArray[0].getY(), Color.RED, g); 
                                                  // snake's head
  }

  private static void drawSquares(int i, int j, Color c, Graphics g) {
    g.setColor(c);
    g.fillRect(i*sS + x, j*sS + y, sS, sS);
  }

  @Override
  protected synchronized void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.YELLOW);
    g.drawRect(x, y, M*sS, N*sS); // draw background
    
    resetGrid(g);

    // draw game border lines
    g.setColor(Color.BLACK);
    g.drawLine(x, y, x + M*sS, y); // draws top line
    g.drawLine(x, y + N*sS, x + M*sS, y + N*sS); // draws bottom line
    g.drawLine(x, y, x, y + N*sS); // draws left line
    g.drawLine(x + M*sS, y, x + M*sS, y + N*sS); //draws right line

  }
}

