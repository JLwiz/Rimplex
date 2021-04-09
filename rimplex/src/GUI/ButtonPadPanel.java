package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPadPanel extends JPanel
{
  /**
   * Generated serialID
   */
  private static final long serialVersionUID = 3687720860105566166L;
  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints numpad = new GridBagConstraints();
  private static final int MAXFONTSIZE = 30;
//  private static final int MINFONTSIZE = 13;
  private static final String FONT = "Arial";

  /**
   * The default constructor for our ButtonPadPanel.
   */
  public ButtonPadPanel()
  {
    setLayout(layout);
    addUtilitiesBar(0, 0);
    addNumberButtons(0, 1);
    addOperationsColumn(3, 0);
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
    JButton iButton = createButton("i", "i");
    add(iButton, numpad);
  }
  
  private void addOperationsColumn(final int leftEdge, final int topEdge) {
    numpad.gridx = leftEdge;
    numpad.gridy = topEdge;
    numpad.fill = GridBagConstraints.BOTH;
    numpad.weightx = 1;
    numpad.weighty = 1;
    numpad.gridwidth = 1;
    numpad.gridheight = 1;
    
    JButton additionButton = createButton("+", "addition");
    add(additionButton, numpad);
    
    numpad.gridy = topEdge + 1;
    JButton subtractionButton = createButton("-", "subtraction");
    add(subtractionButton, numpad);
    
    numpad.gridy = topEdge + 2;
    JButton multiplicationButton = createButton("�", "multiplication");
    add(multiplicationButton, numpad);
    
    numpad.gridy = topEdge + 3;
    JButton divisionButton = createButton("�", "division");
    add(divisionButton, numpad);
    
    numpad.gridy = topEdge + 4;
    JButton equalsButton = createButton("=", "equals");
    add(equalsButton, numpad);
  }
  
  /**
   * Creates and adds the "Utilities bar" to the JPanel.  Should be noted that the "Utilities Bar"
   * takes up 3 horizontal space in a rectangle on the GridBag.
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
    
    JButton inverseButton = createButton("�", "inverse");
    add(inverseButton, numpad);
    
    numpad.gridx = leftEdge + 1;
    JButton clearButton = createButton("C", "clear");
    add(clearButton, numpad);
    
    numpad.gridx = leftEdge + 2;
    JButton backspaceButton = createButton("<-", "backspace");
    add(backspaceButton, numpad);
  }

  /**
   * Butchered from "CalculatorDisplay.java", thank you Andrew.
   * 
   * @param title the text to display on the Button
   * @param name 
   * @return
   */
  private JButton createButton(final String title, final String name)
  {
    JButton b = new JButton(title);
    b.setName(name);
    b.setBackground(Color.BLACK);
    b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    b.setForeground(Color.WHITE);
    b.setFont(new Font(FONT, Font.BOLD, MAXFONTSIZE));
    return b;
  }
}
