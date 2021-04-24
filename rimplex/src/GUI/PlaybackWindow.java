package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
  private JButton close, pause, play, rec;
  private JComboBox<String> select;
  private JPanel main, side;
  
  /**
   * Constructor.
   */
  private PlaybackWindow()
  {
    super(CalculatorDisplay.getInstance());
    
    setSize(200, 100);
    createComponents();
    setComponents();
    addComponents();
    getContentPane().add(main);
    setFocusable(true);
    
    setVisible(true);
  } // constructor.
  
  // ----------Public Methods----------
  
  
  
  
  
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
    JButton b = new JButton(title);
    ImageIcon icon;
    
    b.setName(name);
    b.setBackground(Color.BLACK);
    b.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    b.setForeground(Color.WHITE);
    b.setFont(new Font("Arial", Font.BOLD, 20));
    b.addActionListener(CalcListener.getInstance());
    
    switch (name)
    {
      case "pause":
        icon = new ImageIcon(PlaybackWindow.class
            .getResource("/images/pause.jpg"));
        b.setIcon(icon);
        break;
      case "play":
        icon = new ImageIcon(PlaybackWindow.class
            .getResource("/images/play.jpg"));
        b.setIcon(icon);
        break;
      case "record":
        icon = new ImageIcon(PlaybackWindow.class
            .getResource("/images/pre-recording.jpg"));
        b.setIcon(icon);
        break;
      default:
        break;
    }
    
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
    main.setLayout(new GridLayout(4, 0));
    side.setLayout(new GridLayout(0, 1));
  } // setComponents method.
  
  
  
} // PlaybackWindow class.
