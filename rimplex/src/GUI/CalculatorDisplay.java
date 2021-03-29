package GUI;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import controller.CalcListener;

/**
 * GUI for the calculator.
 * 
 * @author Andrew Fryer
 * @version 1.0 (03/23/2021)
 */
public class CalculatorDisplay extends JFrame
{
  // ----------Declarations----------
  private static final long serialVersionUID = 1119406259556735502L;
  private static final int MAXFONTSIZE = 30;
  private static final int MINFONTSIZE = 13;
  private static final String FONT = "Arial";

  private static CalculatorDisplay single_instance = null;
  private CalcListener listener;

  private JButton addition;
  private JButton clear;
  private JButton division;
  private JButton equals;
  private JButton multiplication;
  private JButton reset;
  private JButton subtraction;

  private JLabel display;

  private JPanel buttonPanel;
  private JPanel centerPanel;
  private JPanel mainPanel;
  private JPanel northPanel;

  private JTextField inputField;

  // ----------Constructor----------

  /**
   * Default Constructor.
   */
  public CalculatorDisplay()
  {
    setSize(new Dimension(400, 300));

    listener = CalcListener.getInstance();
    createComponents();
    createMenuBar();
    setComponents();
    setLayouts();
    addComponents();
    ImageIcon img = new ImageIcon("rimplex/src/iconRimplex.png");
    setIconImage(img.getImage());
    setTitle("Rimplex");
    getRootPane().setBorder(BorderFactory.createLoweredBevelBorder());
    getContentPane().add(mainPanel);
    setLocationRelativeTo(null);

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  } // default constructor.

  // ----------Singleton----------

  /**
   * Singleton object.
   * 
   * @return the one and only CalculatorDisplay object.
   */
  public static CalculatorDisplay getInstance()
  {
    if (single_instance == null)
      single_instance = new CalculatorDisplay();
    return single_instance;
  } // getInstance method.

  // ----------Public Methods----------

  /**
   * clearInputField - Will clear the text in the input field.
   */
  public void clearInputField()
  {
    inputField.setText("");
  } // clearInputField method.

  /**
   * Get Method for the display component.
   * 
   * @return JLabel - the display
   */

  public JLabel getDisplay()
  {
    return display;
  }

  /**
   * get method for the InputFeild component.
   * 
   * @return JTextField - the inputField
   */

  public JTextField getInputField()
  {
    return inputField;
  }
  
  /**
   * setDisplay - Will set the displays test and adjust font if necessary.
   * 
   * @param text (String)
   */
  public void setDisplay(final String text)
  {
    display.setText(text);
    adjustFont();
  } // setDisplay method.
  
  /**
   * invalidStatus - Will change the color of the JPanel based on the the validity of the content in
   * the text field.
   * 
   * @param invalid true if there is an error present, otherwise false.
   *          (boolean)
   */
  public void invalidStatus(final boolean invalid, final String message)
  {
    if (invalid)
    {
      inputField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
      JOptionPane.showMessageDialog(this, message);
    }
    else
    {
      inputField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    }
  } // validStatus method.

  // ----------Private Methods---------

  /**
   * addComponents - Will add the components to the panel.
   */
  private void addComponents()
  { 
    mainPanel.add(northPanel);
    mainPanel.add(centerPanel);
    mainPanel.add(buttonPanel);

    northPanel.add(display);

    centerPanel.add(inputField);
    
    buttonPanel.add(reset);
    buttonPanel.add(clear);
    buttonPanel.add(addition);
    buttonPanel.add(subtraction);
    buttonPanel.add(multiplication);
    buttonPanel.add(division);
    buttonPanel.add(equals);

  } // addComponents method.
  
  /**
   * adjustFont - Will adjust the font size of the display field depending
   * on the amount of characters in the display.
   */
  private void adjustFont()
  {
    
    if (display.getText().length() > 23) display.setFont(new Font(FONT, Font.BOLD, MINFONTSIZE));
    else display.setFont(new Font(FONT, Font.BOLD, MAXFONTSIZE));
  } // adjustFont method.

  /**
   * createComponents - Will create the components in the GUI.
   */
  private void createComponents()
  {
    addition = setButton("add", "+");
    clear = setButton("clear", "C");
    clear.setBackground(Color.GRAY);
    division = setButton("divide", "÷");
    equals = setButton("equals", "=");
    multiplication = setButton("multiply", "×");
    reset = setButton("reset", "R");
    reset.setBackground(Color.RED);
    subtraction = setButton("subtract", "-");

    display = new JLabel();

    buttonPanel = new JPanel();
    mainPanel = new JPanel();
    northPanel = new JPanel();
    centerPanel = new JPanel();

    inputField = new JTextField();
  } // createComponents method.

  /**
   * setButton - Will create a button object and return it.
   * 
   * @param name
   *          (String)
   * @param title
   *          (String)
   * 
   * @return a button object with set attributes.
   */
  private JButton setButton(final String name, final String title)
  {
    JButton b = new JButton(title);
    b.setName(name);
    b.setBackground(Color.BLACK);
    b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    b.setForeground(Color.WHITE);
    b.setFont(new Font(FONT, Font.BOLD, MAXFONTSIZE));
    b.addActionListener(listener);

    return b;
  } // setButton method.

  /**
   * setComponents - Will set the components correctly.
   */
  private void setComponents()
  {
    inputField.setFont(new Font(FONT, Font.LAYOUT_RIGHT_TO_LEFT, MAXFONTSIZE));
    inputField.setHorizontalAlignment(JTextField.RIGHT);
    inputField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

    display.setFont(new Font(FONT, Font.BOLD, MAXFONTSIZE));
    display.setBackground(Color.WHITE);
    display.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
  } // setComponents method.

  /**
   * setLayouts - Will set the layouts of the JPanels.
   */
  private void setLayouts()
  {
    mainPanel.setLayout(new GridLayout(3, 0));
    northPanel.setLayout(new GridLayout(1, 0));
    centerPanel.setLayout(new GridLayout(1, 0));
    buttonPanel.setLayout(new GridLayout(0, 7));
  } // setLayouts method.
  
  /**
   * Creates a menu bar for the application.
   * 
   * This will be used for implementing themes, history, and saving history.
   */
  private void createMenuBar() 
  {
    
    ImageIcon themeIcon = new ImageIcon("rimplex/src/theme-icon.png");
    JMenuItem themes = new JMenuItem("Themes", themeIcon);
    JMenu optionsMenu = new JMenu("Options");
    optionsMenu.add(themes);
    
    ImageIcon historyIcon = new ImageIcon("rimplex/src/history-icon.png");
    JMenuItem history = new JMenuItem("Show History", historyIcon);
    history.addActionListener(listener);
    ImageIcon importIcon = new ImageIcon("rimplex/src/import-icon.png");
    JMenuItem open = new JMenuItem("Import File", importIcon);   
    ImageIcon saveIcon = new ImageIcon("rimplex/src/save-icon.png");
    JMenuItem save = new JMenuItem("Save", saveIcon);
    JMenu fileMenu = new JMenu("File");
    fileMenu.add(history);
    fileMenu.add(open);
    fileMenu.add(save);
    
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(optionsMenu);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar); 
    // If you want to hide the menu bar, set this to false.
    menuBar.setVisible(true);
  }
} // CalculatorDisplay class.
