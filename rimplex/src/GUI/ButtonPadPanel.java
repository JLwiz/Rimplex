package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.*;

public class ButtonPadPanel extends JPanel
{
  /**
   * Generated serialID.
   */
  private static final long serialVersionUID = 3687720860105566166L;
  private static final int MAXFONTSIZE = 30;
  private static final String FONT = "Arial";
  private static ButtonPadPanel single_instance = null;
  private static Color foreground = new Color(255, 255, 255);
  private static Color background = new Color(0, 0, 0);
  
  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints numpad = new GridBagConstraints();
  private CalcListener listener;
  
  private ArrayList<String> buttonNames;
  private HashMap<String, JButton> buttonMap;
  
  private JButton modeButton;
  private int mode = 0;
  
  /**
   * The default constructor for our ButtonPadPanel.
   */
  private ButtonPadPanel()
  {
    try
    {
      int colors[] = fetchColors();
      setForeground(colors[0], colors[1], colors[2]);
      setBackground(colors[3], colors[4], colors[5]);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("ERROR RETRIEVING THEMEING");
    }
    listener = CalcListener.getInstance();
    buttonMap = new HashMap<>();
    buttonNames = new ArrayList<>();
    setLayout(layout);
    addUtilitiesBar(0, 0);
    addNumberButtons(0, 1);
    addOperationsColumn(3, 0);
    addMiscColumns(4, 0);
  } // constructor.
  
  /**
   * Singleton for ButtonPadPanel Object.
   * 
   * @return the one and only ButtonPadPanel object.
   */
  public static ButtonPadPanel getInstance()
  {
    if (single_instance == null)
      single_instance = new ButtonPadPanel();
    return single_instance;
  }
  
  /**
   * pressButton - Will press the button whos ID is passed through.
   * 
   * @param buttonID (String)
   */
  public void pressButton (final String buttonID)
  {
    buttonMap.get(buttonID).setEnabled(true);
    buttonMap.get(buttonID).doClick();
    if (!buttonMap.get(buttonID).isFocusable()) buttonMap.get(buttonID).setEnabled(false);
  }

  /**
   * toggleFocus - Will toggle the focus of all components in this panel.
   * 
   * @param focusable
   *          boolean
   */
  public void toggleFocus(final boolean focusable)
  {
    this.setFocusable(focusable);
    for (String name : buttonNames)
    {
      buttonMap.get(name).setFocusable(focusable);
      buttonMap.get(name).setEnabled(focusable);
    }
  } // toggleFocus method.
  
  /**
   * Adds the "Miscellaneous Column" to the button pad.  It should be noted that the "Miscellaneous
   * Column" takes up 1 horizontal space and 5 vertical space on the GridBag.
   * 
   * @param leftEdge the left edge of the "Miscellaneous Column"
   * @param topEdge the top edge of the "Miscellaneous Column"
   */
  private void addMiscColumns(final int leftEdge, final int topEdge) 
  {
    numpad.gridx = leftEdge;
    numpad.gridy = topEdge;
    numpad.fill = GridBagConstraints.BOTH;
    numpad.weightx = 1;
    numpad.weighty = 1;
    numpad.gridwidth = 1;
    numpad.gridheight = 1;
    
    JButton resetButton = createButton("R", "reset");
    add(resetButton, numpad);
    
    numpad.gridy = topEdge + 1;
    JButton inverseButton = createButton("Inv", "inverse");
    add(inverseButton, numpad);
    
    numpad.gridy = topEdge + 2;
    JButton openParenthasesButton = createButton("(", "open parenthases");
    add(openParenthasesButton, numpad);
    
    numpad.gridy = topEdge + 3;
    JButton closedParenthasesButton = createButton(")", "closed parenthases");
    add(closedParenthasesButton, numpad);
    
    numpad.gridy = topEdge + 4;
    JButton decimalButton = createButton(".", "decimal");
    add(decimalButton, numpad);
    
    numpad.gridx = leftEdge + 1;
    numpad.gridy = topEdge;
    modeButton = createButton("DEC", "mode");
    add(modeButton, numpad);
    
    numpad.gridy = topEdge + 1;
    JButton conjugateButton = createButton("Con", "conjugate");
    add(conjugateButton, numpad);
    
    numpad.gridy = topEdge + 2;
    JButton logButton = createButton("log", "logarithm");
    add(logButton, numpad);
    
    numpad.gridy = topEdge + 3;
    JButton expButton = createButton("^", "exponent");
    add(expButton, numpad);
    
    numpad.gridy = topEdge + 4;
    JButton sqrtButton = createButton("<html>&#8730</html>", "squareroot");
    add(sqrtButton, numpad);
    
    numpad.gridx = leftEdge + 2;
    numpad.gridy = topEdge;
    JButton sinButton = createButton("sin", "sin");
    add(sinButton, numpad);
    
    numpad.gridy = topEdge + 1;
    JButton cosButton = createButton("cos", "cos");
    add(cosButton, numpad);
    
    numpad.gridy = topEdge + 2;
    JButton tanButton = createButton("tan", "tan");
    add(tanButton, numpad);
    
    numpad.gridy = topEdge + 3;
    JButton realPartButton = createButton("RP", "realpart");
    add(realPartButton, numpad);
    
    numpad.gridy = topEdge + 4;
    JButton imaginaryPartButton = createButton("IP", "imaginarypart");
    add(imaginaryPartButton, numpad);
    
    numpad.gridx = leftEdge + 3;
    numpad.gridy = topEdge;
    numpad.gridheight = 5;
    numpad.weightx = 0.1;
    JButton historyButton = createButton(">", "history");
    add(historyButton, numpad);
  }
  /**
   * Creates and adds the "Number pad" to the JPanel. Should be noted that the number pad takes up 3
   * horizontal space and 4 vertical space in a rectangle on the GridBag.
   * 
   * @param leftEdge
   *          the left edge coordinate of the "Number pad"
   * @param topEdge
   *          the top edge coordinate of the Number pad"
   */
  private void addNumberButtons(final int leftEdge, final int topEdge)
  {
    numpad.gridx = leftEdge;
    numpad.gridy = topEdge;
    numpad.fill = GridBagConstraints.BOTH;
    numpad.weightx = 1;
    numpad.weighty = 1;

    JButton oneButton = createButton("1", "1");
    add(oneButton, numpad);

    numpad.gridx = leftEdge + 1;
    JButton twoButton = createButton("2", "2");
    add(twoButton, numpad);

    numpad.gridx = leftEdge + 2;
    JButton threeButton = createButton("3", "3");
    add(threeButton, numpad);

    numpad.gridx = leftEdge;
    numpad.gridy = topEdge + 1;
    JButton fourButton = createButton("4", "4");
    add(fourButton, numpad);

    numpad.gridx = leftEdge + 1;
    JButton fiveButton = createButton("5", "5");
    add(fiveButton, numpad);

    numpad.gridx = leftEdge + 2;
    JButton sixButton = createButton("6", "6");
    add(sixButton, numpad);

    numpad.gridx = leftEdge;
    numpad.gridy = topEdge + 2;
    JButton sevenButton = createButton("7", "7");
    add(sevenButton, numpad);

    numpad.gridx = leftEdge + 1;
    JButton eightButton = createButton("8", "8");
    add(eightButton, numpad);

    numpad.gridx = leftEdge + 2;
    JButton nineButton = createButton("9", "9");
    add(nineButton, numpad);

    numpad.gridx = leftEdge;
    numpad.gridy = topEdge + 3;
    numpad.gridwidth = 2;
    JButton zeroButton = createButton("0", "0");
    add(zeroButton, numpad);

    numpad.gridx = leftEdge + 2;
    numpad.gridy = topEdge + 3;
    numpad.gridwidth = 1;
    JButton iButton = createButton("<html><i>i</i></html>", "i");
    add(iButton, numpad);
  }
  
  /**
   * Adds the "Operations Column" to the button pad.  It should be noted that the "Operations 
   * Column" takes up 1 horizontal space and 5 vertical space on the GridBag.
   * @param leftEdge
   * @param topEdge
   */
  private void addOperationsColumn(final int leftEdge, final int topEdge) {
    numpad.gridx = leftEdge;
    numpad.gridy = topEdge;
    numpad.fill = GridBagConstraints.BOTH;
    numpad.weightx = 1;
    numpad.weighty = 1;
    numpad.gridwidth = 1;
    numpad.gridheight = 1;
    
    JButton additionButton = createButton("+", "add");
    add(additionButton, numpad);
    
    numpad.gridy = topEdge + 1;
    JButton subtractionButton = createButton("-", "subtract");
    add(subtractionButton, numpad);
    
    numpad.gridy = topEdge + 2;
    JButton multiplicationButton = createButton("×", "multiply");
    add(multiplicationButton, numpad);
    
    numpad.gridy = topEdge + 3;
    JButton divisionButton = createButton("÷", "divide");
    add(divisionButton, numpad);
    
    numpad.gridy = topEdge + 4;
    JButton equalsButton = createButton("=", "equals");
    add(equalsButton, numpad);
  }
  
  /**
   * Creates and adds the "Utilities bar" to the JPanel.  Should be noted that the "Utilities Bar"
   * takes up 3 horizontal space and 1 vertical space on the GridBag.
   * 
   * @param leftEdge the left edge coordinate of the "Utilities Bar"
   * @param topEdge the top edge coordinate of the "Utilities Bar"
   */
  private void addUtilitiesBar(final int leftEdge, final int topEdge)
  {
    numpad.gridx = leftEdge;
    numpad.gridy = topEdge;
    numpad.fill = GridBagConstraints.BOTH;
    numpad.weightx = 1;
    numpad.weighty = 1;
    numpad.gridwidth = 1;
    numpad.gridheight = 1;
    
    JButton signButton = createButton("±", "sign");
    add(signButton, numpad);
    
    numpad.gridx = leftEdge + 1;
    JButton clearButton = createButton("C", "clear");
    add(clearButton, numpad);
    
    numpad.gridx = leftEdge + 2;
    JButton backspaceButton = createButton("<html>&#8592</html>", "backspace");
    add(backspaceButton, numpad);
  }

  /**
   * Butchered from "CalculatorDisplay.java", thank you Andrew.
   * 
   * @param title the text to display on the Button
   * @param name the internal name for switch cases
   * @return JButton the created button
   */
  private JButton createButton(final String title, final String name)
  {
    JButton b = new JButton(title);
    b.setName(name);
    b.setBackground(background);
    b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    b.setForeground(foreground);
    b.setFont(new Font(FONT, Font.BOLD, MAXFONTSIZE));
    b.setFocusable(true);
    b.addActionListener(listener);
    b.addKeyListener(listener);
    buttonMap.put(name, b);
    buttonNames.add(name);
    
    return b;
  }
  
  public static void setForeground(int a, int b, int c) {
    foreground = new Color(a, b, c);
  }
  
  public static void setBackground(int a, int b, int c) {
    background = new Color(a, b, c);
  }
  
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
   * For use in updating the display of the calculator to correctly display what mode its in.
   * 
   * @param isFraction whether or not the calculator is in fractional mode.
   */
  public int updateMode() {
    String text = modeButton.getText();
    if (text.equals("POLAR"))
    {
      mode = 1;
      modeButton.setText("FRAC");
    } else if (text.equals("FRAC"))
    {
      mode = 0;
      modeButton.setText("DEC");
    } else if (text.equals("DEC")) 
    {
      mode = 2;
      modeButton.setText("POLAR");
    }
    return mode;
  }
}
