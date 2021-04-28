package Color;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The listener for the ColorSelector application.
 * 
 * @author Benjamin Huber
 * @version 4/27/2021
 */
public class ColorSelectorListener implements ActionListener
{
  private ColorSelector color;
  private String space = " ";
  private String newline = "\n";
  
  /**
   * The actionEvent handler for whenever the "OK" Button is pressed.  It gets 
   * the colors from the foreground and background, writes them to a file, and
   * updates the "OK" Button to preview the selection.
   * 
   * @param e the event of the button being pressed.
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    color = ColorSelector.getInstance();
    
    int fcolors[] = color.getForegroundColors();
    int bcolors[] = color.getBackgroundColors();
    
    try
    {
      FileWriter colorWriter = new FileWriter("src/app/config.txt");
      colorWriter.write(fcolors[0] + space + fcolors[1] + space + fcolors[2] + newline);
      colorWriter.write(bcolors[0] + space + bcolors[1] + space + bcolors[2] + newline);
      colorWriter.close();
      
      Color foreground = new Color(fcolors[0], fcolors[1], fcolors[2]);
      Color background = new Color(bcolors[0], bcolors[1], bcolors[2]);
      color.setOKColor(foreground, background);
    }
    catch (IOException e1)
    {
      System.out.println("Could not find config file.");
    }
    catch (IllegalArgumentException e2)
    {
      System.out.println("A field was not an integer in the interval [0, 255]");
    }
  }

}
