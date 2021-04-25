package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
  private static PlaybackWindow single_instance = null;
  private boolean recording;
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
    createComponents();
    setComponents();
    addComponents();
    getContentPane().add(main);
    setFocusable(true);
    
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
   * toggleIcon - Will change the recording icon.
   */
  public void toggleIcon()
  {
    ImageIcon icon;
    Image recimg;
    if (recording)
    {
      icon = new ImageIcon(PlaybackWindow.class
          .getResource("/images/recording.jpg"));
      recimg = icon.getImage();
      recimg = recimg.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
      icon = new ImageIcon(recimg);
    }
    else
    {
      icon = new ImageIcon(PlaybackWindow.class
          .getResource("/images/pre-recording.jpg"));
      recimg = icon.getImage();
      recimg = recimg.getScaledInstance(75, 50, java.awt.Image.SCALE_SMOOTH);
    }
    icon = new ImageIcon(recimg);
    rec.setIcon(icon);
    recording = !recording; 
  } // toggleIcon method.
  
  
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
        icon = new ImageIcon(PlaybackWindow.class
            .getResource("/images/pause.jpg"));
        Image pauseimg = icon.getImage();
        pauseimg = pauseimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(pauseimg);
        b = new JButton(icon);
        break;
      case "play":
        icon = new ImageIcon(PlaybackWindow.class
            .getResource("/images/play.jpg"));
        Image playimg = icon.getImage();
        playimg = playimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(playimg);
        b = new JButton(icon);
        break;
      case "record":
        icon = new ImageIcon(PlaybackWindow.class
            .getResource("/images/pre-recording.jpg"));
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
