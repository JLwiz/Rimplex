package Color;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A GUI for the marketing team to use when selecting colors.
 * 
 * @author Benjamin Huber
 * @version 4/27/2021
 */
public class ColorSelector extends JFrame
{
  /**
   * 
   */
  private static final long serialVersionUID = -8657371588811888341L;
  private static ColorSelector single_instance = null;

  private ColorSelectorListener listener;
  private JButton ok;
  private JTextField fcolorr, fcolorg, fcolorb, bcolorr, bcolorg, bcolorb;
  private String redText = "R: ", greenText = "G: ", blueText = "B: ";

  /**
   * Default constructor.  Creates the colorSelector frame when called.
   */
  private ColorSelector()
  {
    listener = new ColorSelectorListener();

    GridBagConstraints format = new GridBagConstraints();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new GridBagLayout());
    setVisible(true);
    setTitle("Color Selector");
    format.gridx = 0;
    format.gridy = 0;
    format.anchor = GridBagConstraints.CENTER;

    getContentPane().add(createForegroundPanel(), format);

    format.gridx = 1;
    getContentPane().add(createBackgroundPanel(), format);

    format.gridwidth = 2;
    format.gridy = 1;
    format.gridx = 0;
    ok = new JButton("OK");
    getContentPane().add(ok, format);
    ok.addActionListener(listener);

    setSize(300, 150);
  }

  /**
   * Singleton object.
   * 
   * @return the one and only CalculatorDisplay object.
   */
  public static ColorSelector getInstance()
  {
    if (single_instance == null)
      single_instance = new ColorSelector();
    return single_instance;
  }

  /**
   * Helper method for constructor, creates the left side of the input block.
   * 
   * @return the Panel to paste to the constructor.
   */
  private JPanel createForegroundPanel()
  {

    GridBagConstraints format = new GridBagConstraints();

    JPanel colorSelectorPanel = new JPanel(new GridBagLayout());

    format.fill = GridBagConstraints.NONE;
    format.gridheight = 1;
    format.gridwidth = 2;
    format.gridx = 0;
    format.gridy = 0;

    JLabel foreground = new JLabel("Foreground: ");
    colorSelectorPanel.add(foreground, format);

    format.gridwidth = 1;
    format.gridy = 1;
    JLabel red = new JLabel(redText);
    colorSelectorPanel.add(red, format);

    format.gridy = 2;
    JLabel green = new JLabel(greenText);
    colorSelectorPanel.add(green, format);

    format.gridy = 3;
    JLabel blue = new JLabel(blueText);
    colorSelectorPanel.add(blue, format);

    format.gridx = 1;
    format.gridy = 1;
    fcolorr = new JTextField(3);
    colorSelectorPanel.add(fcolorr, format);

    format.gridy = 2;
    fcolorg = new JTextField(3);
    colorSelectorPanel.add(fcolorg, format);

    format.gridy = 3;
    fcolorb = new JTextField(3);
    colorSelectorPanel.add(fcolorb, format);

    return colorSelectorPanel;
  }

  /**
   * Helper method for constructor, creates the right side of the input block.
   * 
   * @return the Panel to paste to the constructor.
   */
  private JPanel createBackgroundPanel()
  {

    GridBagConstraints format = new GridBagConstraints();

    JPanel colorSelectorPanel = new JPanel(new GridBagLayout());

    format.fill = GridBagConstraints.NONE;
    format.gridheight = 1;
    format.gridwidth = 2;
    format.gridx = 0;
    format.gridy = 0;

    JLabel background = new JLabel("Background: ");
    colorSelectorPanel.add(background, format);

    format.gridwidth = 1;
    format.gridy = 1;
    JLabel red = new JLabel(redText);
    colorSelectorPanel.add(red, format);

    format.gridy = 2;
    JLabel green = new JLabel(greenText);
    colorSelectorPanel.add(green, format);

    format.gridy = 3;
    JLabel blue = new JLabel(blueText);
    colorSelectorPanel.add(blue, format);

    format.gridx = 1;
    format.gridy = 1;
    bcolorr = new JTextField(3);
    colorSelectorPanel.add(bcolorr, format);

    format.gridy = 2;
    bcolorg = new JTextField(3);
    colorSelectorPanel.add(bcolorg, format);

    format.gridy = 3;
    bcolorb = new JTextField(3);
    colorSelectorPanel.add(bcolorb, format);

    return colorSelectorPanel;
  }

  /**
   * Grabs the Foreground colors column entries from the JPanel.
   * 
   * @return an int array containing [r, g, b]
   * @throws NumberFormatException when a foreground field is not an integer
   */
  public int[] getForegroundColors() throws NumberFormatException
  {
    int output[] = new int[3];
    try
    {
      output[0] = Integer.parseInt(fcolorr.getText());
      output[1] = Integer.parseInt(fcolorg.getText());
      output[2] = Integer.parseInt(fcolorb.getText());
    }
    catch (NumberFormatException e)
    {
      System.out.println("A foreground field is not an integer.");
    }

    return output;
  }

  /**
   * Grabs the Background colors column entries from the JPanel.
   * 
   * @return an int array containing [r, g, b]
   * @throws NumberFormatException when a background field is not an integer
   */
  public int[] getBackgroundColors() throws NumberFormatException
  {
    int output[] = new int[3];
    try
    {
      output[0] = Integer.parseInt(bcolorr.getText());
      output[1] = Integer.parseInt(bcolorg.getText());
      output[2] = Integer.parseInt(bcolorb.getText());
    }
    catch (NumberFormatException e)
    {
      System.out.println("A background field is not an integer.");
    }
    return output;
  }

  /**
   * Sets the "OK" button to preview the color based on the colors provided to it.
   * 
   * @param foreground the foreground "text" color
   * @param background the background color
   */
  public void setOKColor(final Color foreground, final Color background)
  {
    ok.setForeground(foreground);
    ok.setBackground(background);
  }

  /**
   * Creates the colorSelector.
   * 
   * @param args command line arguments
   */
  public static void main(final String[] args)
  {
    ColorSelector.getInstance();
  }
}
