package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import GUI.CalculatorDisplay;
import calculations.ComplexNumber;
import calculations.Equation;
import util.InputParser;

/**
 * Listener class to handle events of buttons, events of the JFrame, and other components of the
 * GUI.
 * 
 * This Work complies with the JMU honor code
 * 
 * @author Andrew Fryer, Storm Behrens
 * @version 1.0 (03/23/2021)
 *
 */
public class CalcListener implements ActionListener, WindowListener
{
  private static CalcListener listener;
  private CalculatorDisplay frame;
  private Equation evaluate;
  private InputParser parser;

  /**
   * Default Constructor.
   */
  private CalcListener()
  {
    evaluate = Equation.getInstance();
    parser = parser.getInstance();

  } // Default Constructor.

  /**
   * Determines what actions to do following an action event.
   */

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    JButton button = null;
    frame = CalculatorDisplay.getInstance();

    if (e.getSource() instanceof JButton)
      button = (JButton) e.getSource();

    if (button != null)
    {
      switch (button.getName().toLowerCase())
      {
        case "add":
          operatorButton(button.getText());
          break;
        case "divide":
          operatorButton(button.getText());
          break;
        case "multiply":
          operatorButton(button.getText());
          break;
        case "subtract":
          operatorButton(button.getText());
          break;
        case "equals":
          operatorButton(button.getText());
          break;
        case "clear":
          frame.clearInputField();
          frame.validStatus(false);
          break;
        case "reset":
          resetDisplay();
          break;
        default:
          break;
      }
    }

  } // actionPerformed method.

  /**
   * clears InputField for CalculatorDisplay.
   */

  private void clearInput()
  {
    frame.clearInputField();
  }

  /**
   * gives the instance of the listener.
   * 
   * @return CalcListener - the listener
   */

  public static CalcListener getInstance()
  {
    if (listener == null)
      listener = new CalcListener();
    return listener;
  }

  /**
   * Processes the event for the operation buttons. (add,subtract,multiply,divide,equals)
   * 
   * @param operation
   *          - the operation thats taking place
   */

  private void operatorButton(final String operation)
  {
    JTextField inputField = frame.getInputField();
    String text;
    text = inputField.getText();
    if (text == null)
      text = "";
    if ((evaluate.operandEmpty() || !evaluate.operatorEmpty()) && text.length() == 0)
    {
      invalidInput();
    }
    else
    {
      try
      {
        operationsProcessor(text, operation);
        frame.validStatus(false);
      }
      catch (NumberFormatException e)
      {
        invalidInput();
      }
    }
  }

  /**
   * signals that the input is invalid.
   */

  private void invalidInput()
  {
    frame.validStatus(true);
  }

  /**
   * Logic processor for performing operations and updating the display.
   * 
   * @param text
   *          - the text from the inputField
   * @param operation
   *          - the operation thats taking place
   * @throws NumberFormatException
   *           the Input was invalid.
   */

  private void operationsProcessor(final String text, final String operation)
      throws NumberFormatException
  {

    JLabel display = frame.getDisplay();
    String equalsOperator = "=";

    if (evaluate.operatorEmpty() && text.length() > 0)
    {
      ComplexNumber op1 = parser.parseInput(text);
      frame.clearDisplay();
      if (operation.equals(equalsOperator))
      {
        display.setText(op1.toString() + operation + op1.toString());
        frame.adjustFont();
      }
      else
      {
        display.setText(op1.toString() + operation);
        frame.adjustFont();
        evaluate.setOperator(operation);
      }
      evaluate.setFirstOp(op1);
      clearInput();
    }
    else if (!evaluate.operandEmpty() && evaluate.operatorEmpty() && text.length() == 0)
    {
      frame.clearDisplay();
      if (operation.equals(equalsOperator))
      {
        display.setText(
            evaluate.getFirstOp().toString() + operation + evaluate.getFirstOp().toString());
        frame.adjustFont();
      }
      else
      {
        evaluate.setOperator(operation);
        display.setText(evaluate.getFirstOp().toString() + operation);
        frame.adjustFont();
      }
    }
    else if (!evaluate.operandEmpty() && !evaluate.operatorEmpty() && text.length() > 0)
    {
      ComplexNumber op2 = parser.parseInput(text);
      evaluate.setSecondOp(op2);
      evaluate.solve();
      if (operation.equals(equalsOperator))
      {
        display.setText(
            display.getText() + op2.toString() + operation + evaluate.getFirstOp().toString());
        frame.adjustFont();
      }
      else
      {
        display.setText(display.getText() + op2.toString() + operation);
        frame.adjustFont();
        evaluate.setOperator(operation);
      }
      clearInput();
    }
    else
    {
      invalidInput();
    }

  }

  /**
   * resets the display and the equation class.
   */

  private void resetDisplay()
  {
    frame.clearDisplay();
    evaluate.setFirstOp(null);
    evaluate.setSecondOp(null);
    evaluate.setOperator(null);
    frame.adjustFont();
  }

  @Override
  public void windowClosed(final WindowEvent e)
  {
    System.exit(0);
  } // windowClosed method.

  // ----------Unused Implemented Methods----------

  @Override
  public void windowOpened(final WindowEvent e)
  {
  } // unused.

  @Override
  public void windowClosing(final WindowEvent e)
  {
  } // unused.

  @Override
  public void windowIconified(final WindowEvent e)
  {
  } // unused.

  @Override
  public void windowDeiconified(final WindowEvent e)
  {
  } // unused.

  @Override
  public void windowActivated(final WindowEvent e)
  {
  } // unused.

  @Override
  public void windowDeactivated(final WindowEvent e)
  {
  } // unused.

} // CalcListener class.
