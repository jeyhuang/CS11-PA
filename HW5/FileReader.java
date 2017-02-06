/************************************************************
/*                    FileReader.java
*************************************************************
/* Author: JENNY HUANG
/* Date Written: 03 February 2017
/* Last Modified: 04 February 2017
/* Description: This program reads a file entered in   a text
/*  box and displays its content into a text area.
************************************************************/
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;

public class FileReader extends JFrame
    implements ActionListener {

  private JTextField input; // input of file name
  private static JTextArea output; // output of file's content
  private JPanel mainPanel; // main panel
  private JPanel centerPanel; // center panel contained in main panel
  private JButton clearButton; // button that clears output text 
                         // when pressed
  private JScrollPane scroll;
  public FileReader() {
    setTitle("File Reader");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getFrameComponents();
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == input) {
      readFileByLine(input.getText());
      input.setText("");
    }
    else { 
      output.setText("");
    }
  }

  private void getFrameComponents() {
    JLabel inputLabel = new JLabel("File Name: ");
                                  // label for input field
    JLabel outputLabel = new JLabel("File text: ");
                                  // label for output box
    input = new JTextField(40);
    input.addActionListener(this);
    output = new JTextArea(20, 40 );
    scroll = new JScrollPane(output);
    output.setEditable(false);
    clearButton = new JButton("clear output");
    clearButton.addActionListener(this);

    add(inputLabel, BorderLayout.NORTH);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(input);
    mainPanel.add(outputLabel);
    mainPanel.add(scroll);
    add(mainPanel);

    add(clearButton, BorderLayout.SOUTH);

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
      output.setText(contents);
    }
    catch(FileNotFoundException e) {
      output.setText("File not found");
    }
  }

  public static void main(String[] args) {
    FileReader fr = new FileReader(); 
  }
}
