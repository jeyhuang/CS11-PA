/************************************************************
/*                  FileReaderAndCount.java
*************************************************************
/* Author: JENNY HUANG
/* Date Written: 03 February 2017
/* Last Modified: 04 February 2017
/* Description: This program reads a file entered in   a text
/*  box and displays its content into a text area. This 
/*  program can also count the number of characters in a file
/*  text. 
************************************************************/
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;

public class FileReaderAndCount extends JFrame
    implements ActionListener {
  
  private static int ht = 20;
  private static int wdt = 40;

  private JTextField input; // input of file name
  private static JTextArea output; // output of file's content
  private JPanel upperPanel; // panel set north in main container
  private JPanel midPanel; // panel set center in main container
  private JPanel buttonPanel; // panel set south in main container
  private JButton clearButton; // button that clears output text 
  private JButton countButton; // button that counts # of char in text
  private JScrollPane scroll; // scroll pane for output
  private JLabel countLabel = new JLabel("# of characters in file: ");
                                  // label for count
  private static int numChar;
  
  public FileReaderAndCount() {
    setTitle("File Reader");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getFrameComponents();
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == input) {
      readFileByLine(input.getText());
      input.setText("");
      countLabel.setText("# of characters in file: ");
      pack();
    }

    if (e.getSource() == countButton) {
      countLabel.setText("# of characters in file: " + numChar);
    }

    if (e.getSource() == clearButton) { 
      output.setText("");
      numChar = 0;
      countLabel.setText("# of characters in file: ");
    }
  }

  private void getFrameComponents() {
    JLabel inputLabel = new JLabel("File Name: ");
                                  // label for input field
    JLabel outputLabel = new JLabel("File text: ");
                                  // label for output box
    upperPanel = new JPanel();
    midPanel = new JPanel();
    buttonPanel = new JPanel();

    input = new JTextField(wdt);
    input.addActionListener(this);

    output = new JTextArea(ht, wdt);
    output.setEditable(false);
    scroll = new JScrollPane(output);
    
    clearButton = new JButton("clear output");
    //clearButton.setPreferredSize(new Dimension(10, 80));
    clearButton.addActionListener(this);

    countButton = new JButton("count");
    //countButton.setPreferredSize(new Dimension(100, 80));
    countButton.addActionListener(this);
    
    //frame layout and display
    // lay out labels and text fields/areas
    midPanel.setLayout(new BoxLayout(midPanel,BoxLayout.PAGE_AXIS));
    midPanel.add(inputLabel); // file name label
    midPanel.add(input); // input field in center of upperPanel
    midPanel.add(Box.createRigidArea(new Dimension(0,5)));
    midPanel.add(outputLabel); // output label
    midPanel.add(scroll);// output in center of center 
    midPanel.add(Box.createRigidArea(new Dimension(0,10)));
    midPanel.add(countLabel); // count label
    midPanel.add(Box.createRigidArea(new Dimension(0,10)));
    midPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    add(midPanel, BorderLayout.NORTH);

    // lay out buttons
    buttonPanel.setLayout(new BoxLayout(buttonPanel, 
        BoxLayout.LINE_AXIS));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(clearButton); // clear button
    buttonPanel.add(Box.createRigidArea(new Dimension(10,10)));
    buttonPanel.add(countButton); // count button
    add(buttonPanel);

    validate();
    pack();
  }
    

  public static void readFileByLine (String fileName) {
    try {
      File file = new File (fileName);
      Scanner scanner = new Scanner(file);
      String contents = "";
      while (scanner.hasNext()) {
        contents += scanner.nextLine() + "\n";
      }
      scanner.close();
      numChar = contents.length();
      output.setText(contents);
    }
    catch(FileNotFoundException e) {
      output.setText("File not found");
    }
  }

  public static void main(String[] args) {
    FileReaderAndCount fr = new FileReaderAndCount(); 
  }
}
