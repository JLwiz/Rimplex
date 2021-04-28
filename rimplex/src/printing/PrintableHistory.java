package printing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JComponent;

/**
 * Maintains a printable history buffer for use with the printer.
 * 
 * @author Jacob Lewis
 * @version 4/26/2021
 */
public class PrintableHistory implements Printable
{
  // private double height, width;
  // private Image image;
  private boolean doubleBuffered;
  private JComponent delegate;

  /**
   * Creates a printable history object.
   * 
   * @param delegate
   *          the component to print.
   */
  public PrintableHistory(final JComponent delegate)
  {
    this.delegate = delegate;
    doubleBuffered = delegate.isDoubleBuffered();
    // image = ImageIO.read(new File(file));
    // width = image.getWidth(null);
    // height = image.getHeight(null);
  }

  /**
   * Prints the delegate.
   * 
   * @param graphics
   *          idk
   * @param pageFormat
   *          idk
   * @param pageIndex
   *          idk
   * @return an integer representation of the status of the print
   */
  @Override
  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
      throws PrinterException
  {
    double cH, cW, h, scale, w, x, y;
    Graphics2D g2;
    int status;

    g2 = (Graphics2D) graphics;

    // Turn off double buffering
    delegate.setDoubleBuffered(false);

    status = Printable.NO_SUCH_PAGE;
    if (pageIndex == 0)
    {
      // Translate the origin
      x = pageFormat.getImageableX();
      y = pageFormat.getImageableY();
      g2.translate(x, y);

      // Scale the coordinate system (without changing the
      // aspect ratio)
      h = pageFormat.getImageableHeight();
      w = pageFormat.getImageableWidth();
      cW = (double) (delegate.getWidth());
      cH = (double) (delegate.getHeight());
      scale = Math.min(w / cW, h / cH);
      g2.scale(scale, scale);

      // Have the JComponent paint itself
      delegate.paint(g2);

      // Inform the caller that a page has been drawn
      status = Printable.PAGE_EXISTS;
    }

    // Restore double buffering (if it was on)
    delegate.setDoubleBuffered(doubleBuffered);

    return status;
  }
}
