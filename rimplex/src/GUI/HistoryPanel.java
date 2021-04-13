package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
  
  private JScrollPane scrollList;
  
  private JTextArea historyText;
  
  
  
  //----------Constructors----------
  
  
  
  /**
   * Default Constructor.
   */
  private HistoryPanel()
  {
    createComponents();
    setComponenets();
    addComponents();    
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
  
  
  
  //----------Private Methods----------
  
  
  
  /**
   * addComponents - Will add the components to their correct positions.
   */
  private void addComponents()
  {
    setLayouts();
    add(scrollList);
    scrollList.add(historyText);
  } // addComponents method.
  
  /**
   * createComponenets - Will create the components.
   */
  private void createComponents()
  {
    scrollList = new JScrollPane();
    
    historyText = new JTextArea();
  } // createComponenets method.
  
  /**
   * setComponents - Will set the components attributes.
   */
  private void setComponenets()
  {
    scrollList.setSize(new Dimension(200, 500));
    
    historyText.setFont(new Font("Arial", Font.BOLD, 20));
    historyText.setLineWrap(true);
    historyText.setWrapStyleWord(true);
    historyText.setSize(new Dimension(200, 500));
    
    setSize(200, 500);
    setVisible(false);
  } // setComponenets method.
  
  /**
   * setLayouts - Will set the layouts of components.
   */
  private void setLayouts()
  {
    setLayout(new FlowLayout());
  } // setLayouts method.
  
  
  
} // HistoryPanel class.
