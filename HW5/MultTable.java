/***************************************************************
/*                          MultTable
/***************************************************************
/* Author: JENNY HUANG
/* Date Created:
/* Last Modified:
/* Decription: this program displays a multiplication table
/*    with a graphical interface. The table is rows x columns
/*    that is controlled by two different sldiers that range
/*    independently between 1 and 12.
 **************************************************************/
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;

public class MultTable extends JFrame
implements ChangeListener {
  private static int nRow;
  private static int nCol;
  private static String output = "";

  private static JLabel sliderLabel = new JLabel();
  private static JTextArea table;
  private static JSlider R;
  private static JSlider C;

  public MultTable(int width, int height) {
    setTitle("Multiplication Table");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getFrame(width, height);
    setVisible(true);
  }

  public void stateChanged(ChangeEvent e) {
    labelSlider();
    int x = C.getValue();
    int y = R.getValue();
    output = "";
    for(int i = 1; i <= y ; i++) {
      for (int j = 1; j <= x; j++) {
        int mult = i*j;
        format(mult);
      }
    output += "\n";
    }
  }

  private void getFrame(int w, int h) {
    table = new JTextArea(h,w);
    table.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    table.setEditable(false);
    format(1);
    // slider rows
    R = new JSlider(JSlider.VERTICAL , 1, 12, 1);
    R.setInverted(true);
    R.addChangeListener(this);
    R.setMajorTickSpacing(10); 
    // slider cols
    C = new JSlider(1, 12, 1);
    C.addChangeListener(this);
    C.setMajorTickSpacing(10); 

    labelSlider();
    add(sliderLabel, BorderLayout.NORTH);
    add(table);
    add(R, BorderLayout.EAST);
    add(C, BorderLayout.SOUTH);
    
    validate(); 
    pack();
    //int R.getValue() , int C.getValue()
  }

  private static void labelSlider() {
    sliderLabel.setText("Sliders: " + R.getValue() + 
        " , " + C.getValue());
  }

  private static void format(int a) {
    String S = "".format("%5d", a); 
    output += S;
    table.setText(output);
    //System.out.printf(output);
  }

  public static void main(String[] args) {
    new MultTable(65, 15);
  }
}
