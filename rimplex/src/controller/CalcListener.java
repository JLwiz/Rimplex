package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import GUI.ButtonPadPanel;
import GUI.CalculatorDisplay;
import GUI.HistoryWindow;
import GUI.PlaybackWindow;
import calculations.ComplexNumber;
import calculations.Equation;
import printing.PrintableHistory;
import printing.PrinterController;
import util.InputParser;
import util.Playback;

/**
 * Listener class to handle events of buttons, events of the JFrame, and other components of the
 * GUI.
 * 
 * This Work complies with the JMU honor code
 * 
 * @author Andrew Fryer, Storm Behrens
 * @version 2.0 (04/12/2021)
 *
 */
public class CalcListener implements ActionListener, KeyListener, WindowListener
{
  private static CalcListener listener;
  private CalculatorDisplay frame;
  private Equation evaluate;
  private InputParser parser;
  private Playback playback = null;
  private final String leftParen = "(";
  private final String rightParen = ")";
  private boolean inFractions;
  
  private String recording = "";

  /**
   * Default Constructor.
   */
  private CalcListener()
  {
    evaluate = Equation.getInstance();
    parser = InputParser.getInstance();
    this.inFractions = false;
  } // Default Constructor.

  /**
   * gives the instance of the listener.
   * 
   * @return CalcListener - the listener
   */

  public static CalcListener getInstance()
  {
    if (listener == null) listener = new CalcListener();
    return listener;
  }

  /**
   * Determines what actions to do following an action event.
   */
  @SuppressWarnings("unlikely-arg-type")
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    frame = CalculatorDisplay.getInstance();
    
    if (e.getSource() instanceof JButton)
      buttonActions((JButton) e.getSource());
    
    if (e.getSource() instanceof JMenuItem)
      menuActions((JMenuItem) e.getSource());
    
    if (e.getSource() instanceof Timer)
    {
      Timer timer = (Timer) e.getSource();
      
      if (timer.getDelay() != 750)
      {
        HistoryWindow window = HistoryWindow.getInstance();
        boolean state = window.isOpen();
      
        if (state) 
          if (window.getWidth() != 200) window.setSize(window.getWidth() + 10, 300);
        if (!state)
          if (window.getWidth() != 0) window.setSize(window.getWidth() - 10, 300);
      
        if (window.getWidth() == 0 || window.getWidth() == 200) timer.stop();
      }
      else playback.run();
        
    }
  } // actionPerformed method.

  /**
   * keyPressed - Will perform the correct action of the key pressed.
   * 
   * @param e
   *          (KeyEvent)
   */
  @Override
  public void keyTyped(final KeyEvent e)
  {
    ButtonPadPanel pad = ButtonPadPanel.getInstance();
    int code = e.getKeyChar();

    Object source = e.getSource();

    switch (code)
    {
      case 8:
        pad.pressButton("backspace");
        break;
      case 10: // enter
        pad.pressButton("equals");
        break;
      case 40:
        pad.pressButton("open parenthases");
        break;
      case 41:
        pad.pressButton("closed parenthases");
        break;
      case 42:
        pad.pressButton("multiply");
        break;
      case 43: // + add
        pad.pressButton("add");
        break;
      case 45: // - minus
        pad.pressButton("subtract");
        break;
      case 46:
        pad.pressButton("decimal");
        break;
      case 47:
        pad.pressButton("divide");
        break;
      case 48:
        pad.pressButton("0");
        break;
      case 49:
        pad.pressButton("1");
        break;
      case 50:
        pad.pressButton("2");
        break;
      case 51:
        pad.pressButton("3");
        break;
      case 52:
        pad.pressButton("4");
        break;
      case 53:
        pad.pressButton("5");
        break;
      case 54:
        pad.pressButton("6");
        break;
      case 55:
        pad.pressButton("7");
        break;
      case 56:
        pad.pressButton("8");
        break;
      case 57:
        pad.pressButton("9");
        break;
      case 94:
        pad.pressButton("exponent");
        break;
      case 105:
        pad.pressButton("i");
        break;
      default: // a key was pressed that we don't allow
        break;
    }

  } // keyPressed method.

  /**
   * append - Will add a character to the end of the display.
   * 
   * @param addition
   *          ( String )
   */
  private void append(final char addition)
  {
    String text = frame.getInputField().getText();
    if (addition != 'b')
    {
      frame.setInput(text + addition);
      return;
    }
    if (text.length() > 0)
      frame.setInput(text.substring(0, text.length() - 1));
  } // append method.
  
  /**
   * buttonActions - Will call the buttons actions.
   * 
   * @param button
   *          The button pressed.
   */
  private void buttonActions(final JButton button)
  {
    numberActions(button);
    operationActions(button);
    logicActions(button);
    historyActions(button);
    playbackActions(button);
  } // buttonActions method.
  
  /**
   * clears InputField for CalculatorDisplay.
   */
  private void clearInput()
  {
    frame.clearInputField();
  }

  /**
   * changes the mode of the calculator between fractions and decimals.
   */

  private void changeMode()
  {
    if (inFractions)
    {
      inFractions = false;
    }
    else if (!inFractions)
    {
      inFractions = true;
    }
    ButtonPadPanel.getInstance().updateMode(inFractions);
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
    String iPlus = "+i";
    String iMinus = "-i";
    String iParen = "(i";
    char imaginary = 'i';
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
   * returns the proper ComplexNumber text based off the mode of the calculator (Variable
   * inFractions). (either in decimal or fractional)
   * 
   * @param compNum
   *          - the complex number to get the string of
   * @return String - the proper text for the Complex Number
   */

  private String getComplexText(final ComplexNumber compNum)
  {
    String text = "";
    if (inFractions)
    {
      text = compNum.toFraction();
    }
    else if (!inFractions)
    {
      text = compNum.toString();
    }
    return text;
  }
  
  /**
   * historyActions - Will call the button actions for history.
   * 
   * @param button
   *          The button pressed.
   */
  private void historyActions(final JButton button)
  {
    switch (button.getName().toLowerCase())
    {
      case "history":
        if (!HistoryWindow.getInstance().isOpen())
          HistoryWindow.getInstance().toggleHistory(true);
        break;
      case "winhistory": // window history button.
        if (HistoryWindow.getInstance().isOpen())
          HistoryWindow.getInstance().toggleHistory(false);
        break;
      default:
        break;
    }
  } // historyActions method.

  /**
   * signals that the input is invalid.
   */
  private void invalidInput()
  {
    frame.invalidStatus(true, "Invalid Input");
  }

  /**
   * checks if a character is a number or number adjacent symbol.
   * 
   * @param letter
   *          - the symbol to check
   * @return boolean - whether the character is a number
   */

  private boolean isNumber(final char letter)
  {
    String numbers = "1234567890.i";
    boolean isNum = false;
    for (int i = 0; i < numbers.length(); i++)
    {
      if (numbers.charAt(i) == letter)
      {
        isNum = true;
      }
    }
    return isNum;
  }
  
  /**
   * logicActions - Will call the button actions for logic.
   * 
   * @param button
   *          The button pressed.
   */
  private void logicActions(final JButton button)
  {
    switch (button.getName().toLowerCase())
    {
      case "inverse":
      case "logarithm":
      case "conjugate":
      case "squareroot":
      case "sin":
      case "cos":
      case "tan":
      case "realpart":
      case "imaginarypart":
        if (evaluate.operatorEmpty())
        {
          if (evaluate.getFirstOp() == null
              || frame.getInputField() != null && !frame.getInputField().getText().equals(""))
          {
            operatorButton(button.getText());
          }
          else
          {
            operationsProcessor(evaluate.getFirstOp().getRawString(), button.getText());
          }
        }
        else
        {
          frame.invalidStatus(true, "Can't get the " + button.getName() + ".");
        }
        HistoryWindow.getInstance().addToHistory(frame.getDisplay().getText());
        break;
      default:
        break;
    }
  } // logicActions method.
  
  /**
   * menuActions - Will perform the menu actions.
   * 
   * @param menu
   *          The menu item selected.
   */
  private void menuActions(final JMenuItem menu)
  {
    if (menu.getText().equals("Print History..."))
    {
      HistoryWindow history = HistoryWindow.getInstance();
      try
      {
        PrintableHistory ph = new PrintableHistory(history.getTextArea());
        PrinterController.print(ph, frame);
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }

    }
    else if (menu.getText().equals("Playback"))
      PlaybackWindow.getInstance().setVisible(true);
  } // menuActions method.

  
  /**
   * numberActions - Will call button actions for number buttons.
   * 
   * @param button
   *          The button pressed.
   */
  private void numberActions(final JButton button)
  {
    switch (button.getName().toLowerCase())
    {
      case "0":
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
      case "6":
      case "7":
      case "8":
      case "9":
      case "decimal":
      case "open parenthases":
      case "closed parenthases":
        append(button.getText().charAt(0));
        break;
      case "i":
        append('i');
        break;
      case "backspace":
        append('b');
        break;
      case "sign":
        signChange();
        break;
      default:
        break;
    }
  } // numberActions method.
  
  /**
   * operationActions - Will call button actions for operations.
   * 
   * @param button
   *          The button pressed.
   */
  private void operationActions(final JButton button)
  {
    switch (button.getName().toLowerCase())
    {
      case "add":
      case "divide":
      case "multiply":
      case "subtract":
      case "exponent":
        operationsSwitch(button.getText());
        break;
      case "equals":
        operatorButton(button.getText());
        HistoryWindow.getInstance().addToHistory(frame.getDisplay().getText());
        break;
      case "clear":
        clearInput();
        frame.invalidStatus(false, "no Error");
        break;
      case "reset":
        resetDisplay();
        break;
      default:
        break;
    }
  } // operationActions method.

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
    String logOperator = "log";

    if (evaluate.operatorEmpty() && text.length() > 0)
    {
      ComplexNumber op1;
      if (evaluate.getFirstOp() == null || text.length() > 0)
      {
        op1 = parser.parseInput(text);
      }
      else
      {

        op1 = evaluate.getFirstOp();
      }

      frame.setDisplay("");
      if (operation.equals(equalsOperator))
      {
        frame.setDisplay(text + operation + getComplexText(op1));
      }
      // put the inverse sign here when the button is added.
      else if (operation.equals("Inv"))
      {
        if (op1 != null || (input != null && input.isEmpty()))
        {
          op1 = parser.parseInput(text);
        }
        ComplexNumber inv = op1.inverse();
        String str = "1/" + getComplexText(op1) + equalsOperator + getComplexText(inv);
        op1 = inv;
        frame.setDisplay(str);
      }
      else if (operation.equals("Con"))
      {
        String test = frame.getInputField().getText();
        if (op1 != null && test.isBlank())
        {
          ComplexNumber firstOp = evaluate.getFirstOp();
          op1 = new ComplexNumber(firstOp.getReal(), firstOp.getImaginary());
        }
        else if (op1 != null || (input != null && input.isEmpty()))
        {
          op1 = parser.parseInput(text);
        }
        String str = operation + getComplexText(op1) + equalsOperator;
        ComplexNumber conj = op1.conjugate();
        op1 = conj;
        str += op1;
        frame.setDisplay(str);
      }
      else if (operation.equals("sin") || operation.equals("cos") || operation.equals("tan")
          || operation.equals("sqrt") || operation.equals(logOperator))
      {
        evaluate.setOperator(operation);
        evaluate.setFirstOp(op1);
        ComplexNumber unitaryOperationResult = evaluate.solve();
        frame.setDisplay(operation + getComplexText(op1) + equalsOperator
            + getComplexText(unitaryOperationResult));
        op1 = unitaryOperationResult;
      }
      else if (operation.equals("RP")) {
        String str = operation + getComplexText(op1) + equalsOperator;
        op1 = new ComplexNumber(op1.getReal(), 0.0);
        str += getComplexText(op1);
        frame.setDisplay(str);
      }
      else if (operation.equals("IP")) {
        String str = operation + getComplexText(op1) + equalsOperator;
        op1 = new ComplexNumber(0.0, op1.getImaginary());
        str += getComplexText(op1);
        frame.setDisplay(str);
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
        frame.setDisplay(getComplexText(evaluate.getFirstOp()) + operation
            + getComplexText(evaluate.getFirstOp()));
      }
      else
      {
        evaluate.setOperator(operation);
        frame.setDisplay(getComplexText(evaluate.getFirstOp()) + operation);
      }
    }
    else if (!evaluate.operandEmpty() && !evaluate.operatorEmpty() && text.length() > 0)
    {
      ComplexNumber op2 = parser.parseInput(text);
      evaluate.setSecondOp(op2);
      evaluate.solve();
      if (operation.equals(equalsOperator))
      {
        frame.setDisplay(
            display.getText() + text + operation + getComplexText(evaluate.getFirstOp()));
      }
      else
      {
        frame.setDisplay(getComplexText(evaluate.getFirstOp()) + operation);
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
   * checks if their are open parenthesis and acts with the operator accordingly. if the parenthesis
   * are open it appends the operator to the text, else it performs the operation.
   * 
   * @param operation
   *          - the operator to act on
   */

  private void operationsSwitch(final String operation)
  {
    if (openParenCheck() || (frame.getInputField().getText().length() == 0
        && operation.contentEquals("-") && !evaluate.operatorEmpty()))
    {
      if (operation.charAt(0) == '+' && frame.getInputField().getText().contains("+"))
      {
        invalidInput();
      }
      else
      {
        append(operation.charAt(0));
      }
    }
    else
    {
      if (operation == "^") // acts like the number Buttons but may become a proper operator later
      {
        append(operation.charAt(0));
      }
      else
      {
        operatorButton(operation);
      }
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
    JLabel inputField = frame.getInputField();
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
   * returns if the textfield has unclosed parenthesis. true if their are more left paren then right
   * paren, returns false otherwise.
   * 
   * @return boolean - if there are open parenthesis
   */

  private boolean openParenCheck()
  {
    int openParen = 0;
    int closedParen = 0;
    String text = frame.getInputField().getText();
    for (int i = 0; i < text.length(); i++)
    {
      if (text.charAt(i) == '(')
      {
        openParen++;
      }
      if (text.charAt(i) == ')')
      {
        closedParen++;
      }
    }

    return (openParen != closedParen);
  }
  
  /**
   * playbackActions - Will perform the playback actions.
   * 
   * @param button
   *          The button pressed.
   */
  private void playbackActions(final JButton button)
  {
    switch (button.getName().toLowerCase())
    {
      case "record":
        int place;
        PlaybackWindow.getInstance().toggleIcon();
        if (!PlaybackWindow.getInstance().recording())
        {
          place = HistoryWindow.getInstance().getPlace();
          HistoryWindow.getInstance().nextRecording();
          PlaybackWindow.getInstance().saveRecording(
              HistoryWindow.getInstance().getRecording().get(place));
        }
        else
        {
          clearInput();
          resetDisplay();
          if (playback != null) playback.toggleFocusable(true);
        }
        break;
      case "play":
        /*
         * Grab a string based on the name of recording stored in the
         * JComboBox, create a new object of Playback with the string
         * of total recording.
         */
        String input = PlaybackWindow.getInstance().getRecording();
        if (!recording.equals(input))
        {
          clearInput();
          resetDisplay();
          playback = new Playback(input);
          recording = input;
        }
        playback.pause(false);
        playback.start();
        break;
      case "pause":
        if (playback != null)
          if (!playback.paused()) playback.pause(true);
        break;
      case "close":
        PlaybackWindow.getInstance().setVisible(false);
        if (playback != null) 
        {
          clearInput();
          resetDisplay();
          playback.pause(true);
          playback.toggleFocusable(true);
          recording = "";
        }
        break;
      default:
        break;
    }
  } // playbackActions method.

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

  private void signChange()
  {
    String text = "";
    if (frame.getInputField().getText() != null && frame.getInputField().getText().length() != 0)
    {
      text = frame.getInputField().getText();
      String neg = "-";
      String pos = "\\+";
      text = text.replaceAll(neg, neg + neg);
      text = text.replaceAll(pos, neg);
      text = text.replaceAll(neg + neg, pos);
      for (int i = 0; i < text.length(); i++)
      {
        if (isNumber(text.charAt(i)) && i == 0)
        {
          text = neg + text;
        }
        else if (text.charAt(i) == '+' && (i == 0 || !isNumber(text.charAt(i - 1))))
        {
          text = text.substring(0, i) + text.substring(i + 1);
        }
        else if (isNumber(text.charAt(i)) && i > 0 && (text.charAt(i - 1) == '('
            || text.charAt(i - 1) == '×' || text.charAt(i - 1) == '÷'))
        {
          text = text.substring(0, i) + neg + text.substring(i);
        }
      }
      frame.setInput(text);
    }
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
  public void windowDeactivated(final WindowEvent e)
  {
  } // unused.

  @Override
  public void windowActivated(final WindowEvent e)
  {
  } // unused.

  @Override
  public void keyPressed(final KeyEvent e)
  {
  } // unused.

  @Override
  public void keyReleased(final KeyEvent e)
  {
  } // unused.

} // CalcListener class.
