package app;

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
   * @param args
   *          unused.
   */
  public static void main(final String[] args)
  {
    CalculatorDisplay.getInstance().addComponentListener(new DisplayListener());

  } // main method.

} // RimplexDriver class.
