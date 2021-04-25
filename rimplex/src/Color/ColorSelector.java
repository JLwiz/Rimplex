package Color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ColorSelector implements ActionListener
{
  private Color primary;
  private Color secondary;
  private static JTextField pcolor1, pcolor2, pcolor3;
  private static ColorSelector listener;
  private static JButton ok;
  public static void main(String[] args)
  {
    listener = new ColorSelector();
    
    JFrame colorSelector = new JFrame();
    colorSelector.setSize(250, 250);
    colorSelector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel colorInputs = new JPanel(new BorderLayout());
    
    pcolor1 = new JTextField();
    colorInputs.add(pcolor1, BorderLayout.CENTER);
    
    ok = new JButton("OK");
    ok.addActionListener(listener);
    colorInputs.add(ok, BorderLayout.SOUTH);
    
    colorSelector.add(colorInputs);
    
    colorSelector.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e)
  {
    
    int a = Integer.parseInt(pcolor1.getText());
    primary = new Color(a, a, a);
    ((JButton) e.getSource()).setBackground(primary);
  }

}
