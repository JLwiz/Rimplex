package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.Timer;

import controller.CalcListener;

/**
 * HistoryPanel - A JPanel that will display the history of the
 * calculators equations.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/13/2021)
 */
public class HistoryWindow extends JWindow
{

  //----------Declarations----------
  
  
  
  private static final long serialVersionUID = -6237181401018469549L;
  private static HistoryWindow single_instance = null;
  
  private boolean open;
  private CalculatorDisplay display = CalculatorDisplay.getInstance();
  private ArrayList<String> recordings;
  private int place;
  private JButton close;
  private JTextPane historyText;
  private JPanel historyPanel;
  private JScrollPane scrollList;
  
  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints constraints = new GridBagConstraints();
  
  
  
  //----------Constructors----------
  
  
  
  /**
   * Default Constructor.
   */
  private HistoryWindow()
  { 
    super(CalculatorDisplay.getInstance());
    
    open = false;
    place = 0;
    setSize(new Dimension(0, 300));
    setLocation(display.getX() + 490, display.getY() + 160);
    createComponents();
    setComponents();
    addComponents();    
    setFocusable(true);
    addKeyListener(CalcListener.getInstance());
    getContentPane().add(historyPanel);
    
    setVisible(true);
  } // constructor.
  
  /**
   * getInstance - Singleton for HistoryPanel.
   * 
   * @return the one and only HistoryPanel object.
   */
  public static HistoryWindow getInstance()
  {
    if (single_instance == null) single_instance = new HistoryWindow();
    return single_instance;
  } // getInstance method.
  
  
  
  //----------Public Methods----------
  
  
  
  /**
   * addToHistory - Will add an equation and answer to the history.
   * 
   * @param saved
   *          String of the equation.
   */
  public void addToHistory(final String saved)
  {
    String keep = saved.replaceAll("<html>", "").replaceAll("</html>", "");
    
    if (PlaybackWindow.getInstance().recording())
      if (recordings.size() < 1) recordings.add(place, keep);
      else recordings.add(place, recordings.get(place) + keep);
    
    if (!keep.trim().equals(""))
    {
      keep = historyText.getText().replaceAll("</p>", keep + "<br><br></p>");
      historyText.setText(keep);
    }
  } // addToHistory method.
  
  /**
   * getPlace - Will return the current place integer.
   * 
   * @return place
   */
  public int getPlace()
  {
    return place;
  }
  
  /**
   * getRecording - Will get the equation list.
   * 
   * @return ArrayList
   */
  public ArrayList<String> getRecording() 
  {
    return recordings;
  }
  
  /**
   * getPanel - Will return the JPanel.
   * 
   * @return historyPanel
   *            (JPanel)
   */
  public JPanel getPanel()
  {
    return historyPanel;
  } // getPanel method.
  
  /**
   * nextRecording - Will increment place by one.
   */
  public void nextRecording()
  {
    place++;
  } // nextRecording.
  
  /**
   * toggleHistory - Will open/close the history.
   * 
   * @param open
   *      boolean
   */
  public void toggleHistory(final boolean open)
  {
    Timer timer = new Timer(5, CalcListener.getInstance());
    
    timer.start();
    
    this.open = open;
  } // open method.
  
  /**
   * isOpen - Will return the state of the JWindow.
   * 
   * @return true if open, false if closed.
   */
  public boolean isOpen()
  {
    return open;
  } // state method.
  
  
  //----------Private Methods----------
  
  
  
  /**
   * addComponents - Will add the components to their correct positions.
   */
  private void addComponents()
  {
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = .95;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    historyPanel.add(scrollList, constraints);
    
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.weightx = .05;
    constraints.weighty = 1;
    historyPanel.add(close, constraints);
  } // addComponents method.
  
  /**
   * createComponenets - Will create the components.
   */
  private void createComponents()
  {
    recordings = new ArrayList<>();
    close = new JButton("<");
    historyText = new JTextPane();
    historyPanel = new JPanel();
    scrollList = new JScrollPane(historyText);
  } // createComponenets method.
  
  /**
   * getTextPane - Will return the JTextPane component.
   * 
   * @return the JTextPane.
   */
  public JTextPane getTextArea()
  {
    return this.historyText;
  }
  
  /**
   * setComponents - Will set the components attributes.
   */
  private void setComponents()
  {
    close.setName("winhistory");
    close.addActionListener(CalcListener.getInstance());
    close.setMaximumSize(new Dimension(20, getHeight()));
    close.setMinimumSize(new Dimension(20, getHeight()));
    close.setPreferredSize(new Dimension(20, getHeight()));
    close.setBackground(Color.BLACK);
    close.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    close.setForeground(Color.WHITE);
    close.setFont(new Font("Arial", Font.BOLD, 15));
    scrollList.setWheelScrollingEnabled(true);
    historyText.setContentType("text/html");
    historyText.setFont(new Font("Arial", Font.BOLD, 10));
    historyText.setEditable(false);
    historyPanel.setLayout(layout);
  } // setComponenets method.
  
} // HistoryWindow class.
