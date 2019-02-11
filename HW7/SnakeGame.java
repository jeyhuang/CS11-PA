/*********************************************************************
/*                            SnakeGame.java
/*********************************************************************
/* Author:
/* Date Created: 14 February 2017
/* Last Modified:
/*
/********************************************************************/
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGame extends JFrame
implements ChangeListener, ActionListener, KeyListener {
  private static final int sS = 10;
  private static int score = 0;
  private static int hScore = 0;
  private static int v = 1;
  private static final int h = 400; // default size when starting game
  private static final int w = 500; // default size when starting game
  private static Snake s; 
  private static SnakeMover mover;
  private static SnakeGame sg;

  //frame components
  private static GameGrid gg;
  private static JPanel north;
  private static JPanel south;
  private static JSlider slider;
  private static JLabel sLabel;
  private static JLabel hLabel;
  private static JLabel gOver;
  private static JButton nGButton;
  private static JButton resetButton;

  public SnakeGame() {
    setTitle("Snake Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    createSnake(w, h);
    gg = new GameGrid (w, h, s, sS); 
    gg.addKeyListener(this);
    getFrame();
    setVisible(true);

    mover = new SnakeMover(s, gg, v);
    mover.start();
  }

  private void getFrame() {
    sLabel = new JLabel("Score: \t\t" + score);
    hLabel = new JLabel("High Score: \t\t" + hScore);
    gOver = new JLabel("GAME OVER!");
    slider = new JSlider(1, 20, 1);
    slider.addChangeListener(this);
    nGButton = new JButton("New Game");
    nGButton.addActionListener(this);
    resetButton = new JButton("Reset");
    resetButton.addActionListener(this);

    setBackground(Color.WHITE);

    // north panel setup 
    north = new JPanel();
    north.setBackground(new Color(207,207,207));
    north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
    north.setAlignmentX(LEFT_ALIGNMENT);
    north.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    // north add components
    north.add(sLabel);
    north.add(hLabel);
    add(north, BorderLayout.NORTH);

    add(gg);

    // south panel setup 
    south = new JPanel();
    south.setBackground(new Color(207,207,207));
    south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
    south.setAlignmentX(LEFT_ALIGNMENT);
    south.setBorder(BorderFactory.createEmptyBorder(5,8,5,8));
    slider.setBackground(new Color(207, 207, 207));
    // south add components
    south.add(nGButton);
    south.add(Box.createRigidArea(new Dimension(8,1)));
    south.add(resetButton);
    south.add(Box.createRigidArea(new Dimension(5,1)));
    south.add(slider);
    add(south, BorderLayout.SOUTH);

    validate();
    pack();
  }

  public void stateChanged(ChangeEvent e) {
    if (e.getSource() == slider) {
      mover.setVel(slider.getValue());
      gg.requestFocusInWindow();
    }
  }

  public void keyPressed(KeyEvent e) {
    switch (e.getKeyChar()) {
      case 'j': if(!mover.growS('j')) endGame();
        score += 10;
        repaint() ;
        break;
      case 'l': if(!mover.growS('l')) endGame();
        repaint() ;
        score += 10;
        break;
    }
  }

  public void keyReleased(KeyEvent e) {}

  public void keyTyped(KeyEvent e) {}

  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == nGButton) {
      endGame();
      newGame();
    }
    if (e.getSource() == resetButton) {
      reset();
    }
  }

  private void createSnake(int nX, int nY) {
    int x = nX / sS - 2;
    int y = nY / sS - 1;
    s = new Snake(x*y, new Coord(x / 2, 0));
  }

  private void newGame() {
    score = 0;
    remove(gg);
    int x = gg.getWidth();
    int y = gg.getHeight();
    //System.out.println(x + " , " + y);
    createSnake(x, y);
    gg = new GameGrid(x, y, s, sS);
    north.remove(gOver);
    gg.addKeyListener(this);
    add(gg);
    mover = new SnakeMover(s, gg, slider.getValue());
    mover.start();
    validate();
    pack();
    repaint();

    gg.requestFocusInWindow();
  }

  private void reset() {
    slider.setValue(1);
    hScore = 0;
    endGame();
  }

  private void endGame() {
    mover.stopMover();
    if (score > hScore) hScore = score;
    north.add(gOver);
    setVisible(true);
    }

  public static void main(String[] args) {
    SnakeGame sg = new SnakeGame();

    //game functions/rules:
    /* speed of snake increases every 100 points until max */
    /*while (v < 20) {
      if (score % 100 == 0) {
        v += 1;
        mover.setVel(v);
      }  
    }*/

  }
}
