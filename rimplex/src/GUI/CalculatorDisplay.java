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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import calculations.Equation;
import controller.CalcListener;

/**
 * GUI for the calculator.
 * 
 * @author Andrew Fryer
 * @version 1.0 (03/23/2021)
 */
public class CalculatorDisplay extends JFrame
{
  //----------Declarations----------
  private static final long serialVersionUID = 1119406259556735502L;
  private static final String FONT = "Arial";
  
  private static CalculatorDisplay single_instance = null;
  
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
  
  
  
  //----------Constructor----------
  
  
  
  /**
   * Default Constructor.
   */
  public CalculatorDisplay()
  {
    setSize(new Dimension(400, 300));
    
    createComponents();
    setComponents();
    setLayouts();
    addComponents();
    ImageIcon img = new ImageIcon("rimplex/src/logoRimplex.png");
    setIconImage(img.getImage());
    getRootPane().setBorder(BorderFactory.createLoweredBevelBorder());
    setContentPane(mainPanel);
    setLocationRelativeTo(null);
    
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  } // default constructor.
  
  
  
  //----------Singleton----------


  /**
   * Singleton object.
   * 
   * @return the one and only CalculatorDisplay object.
   */
  public static CalculatorDisplay getInstance()
  {
    if (single_instance == null) single_instance = new CalculatorDisplay();
    return single_instance;
  } // getInstance method.
  
  
  
  
  //----------Public Methods----------
  
  
  
  
  /**
   * addOperator - Will add the operator of the string passed on through
   * the parameter to the equation in the display.
   * 
   * @param op (String)
   */
  public void addOperator(String op)
  {
    // If we are using operators to add operands.
    if (inputField.getText().length() > 0 
        && display.getText().length() == 0) //***if input parser returns true***
    {
      display.setText("(" + inputField.getText() + ")" + op);
      clearInputField();
      //Equation.getInstance().setOperator(op);
    }
    
  } // addOperator method.
  
  /**
   * clearDisplay - Will clear the text in the display.
   */
  public void clearDisplay()
  {
    display.setText("");
  } // clearDisplay method.
  
  /**
   * clearInputField - Will clear the text in the input field.
   */
  public void clearInputField()
  {
    inputField.setText("");
  } // clearInputField method.
  
  /**
   * validStatus - Will change the color of the JPanel based on the
   * the validity of the content in the text field.
   * 
   * @param valid (boolean)
   */
  public void validStatus(boolean valid)
  {
    if (valid) inputField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
    else inputField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
  } // validStatus method.
  
  
  
  //----------Private Methods---------
  
  
  
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
    subtraction = setButton("subtract", "–");
    
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
   * @param name (String)
   * @param title (String)
   *    
   * @return a button object with set attributes.
   */
  private JButton setButton(String name, String title)
  {
    JButton b = new JButton(title);
    b.setName(name);
    b.setBackground(Color.BLACK);
    b.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    b.setForeground(Color.WHITE);
    b.setFont(new Font(FONT, Font.BOLD, 30));
    b.addActionListener(new CalcListener());
    
    return b;
  } // setButton method.
  
  /**
   * setComponents - Will set the components correctly.
   */
  private void setComponents()
  {
    inputField.setFont(new Font(FONT, Font.LAYOUT_RIGHT_TO_LEFT, 30));
    inputField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    
    display.setFont(new Font(FONT, Font.BOLD, 30));
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
  
} // CalculatorDisplay class.
