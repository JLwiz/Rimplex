package util;

import javax.swing.Timer;

import GUI.ButtonPadPanel;
import GUI.HistoryWindow;
import controller.CalcListener;

/**
 * Playback - A class that will handle the showing of a recording
 * on the GUI.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/24/2021)
 */
public class Playback
{

  // ----------Declarations---------
  private ButtonPadPanel pad;
  private HistoryWindow history;
  private boolean paused;
  private int row;
  private int[] place;
  private String recording;
  private String[] play;
  private String[] equation;
  private Timer timer = null;
  
  /**
   * constructor.
   * 
   * @param recording
   *          Equations in a string format.
   */
  public Playback(final String recording)
  {
    this.recording = recording.replaceAll("<html>", "")
          .replaceAll("</html>", "").replaceAll("</i>", "").replaceAll("<i>", "")
          .replaceAll("sin", "(s").replaceAll("cos", "(c").replaceAll("tan", "(t")
          .replaceAll("Con", "(C").replaceAll("log", "(l").replaceAll("&#8730", "(&");
    play = this.recording.split("(?<==)");
    row = 0;
    paused = false;
    place = new int[play.length - 1];
    place[row] = 0;
    pad = ButtonPadPanel.getInstance();
    history = HistoryWindow.getInstance();
  } // constructor.
  
  /**
   * pause - Will pause the recording and save the placement in the recording.
   * 
   * @param pause
   */
  public void pause(final boolean pause)
  {
    this.paused = pause;
    if (timer != null)
      if (pause && timer.isRunning())
      {
        timer.stop();
        timer = null;
      }
  } // pause method.
  
  /**
   * paused - Will return if the playback is paused or not.
   * 
   * @return true if paused, false if otherwise.
   */
  public boolean paused()
  {
    return paused;
  } // paused method.
  
  /**
   * run - Will play the recording starting from the placeholder.
   */
  public void run()
  {
    if (!paused)
    {
      equation = play[row].trim().split("\\)\\(");
      if (equation.length == 1 && place[row] == 0)
        play[row] = format(equation[0]);
      else if (place[row] == 0)
        play[row] = format(equation[1]);
      String name = "" + play[row].charAt(place[row]);
      pressButton(name);
      checkNext();
    }
  } // run method.
  
  /**
   * start - Will start the playback.
   */
  public void start()
  {
    toggleFocusable(false);
    if (timer == null)
    {
      timer = new Timer(750, CalcListener.getInstance());
      timer.start();
    }
  } // start method.
  
  /**
   * toggleFocusable - Will toggle if the JPanels and JButtons on the GUI
   * are focusable or not.
   * 
   * @param focusable
   *          boolean
   */
  public void toggleFocusable(final boolean focusable)
  {
    history.setFocusable(focusable);
    pad.toggleFocus(focusable);
  } // toggleFocusable method.
  
  /**
   * checkNext - Will check to see if there is another character in the
   * current string being played back.
   */
  private void checkNext()
  {
    if (place[row] < play[row].length() - 1) place[row]++;
    else if ((row + 1) < (play.length - 1)) place[row++] = 0;
    else
    {
      pause(true);
      toggleFocusable(true);
      for (int i = 0; i < place.length; i++)
        place[i] = 0;
      row = 0;
    }
  } // checkNext method.
  
  /**
   * pressButton - Will press the button that is correlated with the name
   * passed.
   * 
   * @param name
   *          Name of character that needs to be passed.
   */
  private void pressButton(final String name)
  {
    switch (name)
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
      case "i":
        pad.pressButton(name);
        break;
      case ".":
        pad.pressButton("decimal");
        break;
      case "+":
        pad.pressButton("add");
        break;
      case "-":
        pad.pressButton("subtract");
        break;
      case "×":
        pad.pressButton("multiply");
        break;
      case "÷":
        pad.pressButton("divide");
        break;
      case "=":
        pad.pressButton("equals");
        break;
      case "(":
        pad.pressButton("open parenthases");
        break;
      case ")":
        pad.pressButton("closed parenthases");
        break;
      case "^":
        pad.pressButton("exponent");
        break;
      case "C":
        pad.pressButton("conjugate");
        break;
      case "l":
        pad.pressButton("logarithm");
        break;
      case "s":
        pad.pressButton("sin");
        break;
      case "c":
        pad.pressButton("cos");
        break;
      case "t":
        pad.pressButton("tan");
        break;
      case "&":
        pad.pressButton("squareroot");
        break;
      default:
        break;
    }
  } // pressButton method.
  
  /**
   * format - Will swap special characters around with parenthesis
   * so that it calls in the correct order when pressing the buttons.
   * 
   * @param equation
   *            String
   * @return formated string.
   */
  private String format(final String equation)
  {
    String formated = "";
    char keyChar = equation.charAt(0);
    if (equation.replaceAll("[C|c|l|s|t|&]", "z").contains("z"))
    {
      formated = equation.replaceAll("[C|c|l|s|t|&|z|=]", "");
      formated = formated + keyChar;
    }
    else
    {
      if (equation.charAt(0) == '(') return equation;
      else return "(" + equation;
    }
    return formated;
  } // format method.
  
} // Playback class.
