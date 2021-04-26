package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import javax.swing.SwingUtilities;

import GUI.CalculatorDisplay;
import controller.DisplayListener;

/**
 * The driver for rimplex.
 * 
 * @author Andrew Fryer
 * @version 1.0 (03/23/2021)
 */
public class RimplexDriver
{

  /**
   * Main method.
   * 
   * @param args unused.
   */
  public static void main(final String[] args)
  {
    try
    {
      FileReader languageReader = new FileReader("src/app/language.txt");
      char languageArray[] = new char[5];
      languageReader.read(languageArray);
      String localeString[] = String.valueOf(languageArray).split(" ");
      Locale.setDefault(new Locale(localeString[0], localeString[1]));
    }
    catch (FileNotFoundException e)
    {
      System.out.println("FILE NOT FOUND");
    }
    catch (IOException e)
    {
      System.out.println("ERROR OCCURED WHEN READING");
    }
    SwingUtilities.invokeLater(() -> 
       CalculatorDisplay.getInstance().addComponentListener(new DisplayListener()));
        
  } // main method.
  
} // RimplexDriver class.
