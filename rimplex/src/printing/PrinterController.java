package printing;

import java.awt.print.*;
import javax.swing.*;

/**
 * @author Jacob Lewis
 * @version 04.21.2021
 */
public class PrinterController {
  public static void print(Printable printable, JFrame parent)
  {
    PrinterJob job = PrinterJob.getPrinterJob();
    try
    {
      job.setPrintable(printable);
      boolean shouldPrint = job.printDialog();
      if (shouldPrint) job.print();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(parent, 
          "Unable to print!",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
