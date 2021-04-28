package util;

import java.io.IOException;

import javax.swing.Timer;

import GUI.ButtonPadPanel;
import GUI.HistoryWindow;
import GUI.PlaybackWindow;
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
  private String[] equationArray;
  private Timer timer = null;
  
  private final String sineText = "sin", cosineText = "cos", tangentText = "tan";
  private final String ampersandText = "&";
  private final String zText = "z", openParenthasesText = "(";
  
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
          .replaceAll(sineText, "(s").replaceAll(cosineText, "(c").replaceAll(tangentText, "(t")
          .replaceAll("Con", "(C").replaceAll("log", "(l").replaceAll("&#8730", ampersandText);
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
   * @throws IOException - the IOException
   */
  public void pause(final boolean pause) throws IOException
  {
    this.paused = pause;
    PlaybackWindow.getInstance().toggleRecord(true);
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
   * @throws IOException - the IOException
   */
  public void run() throws IOException
  {
    if (!paused)
    {
      equationArray = play[row].trim().split("\\)\\(");
      if (equationArray.length == 1 && place[row] == 0)
        play[row] = format(equationArray[0]);
      else if (place[row] == 0)
        play[row] = format(equationArray[1]);
      String name = "" + play[row].charAt(place[row]);
      pressButton(name);
      checkNext();
    }
  } // run method.
  
  /**
   * start - Will start the playback.
   * @throws IOException - the IOException
   */
  public void start() throws IOException
  {
    toggleFocusable(false);
    PlaybackWindow.getInstance().toggleRecord(false);
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
   * @throws IOException - the IOException
   */
  private void checkNext() throws IOException
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
   * checkParenthesis - Will make sure there is an equal amount of opening and 
   * closing parenthesis for equation.
   * 
   *  @param equation
   *            String
   *  @return boolean if all closing parenthesis have an opening.
   */
  private boolean checkParenthesis(final String equation)
  {
    if ((equation.replaceAll("\\(", "").length()) == (equation.replaceAll("\\)", "").length()))
      return true;
    return false;
  } // checkParenthesis method.
  
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
    char check = equation.charAt(1);
    if (equation.replaceAll("[C|c|l|s|t]", zText).contains(zText))
    {
      formated = equation.replaceAll("[C|c|l|s|t|&|z|=]", "");
      if ((keyChar == '(') && (check == 'c') || (check == 'C')
          || (check == 'l') || (check == 's') || (check == 't'))
        formated = formated.substring(1) + check;
      else formated = formated + keyChar;
    }
    else
    {
      if (equation.charAt(0) == '(' && checkParenthesis(equation)) return equation;
      else return openParenthasesText + equation;
    }
    return formated;
  } // format method.
  
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
      case openParenthasesText:
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
        pad.pressButton(sineText);
        break;
      case "c":
        pad.pressButton(cosineText);
        break;
      case "t":
        pad.pressButton(tangentText);
        break;
      case ampersandText:
        pad.pressButton("squareroot");
        break;
      default:
        break;
    }
  } // pressButton method.
  
} // Playback class.
