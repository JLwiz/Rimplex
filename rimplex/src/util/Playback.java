package util;

import GUI.ButtonPadPanel;

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
  private boolean paused;
  private int row;
  private int[] place;;
  private String recording;
  private String[] play;
  
  /**
   * constructor.
   * 
   * @param recording
   *          Equations in a string format.
   */
  public Playback(final String recording)
  {
    this.recording = recording.replaceAll("<html>", "")
          .replaceAll("</html>", "").replaceAll("</i>", "").replaceAll("<i>", "");
    play = this.recording.split("(?<==)");
    row = 0;
    paused = false;
    place = new int[play.length/2];
    place[row] = 0;
    pad = ButtonPadPanel.getInstance();
  } // constructor.
  
  /**
   * play - Will play the recording starting from the placeholder.
   */
  public void play()
  {
    if (!paused)
    {
      String name = "" + play[row * 2].charAt(place[row]);
      pressButton(name);
      checkNext();
    }
  } // play method.
  
  /**
   * pause - Will pause the recording and save the placement in the recording.
   */
  public void pause()
  {
    paused = true;
  } // pause method.
  
  /**
   * checkNext - Will check to see if there is another character in the
   * current string being played back.
   */
  private void checkNext()
  {
    if (place[row] < play[row * 2].length()) place[row]++;
    else place[row++] = 0;
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
        pad.pressButton("deciemal");
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
      default:
        break;
    }
  } // pressButton method.
  
} // Playback class.
