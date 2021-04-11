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
import calculations.Operations;
import util.InputParser;

/**
 * Listener class to handle events of buttons, events of the JFrame, and other components of the
 * GUI.
 * 
 * This Work complies with the JMU honor code
 * 
 * @author Andrew Fryer, Storm Behrens
 * @version 1.0 (03/26/2021)
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
          clearInput();
          frame.invalidStatus(false, "no Error");
          break;
        case "reset":
          resetDisplay();
          break;
        case "inverse":
          if (evaluate.operatorEmpty()) 
          {
            if (evaluate.getFirstOp() == null) 
            {
              operatorButton(button.getText());
            } else 
            {
              operationsProcessor(evaluate.getFirstOp().toString(), button.getText());
            }
          } else 
          {
            frame.invalidStatus(true, "Can't Inverse.");
          }
          break;
        case "backspace":
          String text = frame.getInputField().getText();
          if (text.length() > 0) frame.setInput(text.substring(0, text.length() - 1));
          break;
        case "sign":
          break;
        case "decimal":
          append(".");
          break;
        case "i":
          append("i");
          break;
        case "0":
          append("0");
          break;
        case "1":
          append("1");
          break;
        case "2":
          append("2");
          break;
        case "3":
          append("3");
          break;
        case "4":
          append("4");
          break;
        case "5":
          append("5");
          break;
        case "6":
          append("6");
          break;
        case "7":
          append("7");
          break;
        case "8":
          append("8");
          break;
        case "9":
          append("9");
          break;
        case "logarithm":
          if (evaluate.operatorEmpty()) 
          {
            if (evaluate.getFirstOp() == null) 
            {
              operatorButton(button.getText());
            } else 
            {
              operationsProcessor(evaluate.getFirstOp().toString(), button.getText());
            }
          } else 
          {
            frame.invalidStatus(true, "Can't Take Logarithm.");
          }
          break;
        case "conjugate":
          if (evaluate.operatorEmpty()) 
          {
            if (evaluate.getFirstOp() == null) 
            {
              operatorButton(button.getText());
            } else 
            {
              operationsProcessor(evaluate.getFirstOp().toString(), button.getText());
            }
          } else 
          {
            frame.invalidStatus(true, "Can't Conjugate.");
          }
          break;
        default:
          break;
      }
    }

  } // actionPerformed method.
  
  /**
   * append - Will add a character to the end of the display.
   * 
   * @param addition
   *            ( String )
   */
  private void append(String addition)
  {
    frame.setInput(frame.getInputField().getText() + addition);
  } // append method.
  

  /**
   * clears InputField for CalculatorDisplay.
   */
  private void clearInput()
  {
    frame.clearInputField();
  }

  /**
   * formats input text for display.
   * 
   * **Should not modify input in a way that would change parse result**
   * 
   * @param input
   *          - the initial input
   * @return String - the formatted input
   */
  private String formatInput(final String input)
  {
    String text = input;
    String leftParen = "(";
    String rightParen = ")";
    String iPlus = "+i";
    String iMinus = "-i";
    String iParen = "(i";
    String imaginary = "i";
    if (!text.endsWith(rightParen) || !text.startsWith(leftParen))
    {
      text = leftParen + text + rightParen;
    }
    if (text.contains(iPlus) || text.contains(iMinus) || text.contains(iParen))
    {
      text = text.replace(iParen, "(1" + imaginary);
      text = text.replace(iPlus, "+1" + imaginary);
      text = text.replace(iMinus, "-1" + imaginary);
    }
    return text;
  }

  /**
   * signals that the input is invalid.
   */
  private void invalidInput()
  {
    frame.invalidStatus(true, "Invalid Input");
  }

  /**
   * Logic processor for performing operations and updating the display.
   * 
   * @param input
   *          - the text from the inputField
   * @param operation
   *          - the operation thats taking place
   * @throws NumberFormatException
   *           the Input was invalid.
   */
  private void operationsProcessor(final String input, final String operation)
      throws NumberFormatException
  {

    JLabel display = frame.getDisplay();
    String text = input;
    if (input != null && !input.isEmpty())
    {
      text = formatInput(input);
    }
    String equalsOperator = "=";

    if (evaluate.operatorEmpty() && text.length() > 0)
    {
      ComplexNumber op1;
      if (evaluate.getFirstOp() == null) 
      {
        op1 = parser.parseInput(text);
      } else 
      {
        op1 = evaluate.getFirstOp();
      }

      frame.setDisplay("");
      if (operation.equals(equalsOperator))
      {
        frame.setDisplay(text + operation + op1.toString());
      }
      // put the inverse sign here when the button is added.
      else if (operation.equals("Inv"))
      {
        ComplexNumber inv = op1.inverse();
        op1 = inv;
        frame.setDisplay(inv.toString());
      } 
      else if (operation.equals("log")) 
      {
        evaluate.setOperator("log");
        evaluate.setFirstOp(op1);
        String str = "log" + op1.toString();
        op1 = evaluate.solve();
        str += "=" + op1.toString();
        frame.setDisplay(str);    
      } 
      else if (operation.equals("Con")) 
      {
        ComplexNumber conj = op1.conjugate();
        op1 = conj;
        frame.setDisplay(op1.toString());
      }
      else
      {
        frame.setDisplay(text + operation);
        evaluate.setOperator(operation);
      }
      evaluate.setFirstOp(op1);
      clearInput();
    }
    else if (!evaluate.operandEmpty() && evaluate.operatorEmpty() && text.length() == 0)
    {
      frame.setDisplay("");
      if (operation.equals(equalsOperator))
      {
        frame.setDisplay(
            evaluate.getFirstOp().toString() + operation + evaluate.getFirstOp().toString());
      }
      else
      {
        evaluate.setOperator(operation);
        frame.setDisplay(evaluate.getFirstOp().toString() + operation);
      }
    }
    else if (!evaluate.operandEmpty() && !evaluate.operatorEmpty() && text.length() > 0)
    {
      ComplexNumber op2 = parser.parseInput(text);
      evaluate.setSecondOp(op2);
      evaluate.solve();
      if (operation.equals(equalsOperator))
      {
        frame.setDisplay(display.getText() + text + operation + evaluate.getFirstOp().toString());
      }
      else
      {
        frame.setDisplay(evaluate.getFirstOp().toString() + operation);
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
        frame.invalidStatus(false, "no");
      }
      catch (NumberFormatException e)
      {
        invalidInput();
      }
    }
  }

  /**
   * resets the display and the equation class.
   */
  private void resetDisplay()
  {
    frame.setDisplay("");
    evaluate.setFirstOp(null);
    evaluate.setSecondOp(null);
    evaluate.setOperator(null);
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
