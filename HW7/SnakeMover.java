/***********************************************************************
/*                               SnakeMover
/***********************************************************************
/* Author:
/* Date Created: 15 February 2017
/* Last Modified:
/* Description:
/**********************************************************************/

import java.util.concurrent.TimeUnit;

public class SnakeMover extends Thread {

  private static final int up = 0;
  private static final int right = 1;
  private static final int down = 2;
  private static final int left = 3;
  private Snake s;
  private GameGrid gg;
  private long inc; 

  private int key;
  private int dir;
  private int v;
  private long delay;
  private long wait;
  private boolean valid = true;

  public SnakeMover(Snake s, GameGrid gg, int v) {
    this.s = s;
    this.gg = gg;
    this.v = v;
    setVel(v);
    inc = 0;
    dir = 2;
  }

  public boolean growS(char key) {
    switch (key) {
      case 'j': dir = (dir + left) % 4;
                if (!s.grow(dir)) return false;
                inc = 0;
                return true;

      case 'l': dir = (dir + right) % 4;
                if (!s.grow(dir)) return false;
                inc = 0;
                return true;
      default: return true;
    }
  }

  public void setVel(int v) {
    delay = 1000000 / v;
    wait = delay / 20;
  }

  public void stopMover() {
    valid = false;
  }

  public void run() {
    
    while (valid) {
      for (inc = 0; inc < 20; ++inc) {
        try { // pause for wait time
          TimeUnit.MICROSECONDS.sleep(wait);
        } catch (InterruptedException e) {}
      }

      if (!s.move(dir)) break;
      gg.revalidate();
      gg.repaint();
    }
  }
}
