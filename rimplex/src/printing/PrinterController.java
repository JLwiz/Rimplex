package printing;

import java.awt.print.*;
import javax.swing.*;

/**
 * The controller for the printer.
 * 
 * @author Jacob Lewis
 * @version 04.21.2021
 */
public class PrinterController
{

  /**
   * Prints the thing.
   * 
   * @param printable
   *          the printable object to print.
   * @param parent
   *          the parent JFrame
   */
  public static void print(final Printable printable, final JFrame parent)
  {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(printable);
    boolean shouldPrint = job.printDialog();
    if (shouldPrint)
    {
      try
      {
        job.print();
      }
      catch (PrinterException e)
      {
        JOptionPane.showMessageDialog(parent, "Unable to print!", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
