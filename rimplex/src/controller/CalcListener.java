package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.CalculatorDisplay;

/**
 * Listener class to handle events of buttons, events of the JFrame,
 * and other components of the GUI.
 * 
 * @author Andrew Fryer
 * @version 1.0 (03/23/2021)
 *
 */
public class CalcListener implements ActionListener, WindowListener
{
  
  /**
   * Default Constructor.
   */
  public CalcListener()
  {} // Default Constructor.
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    JButton button = null;
    
    if (e.getSource() instanceof JButton) button = (JButton) e.getSource();
    
    if (button != null)
    {
      CalculatorDisplay frame = CalculatorDisplay.getInstance();
      switch (button.getName().toLowerCase())
      {
        case "add":
          frame.addOperator(button.getLabel());
          break;
        case "divide":
          frame.addOperator(button.getLabel());
          break;
        case "multiply":
          frame.addOperator(button.getLabel());
          break;
        case "subtract":
          frame.addOperator(button.getLabel());
          break;
        case "equals":
          frame.validStatus(true);
          break;
        case "clear":
          frame.clearInputField();
          frame.validStatus(false);
          break;
        case "reset":
          frame.clearDisplay();
          break;
        default:
          break;
      }
    }
    
  } // actionPerformed method.
  
  @Override
  public void windowClosed(WindowEvent e)
  {
    System.exit(0); 
  } // windowClosed method.
  
  
  
  
  //----------Unused Implemented Methods----------
  
  
  
  
  @Override
  public void windowOpened(WindowEvent e)
  {} // unused.

  @Override
  public void windowClosing(WindowEvent e) 
  {} // unused.

  @Override
  public void windowIconified(WindowEvent e)
  {} // unused.

  @Override
  public void windowDeiconified(WindowEvent e)
  {} // unused.

  @Override
  public void windowActivated(WindowEvent e)
  {} // unused.

  @Override
  public void windowDeactivated(WindowEvent e)
  {} // unused.
 
} // CalcListener class.
