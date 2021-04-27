package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;

import File.SaveHandler;
import controller.CalcListener;

/**
 * PlaybackWindow - A JWindow that will be used to record the calculator being used as well as play
 * the recordings.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/24/2021)
 */
public class PlaybackWindow extends JWindow
{

  // ----------Declarations----------

  private static final long serialVersionUID = 2287276262000046595L;
  private static final String DEMO = "(3+3i)+(3^3-6i)^4=(1)" + "(-441.349+38391i)×(441+3i)=(1)"
      + "(383+3848i)÷(383+3848i)=(1)" + "sin(33)=(1)" + "cos(33)=(1)" + "tan(33)=(1)"
      + "&#8730(334)=(1)" + "log(30)=(1)" + "Con(331)=(1)";
  private static PlaybackWindow single_instance = null;
  private CalculatorDisplay display = CalculatorDisplay.getInstance();
  private SaveHandler calcSaver;
  private boolean recording;
  private JButton close, open, pause, play, rec;
  private JPanel main, side;

  /**
   * Constructor.
   * 
   * @throws IOException
   */
  private PlaybackWindow() throws IOException
  {
    super(CalculatorDisplay.getInstance());
    recording = false;

    setSize(250, 50);
    setLocation(display.getX() + 495, display.getY() + 10);
    createComponents();
    setComponents();
    addComponents();
    getContentPane().add(main);

    setVisible(false);
  } // constructor.

  /**
   * Singleton.
   * 
   * @return PlaybackWindow object.
   * @throws IOException
   */
  public static PlaybackWindow getInstance() throws IOException
  {
    if (single_instance == null)
      single_instance = new PlaybackWindow();
    return single_instance;
  } // getInstance method.

  // ----------Public Methods----------

  /**
   * getRecording - Will return the recording selected.
   * 
   * @return equation selected String
   * @throws IOException
   *           - the IOException
   */
  public String getRecording() throws IOException
  {
    JFileChooser chooser = new JFileChooser();
    String input = null;
    chooser.setDialogTitle("Choose a recording file you would like to playback.");

    int userSelection = chooser.showOpenDialog(CalculatorDisplay.getInstance());

    if (userSelection == JFileChooser.APPROVE_OPTION)
    {
      File selected = chooser.getSelectedFile();
      input = calcSaver.readFile(selected);
      // stuff to validate file, read from, and return the equation string.
    }

    return input;
  } // getRecording method.

  /**
   * recording - Will return the boolean recording.
   * 
   * @return true if recording, false if otherwise.
   */
  public boolean recording()
  {
    return recording;
  } // recording method.

  /**
   * saveRecording - Will save the recording by writing it to a file, as well as getting a name from
   * the user and adding it to the JComboBox.
   * 
   * @param record
   *          String
   * @throws IOException
   *           - the IOException
   */
  public void saveRecording(final String record) throws IOException
  {
    if (record.trim().equals(""))
      return;

    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Where would you like to save your recording?");
    
    int userSelection = chooser.showSaveDialog(CalculatorDisplay.getInstance());
    
    if (userSelection == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = chooser.getSelectedFile();
      
      try
      {
        String fileName = selectedFile.getCanonicalPath();
        selectedFile = calcSaver.writeFile(fileName, record);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else return;
    
//    String name = (String) JOptionPane.showInputDialog(this,
//        "Please provide a name for your recording.", "Save Recording", JOptionPane.QUESTION_MESSAGE,
//        null, null, null);
//
//    if (name == null)
//      return;

    /*
     * logic for saving to file.
     */

//    calcSaver.writeFile(name, record);
  } // saveRecording method.

  /**
   * toggleIcon - Will change the recording icon.
   */
  public void toggleIcon()
  {
    ImageIcon icon;
    Image recimg;
    recording = !recording;
    if (recording)
    {
      icon = new ImageIcon(this.getClass()
          .getResource("/resources/images/" + "baseline_radio_button_checked_black_24dp.png"));
      recimg = icon.getImage();
      recimg = recimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      icon = new ImageIcon(recimg);
      play.setEnabled(false);
      pause.setEnabled(false);
    }
    else
    {
      icon = new ImageIcon(this.getClass()
          .getResource("/resources/images/" + "baseline_radio_button_unchecked_black_24dp.png"));
      recimg = icon.getImage();
      recimg = recimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      play.setEnabled(true);
      pause.setEnabled(true);
    }
    icon = new ImageIcon(recimg);
    rec.setIcon(icon);
  } // toggleIcon method.

  /**
   * toggleRecord - Will change toggle the ability to press the record button.
   * 
   * @param enabled
   *          boolean
   */
  public void toggleRecord(final boolean enabled)
  {
    rec.setEnabled(enabled);
  } // toggleRecord method.

  // -----------Private Methods-----------

  /**
   * addComponents - Will add components to the correct objects.
   */
  private void addComponents()
  {
    main.add(rec);
    main.add(play);
    main.add(pause);
    main.add(side);
    side.add(open);
    side.add(close);
  } // addComponents method.

  /**
   * createButton - Will return a JButton with specific attributes.
   * 
   * @param name
   *          String
   * @param title
   *          String
   * @return JButton.
   */
  private JButton createButton(final String name, final String title)
  {
    JButton b;
    ImageIcon icon;
    switch (name)
    {
      case "pause":
        icon = new ImageIcon(this.getClass()
            .getResource("/resources/images/" + "baseline_pause_circle_filled_black_24dp.png"));
        Image pauseimg = icon.getImage();
        pauseimg = pauseimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(pauseimg);
        b = new JButton(icon);
        break;
      case "play":
        icon = new ImageIcon(this.getClass()
            .getResource("/resources/images/" + "baseline_play_circle_black_24dp.png"));
        Image playimg = icon.getImage();
        playimg = playimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(playimg);
        b = new JButton(icon);
        break;
      case "record":
        icon = new ImageIcon(this.getClass()
            .getResource("/resources/images/" + "baseline_radio_button_unchecked_black_24dp.png"));
        Image recimg = icon.getImage();
        recimg = recimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(recimg);
        b = new JButton(icon);
        break;
      default:
        b = new JButton(title);
        break;
    }

    b.setName(name);
    // b.setBackground(Color.BLACK);
    // b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    // b.setForeground(Color.WHITE);
    b.setFont(new Font("Arial", Font.PLAIN, 10));
    b.setOpaque(false);
    b.setContentAreaFilled(false);
    b.setBorderPainted(false);
    b.addActionListener(CalcListener.getInstance());

    return b;
  } // createButton method.

  /**
   * createComponents - Will create the components.
   * 
   * @throws IOException
   */
  private void createComponents() throws IOException
  {
    calcSaver = SaveHandler.getInstance();
    close = createButton("close", "Close");
    open = createButton("open", "Open");
    pause = createButton("pause", "");
    play = createButton("play", "");
    rec = createButton("record", "");
    main = new JPanel();
    side = new JPanel();
  } // createComponents method.

  /**
   * setComponents - Will set each components attributes.
   */
  private void setComponents()
  {
    main.setLayout(new GridLayout(0, 4));
    side.setLayout(new GridLayout(2, 0));
  } // setComponents method.

} // PlaybackWindow class.
