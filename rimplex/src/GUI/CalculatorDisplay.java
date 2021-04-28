package GUI;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
  private static final String ITALICI = "<i>i</i>";

  private static CalculatorDisplay single_instance = null;
  private ResourceBundle strings;
  private CalcListener listener;

  private GridBagLayout layout = new GridBagLayout();
  private GridBagConstraints constraints = new GridBagConstraints();

  private JLabel display;
  private JLabel inputField;

  private JPanel centerPanel, mainPanel, northPanel;

  // ----------Constructor----------

  /**
   * Default Constructor.
   */
  public CalculatorDisplay()
  {
    setSize(new Dimension(500, 500));

    strings = ResourceBundle.getBundle("Language.Strings", Locale.getDefault());
    listener = CalcListener.getInstance();
    createComponents();
    createMenuBar();
    setComponents();
    setLayouts();
    addComponents();
    ImageIcon img = new ImageIcon(this.getClass().getResource("/logo/icon.png"));
    setIconImage(img.getImage());
    setTitle("Rimplex");
    getRootPane().setBorder(BorderFactory.createLoweredBevelBorder());
    getContentPane().add(mainPanel);
    setLocationRelativeTo(null);

    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
   * changeLanguage - Makes a new instance of CalculatorDisplay in the new default language.
   * @throws IOException - the IOException
   */

  public void changeLanguage() throws IOException
  {
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    single_instance = new CalculatorDisplay();
  }

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

  public JLabel getInputField()
  {
    return inputField;
  }

  /**
   * setDisplay - Will set the displays test and adjust font if necessary.
   * 
   * @param text
   *          (String)
   */
  public void setDisplay(final String text)
  {
    display.setText(replaceI(text));
    adjustFont(display);
  } // setDisplay method.

  /**
   * setInput - Will set the input text.
   * 
   * @param text
   *          (String)
   */
  public void setInput(final String text)
  {
    inputField.setText(text);
    adjustFont(inputField);
  }

  /**
   * invalidStatus - Will change the color of the JPanel based on the the validity of the content in
   * the text field.
   * 
   * @param invalid
   *          true if there is an error present, otherwise false. (boolean)
   * @param message
   *          the error message to display
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
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = 0.3;
    constraints.fill = GridBagConstraints.BOTH;
    mainPanel.add(northPanel, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.weighty = .8;
    constraints.weightx = 1;
    mainPanel.add(ButtonPadPanel.getInstance(), constraints);
    northPanel.setMinimumSize(new Dimension(50, 50));
    northPanel.setPreferredSize(new Dimension(50, 50));
    northPanel.setMaximumSize(new Dimension(50, 50));
    northPanel.add(display);
    northPanel.add(inputField);
  } // addComponents method.

  /**
   * adjustFont - Will adjust the font size of the display field depending on the amount of
   * characters in the display.
   * 
   * @param label
   *          JLabel
   */
  private void adjustFont(final JLabel label)
  {
    if (label.getText().length() > 23)
      label.setFont(new Font(FONT, Font.BOLD, MINFONTSIZE));
    else
      label.setFont(new Font(FONT, Font.BOLD, MAXFONTSIZE));
  } // adjustFont method.

  /**
   * createComponents - Will create the components in the GUI.
   */
  private void createComponents()
  {
    display = new JLabel();

    mainPanel = new JPanel();
    northPanel = new JPanel();
    centerPanel = new JPanel();

    inputField = new JLabel();
  } // createComponents method.

  /**
   * setComponents - Will set the components correctly.
   */
  private void setComponents()
  {
    inputField.setFont(new Font(FONT, Font.LAYOUT_RIGHT_TO_LEFT, MAXFONTSIZE));
    inputField.setHorizontalAlignment(JTextField.RIGHT);
    inputField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    inputField.setVisible(true);

    display.setFont(new Font(FONT, Font.BOLD, MAXFONTSIZE));
    display.setBackground(Color.WHITE);
    display.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    display.setVisible(true);
  } // setComponents method.

  /**
   * setLayouts - Will set the layouts of the JPanels.
   */
  private void setLayouts()
  {
    mainPanel.setLayout(layout);
    northPanel.setLayout(new GridLayout(2, 1));
    centerPanel.setLayout(new GridLayout(1, 0));
  } // setLayouts method.

  /**
   * Creates a menu bar for the application.
   * 
   * This will be used for implementing themes, history, and saving history.
   */
  private void createMenuBar()
  {
    JMenuItem print = new JMenuItem(strings.getString("PHistory"));
    print.setName("Print History");
    print.addActionListener(listener);
    JMenuItem playback = new JMenuItem(strings.getString("Playback"));
    playback.setName("Playback");
    playback.addActionListener(listener);
    // ImageIcon importIcon = new ImageIcon(
    // CalculatorDisplay.class.getResource("/images/import-icon.png"));
    // JMenuItem open = new JMenuItem("Import File");
    // ImageIcon saveIcon = new ImageIcon(
    // CalculatorDisplay.class.getResource("/images/save-icon.png"));
    // JMenuItem save = new JMenuItem("Save");
    JMenu fileMenu = new JMenu(strings.getString("File"));
    fileMenu.add(playback);
    fileMenu.add(print);

    JMenuItem spanishButton = new JMenuItem("Español");
    spanishButton.setName("Sp");
    JMenuItem englishButton = new JMenuItem("English");
    englishButton.setName("En");
    JMenuItem germanButton = new JMenuItem("Deutsche");
    germanButton.setName("Ger");
    JMenuItem frenchButton = new JMenuItem("Française");
    frenchButton.setName("Fr");
    JMenuItem aboutButton = new JMenuItem("About");
    aboutButton.setName("About");
    spanishButton.addActionListener(listener);
    englishButton.addActionListener(listener);
    germanButton.addActionListener(listener);
    frenchButton.addActionListener(listener);
    aboutButton.addActionListener(listener);
    JMenu languageMenu = new JMenu(strings.getString("Language"));
    JMenu settingsMenu = new JMenu(strings.getString("Settings"));
    JMenu helpMenu = new JMenu(strings.getString("Help"));

    helpMenu.add(aboutButton);
    languageMenu.add(englishButton);
    languageMenu.add(spanishButton);
    languageMenu.add(germanButton);
    languageMenu.add(frenchButton);
    settingsMenu.add(languageMenu);

    JMenuBar menuBar = new JMenuBar();
    // menuBar.add(optionsMenu);
    menuBar.add(fileMenu);
    // menuBar.add(settingsMenu);
    menuBar.add(helpMenu);
    setJMenuBar(menuBar);
    // If you want to hide the menu bar, set this to false.
    menuBar.setVisible(true);
  }

  /**
   * replaceI - Will replace all normal i's with italics.
   * 
   * @param text
   *          (String)
   * @return a string with italic i's
   */
  private String replaceI(final String text)
  {
    String temp = text.replaceAll("<html>", "");
    temp = temp.replaceAll("</html>", "");
    temp = temp.replaceAll("<i>", "");
    temp = temp.replaceAll("</i>", "");
    temp = "<html>" + temp + "</html>";

    return temp.replaceAll("i", ITALICI);
  }
} // CalculatorDisplay class.
