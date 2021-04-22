package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;

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
  
  private CalculatorDisplay display = CalculatorDisplay.getInstance();
  private ArrayList<String> equations;
  private JTextArea historyText;
  private JPanel historyPanel;
  private JScrollPane scrollList;
  
  
  
  //----------Constructors----------
  
  
  
  /**
   * Default Constructor.
   */
  private HistoryWindow()
  { 
    super(CalculatorDisplay.getInstance());
    
    setSize(new Dimension(200, 300));
    setLocation(display.getX() + 490, display.getY() + 160);
    createComponents();
    setComponents();
    addComponents();    
    
    setVisible(false);
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
    String keep = saved.replaceAll("<html>", "")
        .replaceAll("<i>", "").replaceAll("</html>", "").replaceAll("</i>", "");
    equations.add(keep); 
    historyText.setText(historyText.getText() + keep + "\n\n");
  } // addToHistory method.
  
  /**
   * getHistory - Will get the equation list.
   * 
   * @return ArrayList
   */
  public ArrayList<String> getHistory() 
  {
    return equations;
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
  
  
  
  //----------Private Methods----------
  
  
  
  /**
   * addComponents - Will add the components to their correct positions.
   */
  private void addComponents()
  {
    historyPanel.add(scrollList);
    add(historyPanel);
  } // addComponents method.
  
  /**
   * createComponenets - Will create the components.
   */
  private void createComponents()
  {
    equations = new ArrayList<>();
    historyText = new JTextArea();
    historyPanel = new JPanel();
    scrollList = new JScrollPane(historyText);
  } // createComponenets method.
  
  /**
   * setComponents - Will set the components attributes.
   */
  private void setComponents()
  {
    scrollList.setWheelScrollingEnabled(true);
    historyText.setFont(new Font("Arial", Font.BOLD, 10));
    historyText.setEditable(false);
    historyPanel.setLayout(new GridLayout(1, 1));
  } // setComponenets method.
  
  
} // HistoryWindow class.
