package Color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GUI.CalculatorDisplay;

public class ColorSelector extends JFrame
{
  /**
   * 
   */
  private static final long serialVersionUID = -8657371588811888341L;
  private static ColorSelector single_instance = null;
  private JTextField fcolorr, fcolorg, fcolorb, bcolorr, bcolorg, bcolorb;
  
  private ColorSelectorListener listener;
  public ColorSelector() {
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
    JButton ok = new JButton("OK");
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
   * @return the Panel to paste to the constructor.
   */
  private JPanel createForegroundPanel() {
    
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
    JLabel red = new JLabel("R: ");
    colorSelectorPanel.add(red, format);
    
    format.gridy = 2;
    JLabel green = new JLabel("G: ");
    colorSelectorPanel.add(green, format);
    
    format.gridy = 3;
    JLabel blue = new JLabel("B: ");
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
   * @return the Panel to paste to the constructor.
   */
  private JPanel createBackgroundPanel() {
    
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
    JLabel red = new JLabel("R: ");
    colorSelectorPanel.add(red, format);
    
    format.gridy = 2;
    JLabel green = new JLabel("G: ");
    colorSelectorPanel.add(green, format);
    
    format.gridy = 3;
    JLabel blue = new JLabel("B: ");
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
  
  public int[] getForegroundColors() {
    int output[] = new int[3];
    output[0] = Integer.parseInt(fcolorr.getText());
    output[1] = Integer.parseInt(fcolorg.getText());
    output[2] = Integer.parseInt(fcolorb.getText());
    
    return output;
  }
  
  public int[] getBackgroundColors() {
    int output[] = new int[3];
    output[0] = Integer.parseInt(bcolorr.getText());
    output[1] = Integer.parseInt(bcolorg.getText());
    output[2] = Integer.parseInt(bcolorb.getText());
    
    return output;
  }
  public static void main(String[] args)
  {
    ColorSelector.getInstance();
  }
}
