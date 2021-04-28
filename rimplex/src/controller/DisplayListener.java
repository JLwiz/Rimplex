package controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JFrame;

import GUI.HistoryWindow;
import GUI.PlaybackWindow;

/**
 * DisplayListener - A listener for the JFrame that manipulates the positioning
 * of the History window.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/22/2021)
 */
public class DisplayListener implements ComponentListener
{
  
  /**
   * Checks to see if the component is resized.
   */
  @Override
  public void componentResized(final ComponentEvent e)
  {
    JFrame frame;
    
    if (e.getSource() instanceof JFrame)
    {
      frame = (JFrame) e.getSource();
      HistoryWindow.getInstance().setLocation(frame.getX() + (int)((.98) * frame.getWidth()),
          frame.getY() + (int)((.32) * frame.getHeight()));
      try
      {
        PlaybackWindow.getInstance().setLocation(frame.getX() + (int)((.99) * frame.getWidth()),
            frame.getY() + (int)((.02) * frame.getHeight()));
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * Checks to see if the component is moved.
   */
  @Override
  public void componentMoved(final ComponentEvent e)
  {
    JFrame frame;
    
    if (e.getSource() instanceof JFrame)
    {
      frame = (JFrame) e.getSource();
      HistoryWindow.getInstance().setLocation(frame.getX() + (int)((.98) * frame.getWidth()),
          frame.getY() + (int)((.32) * frame.getHeight())); 
      try
      {
        PlaybackWindow.getInstance().setLocation(frame.getX() + (int)((.99) * frame.getWidth()),
            frame.getY() + (int)((.02) * frame.getHeight()));
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  /**
   * Unused.
   */
  @Override
  public void componentShown(final ComponentEvent e)
  {
  } // unused.

  /**
   * Unused.
   */
  @Override
  public void componentHidden(final ComponentEvent e)
  { 
  } // unused.
  
} // DisplayListener class.
