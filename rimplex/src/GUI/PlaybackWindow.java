package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;

import controller.CalcListener;

/**
 * PlaybackWindow - A JWindow that will be used to record
 * the calculator being used as well as play the recordings.
 * 
 * @author Andrew Fryer
 * @version 1.0 (04/24/2021)
 */
public class PlaybackWindow extends JWindow
{

  // ----------Declarations----------
  
  private static final long serialVersionUID = 2287276262000046595L;
  private static final String DEMO = "(3+3i)+(3^3-6i)^4=(1)"
      + "(-441.349+38391i)×(441+3i)=(1)"
      + "(383+3848i)÷(383+3848i)=(1)"
      + "sin(33)=(1)"
      + "cos(33)=(1)"
      + "tan(33)=(1)"
      + "&#8730(334)=(1)"
      + "log(30)=(1)"
      + "Con(331)=(1)";
  private static PlaybackWindow single_instance = null;
  private CalculatorDisplay display = CalculatorDisplay.getInstance();
  private boolean recording;
  private HashMap<String, String> saved;
  private JButton close, pause, play, rec;
  private JComboBox<String> select;
  private JPanel main, side;
  
  /**
   * Constructor.
   */
  private PlaybackWindow()
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
   */
  public static PlaybackWindow getInstance()
  {
    if (single_instance == null) single_instance = new PlaybackWindow();
    return single_instance;
  } // getInstance method.
  
  // ----------Public Methods----------
  
  /**
   * getRecording - Will return the recording selected.
   * 
   * @return equation selected
   *              String
   */
  public String getRecording()
  {
    return saved.get(select.getSelectedItem());
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
   * saveRecording - Will save the recording by writing it to a file,
   * as well as getting a name from the user and adding it to the JComboBox.
   * 
   * @param record
   *          String
   */
  public void saveRecording(final String record)
  {
    if (record.trim().equals("")) return;
    
    String name = (String)JOptionPane.showInputDialog(this,
        "Please provide a name for your recording.",
        "Save Recording", JOptionPane.QUESTION_MESSAGE, null, null, null);
    
    if (name == null) return;
    
    /*
     * logic for saving to file.
     */
    
    saved.put(name, record);
    select.addItem(name);
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
      icon = new ImageIcon(this.getClass().getResource("/resources/images/recording.jpg"));
      recimg = icon.getImage();
      recimg = recimg.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
      icon = new ImageIcon(recimg);
      play.setEnabled(false);
      pause.setEnabled(false);
    }
    else
    {
      icon = new ImageIcon(this.getClass().getResource("/resources/images/pre-recording.jpg"));
      recimg = icon.getImage();
      recimg = recimg.getScaledInstance(75, 50, java.awt.Image.SCALE_SMOOTH);
      play.setEnabled(true);
      pause.setEnabled(true);
    }
    icon = new ImageIcon(recimg);
    rec.setIcon(icon);
  } // toggleIcon method.
  
  /**
   * toggleRecord - Will change toggle the ability to press the
   * record button.
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
    side.add(close);
    side.add(select);
  } // addComponents method.
  
  
  /**
   * createButton - Will return a JButton with specific
   * attributes.
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
        icon = new ImageIcon(this.getClass().getResource("/resources/images/pause.jpg"));
        Image pauseimg = icon.getImage();
        pauseimg = pauseimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(pauseimg);
        b = new JButton(icon);
        break;
      case "play":
        icon = new ImageIcon(this.getClass().getResource("/resources/images/play.jpg"));
        Image playimg = icon.getImage();
        playimg = playimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(playimg);
        b = new JButton(icon);
        break;
      case "record":
        icon = new ImageIcon(this.getClass().getResource("/resources/images/pre-recording.jpg"));
        Image recimg = icon.getImage();
        recimg = recimg.getScaledInstance(75, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(recimg);
        b = new JButton(icon);
        break;
      default:
        b = new JButton(title);
        break;
    }
    
    b.setName(name);
//    b.setBackground(Color.BLACK);
//    b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
//    b.setForeground(Color.WHITE);
    b.setFont(new Font("Arial", Font.PLAIN, 10));
    b.setOpaque(false);
    b.setContentAreaFilled(false);
    b.setBorderPainted(false);
    b.addActionListener(CalcListener.getInstance());
    
    return b;
  } // createButton method.
  
  
  /**
   * createComponents - Will create the components.
   */
  private void createComponents()
  {
    close = createButton("close", "Close");
    pause = createButton("pause", "");
    play = createButton("play", "");
    rec = createButton("record", "");
    select = new JComboBox<>();
    main = new JPanel();
    side = new JPanel();
    saved = new HashMap<>();
  } // createComponents method.
  
  
  /**
   * setComponents - Will set each components attributes.
   */
  private void setComponents()
  {
    main.setLayout(new GridLayout(0, 4));
    side.setLayout(new GridLayout(2, 0));
    
    saved.put("DEMO", DEMO);
    select.addItem("DEMO");
  } // setComponents method.
  
  
  
} // PlaybackWindow class.
