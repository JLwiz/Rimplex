package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.Timer;

import File.SaveHandler;
import controller.CalcListener;

/**
 * HistoryPanel - A JPanel that will display the history of the calculators equations.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/13/2021)
 */
public class HistoryWindow extends JWindow
{

  // ----------Declarations----------

  private static final long serialVersionUID = -6237181401018469549L;
  private static HistoryWindow single_instance = null;

  private boolean open;
  private CalculatorDisplay display = CalculatorDisplay.getInstance();
  private HashMap<Integer, String> recordings;
  private int place;
  private JButton close;
  private JTextPane historyText;
  private JPanel historyPanel;
  private JScrollPane scrollList;
  
  private static Color foreground = new Color(255, 255, 255);
  private static Color background = new Color(0, 0, 0);

  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints constraints = new GridBagConstraints();

  // ----------Constructors----------

  /**
   * Default Constructor.
   */
  private HistoryWindow()
  {
    super(CalculatorDisplay.getInstance());

    try
    {
      int colors[] = fetchColors();
      foreground = new Color(colors[0], colors[1], colors[2]);
      background = new Color(colors[3], colors[4], colors[5]);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("ERROR RETRIEVING THEMEING");
    }
    
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
    if (single_instance == null)
      single_instance = new HistoryWindow();
    return single_instance;
  } // getInstance method.

  // ----------Public Methods----------

  /**
   * addToHistory - Will add an equation and answer to the history.
   * 
   * @param saved
   *          String of the equation.
   * @throws IOException
   *           - the IOException
   */
  public void addToHistory(final String saved) throws IOException
  {
    if (saved == null || saved.equals(""))
      return;

    String keep = saved.replaceAll("<html>", "").replaceAll("</html>", "");

    if (PlaybackWindow.getInstance().recording())
      if (recordings.size() == place + 1)
        recordings.put(place, recordings.get(place) + keep);
      else
        recordings.put(place, keep);

    if (!keep.trim().equals(""))
    {
      keep = historyText.getText().replaceAll("</p>", keep + "<br><br></p>");
      historyText.setText(keep);
    }
  } // addToHistory method.
  
  /**
   * clearHistory - Will clear the history.
   */
  public void clearHistory()
  {
    int start = historyText.getText().indexOf("<p>") + 3;
    int end = historyText.getText().indexOf("</p>", start);
    historyText.setText(historyText.getText().substring(0, start) 
        + historyText.getText().substring(end, historyText.getText().length()));
    
  } // clearHistory method.

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
   * @param pos
   *          int
   * @return equation String
   */
  public String getRecording(int pos)
  {
    if (pos < recordings.size())
      return recordings.get(pos);
    return "";
  }

  /**
   * getPanel - Will return the JPanel.
   * 
   * @return historyPanel (JPanel)
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
    if (recordings.size() > place)
      place++;
  } // nextRecording.

  /**
   * toggleHistory - Will open/close the history.
   * 
   * @param open
   *          boolean
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

  // ----------Private Methods----------

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
    recordings = new HashMap<>();
    close = new JButton("<");
    historyText = new JTextPane();
    historyPanel = new JPanel();
    scrollList = new JScrollPane(historyText);
  } // createComponenets method.
  
  private static int[] fetchColors() throws FileNotFoundException{
    Scanner in = new Scanner(new File("src/app/config.txt"));
    int colors[] = new int[6];
    for(int i = 0; i < 3; i++) {
      colors[i] = in.nextInt();
    }
    for(int i = 3; i < 6; i++) {
      colors[i] = in.nextInt();
    }
    in.close();
    return colors;
  }

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
    close.setBackground(background);
    close.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    close.setForeground(foreground);
    close.setFont(new Font("Arial", Font.BOLD, 15));
    scrollList.setWheelScrollingEnabled(true);
    historyText.setContentType("text/html");
    historyText.setFont(new Font("Arial", Font.BOLD, 10));
    historyText.setEditable(false);
    historyPanel.setLayout(layout);
  } // setComponenets method.

} // HistoryWindow class.
