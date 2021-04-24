package controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import GUI.HistoryWindow;

/**
 * DisplayListener - A listener for the JFrame that manipulates the positioning
 * of the History window.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/22/2021)
 */
public class DisplayListener implements ComponentListener
{
  
  @Override
  public void componentResized(ComponentEvent e)
  {
    JFrame frame;
    
    if (e.getSource() instanceof JFrame)
    {
      frame = (JFrame) e.getSource();
      HistoryWindow.getInstance().setLocation(frame.getX() + (int)((.98) * frame.getWidth()),
          frame.getY() + (int)((.32) * frame.getHeight()));
    }
  }

  @Override
  public void componentMoved(ComponentEvent e)
  {
    JFrame frame;
    
    if (e.getSource() instanceof JFrame)
    {
      frame = (JFrame) e.getSource();
      HistoryWindow.getInstance().setLocation(frame.getX() + (int)((.98) * frame.getWidth()),
          frame.getY() + (int)((.32) * frame.getHeight())); 
    }
  }

  @Override
  public void componentShown(ComponentEvent e)
  {
  } // unused.

  @Override
  public void componentHidden(ComponentEvent e)
  { 
  } // unused.
  
} // DisplayListener class.
