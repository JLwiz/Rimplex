package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * HistoryPanel - A JPanel that will display the history of the
 * calculators equations.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/13/2021)
 */
public class HistoryPanel extends JPanel
{

  //----------Declarations----------
  
  
  
  private static final long serialVersionUID = -6237181401018469549L;
  private static HistoryPanel single_instance = null;
  
  private ArrayList<String> equations;
  
  private JTextArea historyText;
  
  private JScrollPane scrollList;
  
  
  
  //----------Constructors----------
  
  
  
  /**
   * Default Constructor.
   */
  private HistoryPanel()
  { 
    setSize(new Dimension(100, 100));
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    
    setLayout(new GridLayout(1, 1));
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
  public static HistoryPanel getInstance()
  {
    if (single_instance == null) single_instance = new HistoryPanel();
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
    
    String keep = saved.replaceAll("<html>", "");
    keep = keep.replaceAll("<i>", "");
    keep = keep.replaceAll("</html>", "").replaceAll("</i>", "");
    
    equations.add(keep);
    
    historyText.setText(historyText.getText() + keep + "\n\n");
    
    
  } // addToHistory method.
  
  
  
  //----------Private Methods----------
  
  
  
  /**
   * addComponents - Will add the components to their correct positions.
   */
  private void addComponents()
  {
    add(scrollList);
  } // addComponents method.
  
  /**
   * createComponenets - Will create the components.
   */
  private void createComponents()
  {
    equations = new ArrayList<>();
    historyText = new JTextArea();
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
  } // setComponenets method.
  
  
  
} // HistoryPanel class.
