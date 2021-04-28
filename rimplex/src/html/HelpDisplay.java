package html;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
/**
 * Holds and displays HTML help page.
 * 
 * @author Kenneth Painter
 * @version 1.0 (04/28/2021)
 */
public class HelpDisplay extends JFrame
{
  /**
   * 
   */
  private static final long serialVersionUID = 4967114016756453881L;
  public static final String help =
      "<!DOCTYPE html>" + 
      "<html>" + 
      "    <head>" + 
      "        <title>Rimplex Help Guide</title>" + 
      "    </head>" + 
      "    <body>" + 
      "        <h2>Rimplex Complex Number Calculator - Help</h2>" + 
      "        <div>" + 
      "            <p>Rimplex is a tool for doing arithmetic with complex numbers.</p>" + 
      "        </div>" + 
      "        <br>" + 
      "        <div>" +
      "            <h3>Supported Operations</h3>" + 
      "            <h5>Addition</h5>" + 
      "            <p>Add two complex numbers together</p>" + 
      "            <h5>Subtraction</h5>" + 
      "            <p>Subtract two complex numbers</p>" + 
      "            <h5>Multiplication</h5>" + 
      "            <p>Multiply two complex numbers together</p>" + 
      "            <h5>Division</h5>" + 
      "            <p>Divide a complex number by another complex number</p>" + 
      "            <h5>Exponentiation</h5>" + 
      "            <p>Raise a complex number to the power of another complex number</p>" + 
      "            <h5>Logarithm</h5>" + 
      "            <p>Find the natural log of a complex number</p>" + 
      "            <h5>Square Root</h5>" + 
      "            <p>Find the square root of a complex number</p>" + 
      "            <br>" + 
      "        </div>" + 
      "        <br>" + 
      "        <div>" + 
      "            <h3>Features</h3>" + 
      "            <h5>Rolling Calculations</h5>" + 
      "            <p>The result of a calculation can be used as an operand for another calculation by using the" + 
      "                operator keys after a result is displayed." + 
      "            </p>" + 
      "            <h5>History Display</h5>" + 
      "            <p>Calculation history can be displayed by opening the history panel from the options menu.</p>" + 
      "            <h5>Multiple Methods of Input Entry</h5>" + 
      "            <p>Operands can be entered into the calculator either by use of the keyboard or using " + 
      "                the soft buttons in the application." + 
      "            </p>" + 
      "            <h5>Fractional, Decimal, or Polar Results</h5>" + 
      "            <p>Toggle between displaying results in fraction, polar, or decimal form by using the FRAC/DEC/POLAR button on the calculator.</p>" + 
      "            <h5>Playback</h5>" + 
      "            <p>Using the playback function allows you to record and step through calculations.</p>" + 
      "            <br>" + 
      "        </div>" + 
      "    </body>" + 
      "</html>";
  
  public void displayHelp() {
    try {
      JEditorPane pane = new JEditorPane("text/html", HelpDisplay.help);
      JScrollPane scrollpane = new JScrollPane(pane);
      add(scrollpane);
      setVisible(true);
      setSize(600, 800);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
