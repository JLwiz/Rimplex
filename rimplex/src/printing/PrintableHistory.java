package printing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class PrintableHistory implements Printable {
//  private double height, width;
//  private Image image;
    private boolean doubleBuffered;
    private JComponent delegate;
  public PrintableHistory(JComponent delegate) throws IOException
  {
      this.delegate = delegate;
      doubleBuffered = delegate.isDoubleBuffered();
//    image = ImageIO.read(new File(file));
//    width = image.getWidth(null);
//    height = image.getHeight(null);
  }
  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
      throws PrinterException {
    double        cH, cW, h, scale, w, x, y;
    Graphics2D    g2;
    int           status;

    g2 = (Graphics2D)graphics;

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
       cW = (double)(delegate.getWidth());
       cH = (double)(delegate.getHeight());
       scale = Math.min(w/cW, h/cH);
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
