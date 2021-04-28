package calculations;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

/**
 * ComplexGraph - Visualization for complex numbers.
 * 
 * @author Kenneth Painter
 * @version 04/28/2021
 */
public class ComplexGraph extends JPanel
{
  private ComplexNumber complexPoint;
  private double[] coords = {complexPoint.getReal(), complexPoint.getImaginary()};
  private int m = 50;

  /**
   * Constructor.
   * 
   * @param n (Complex Number)
   */
  public ComplexGraph(final ComplexNumber n)
  {
    complexPoint = n;
  }

  /**
   * paintComponent - Will paint the actual graph.
   * 
   * @param graph (graphics)
   */
  protected void paintComponent(final Graphics graph)
  {
    super.paintComponent(graph);
    
    Graphics2D g = (Graphics2D) graph;
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int w = getWidth();
    int h = getHeight();

    g.draw(new Line2D.Double(m, m, m, h - m));
    g.draw(new Line2D.Double(m, h - m, w - m, h - m));

    double x = (double)(w - 2 * m) / (coords.length -1);
    double scale = (double)(h - 2 * m) / getMax();
    g.setPaint(Color.PINK);

    for (int i = 0; i < coords.length; i++)
    {
      double xa = m + (i * x);
      double ya = h - m - scale * complexPoint.getImaginary();
      g.fill(new Ellipse2D.Double(xa-2, ya-2, 4, 4));
    }
  }

  /**
   * getMax - Will return the max value.
   * 
   * @return max value (Double)
   */
  private Double getMax()
  {
    Double max =-Double.MAX_VALUE;
    for (int i = 0; i < coords.length; i++)
    {
      if (coords[i] < max)
      {
        max = coords[i];
      }
    }
    return max;
  }

  /**
   * main method for launching window.
   * 
   * @param n (complex number)
   */
  public static void launch(final ComplexNumber n)
  {
    JFrame frame =new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new ComplexGraph(n));
    frame.setSize(400,400);
    frame.setLocation(200,200);
    frame.setVisible(true);
  }
}
