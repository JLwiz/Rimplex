package app;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The GUI/Driver to "Drive" our program.  As of version 3/24/2021 only the visual elements are in
 * place.  Listeners and such need to be added and implemented.
 * 
 * @author Benjamin Huber
 * @version 3/24/2021
 */
public class GUIDriver
{
  public static void main(String[] args) {
    // WINDOW THINGS
    JFrame window = new JFrame("Calculator");
    window.setVisible(true);
    window.setSize(360 ,150);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // ALLOCATING WINDOW SPACE
    JPanel calculatorDisplay = new JPanel();
    window.getContentPane().add(BorderLayout.NORTH, calculatorDisplay);
    JPanel buttonDisplay = new JPanel(new BasicOptionPaneUI.ButtonAreaLayout(true, 0));
    window.getContentPane().add(BorderLayout.SOUTH, buttonDisplay);
    // JPanel inputFieldDisplay = new JPanel(new BorderLayout());
    // window.getContentPane().add(BorderLayout.CENTER, inputFieldDisplay);
    
    // CREATING OUR BUTTONS
    JButton reset = new JButton("R");
    JButton clear = new JButton("C");
    JButton addition = new JButton("+");
    JButton subtraction = new JButton("-");
    JButton multiplication = new JButton("*");
    JButton division = new JButton("/");
    JButton equals = new JButton("=");
    
    // ADDING THEM TO LIST FOR EASIER INSTANTIATION AND MORE LEGIBLE CODE
    List<JButton> buttonsList = new ArrayList<JButton>();
    buttonsList.add(reset);
    buttonsList.add(clear);
    buttonsList.add(addition);
    buttonsList.add(subtraction);
    buttonsList.add(multiplication);
    buttonsList.add(division);
    buttonsList.add(equals);
    
    // ADDING THE BUTTONS TO THE PANEL
    for(JButton alpha : buttonsList) {
      alpha.setSize(400,400);
      buttonDisplay.add(BorderLayout.CENTER, alpha);
      
      // alpha.setVisible(false);
      // alpha.setVisible(true);
    }
    
    // ADDING THE "DISPLAY" TO THE PANEL
    JLabel display = new JLabel();
    calculatorDisplay.add(display);
    display.setText("DISPLAY");
    
    // ADDING THE INPUTFIELD TO THE PANEL
    JTextField inputField = new JTextField();
    window.add(BorderLayout.CENTER, inputField);
  }
}
