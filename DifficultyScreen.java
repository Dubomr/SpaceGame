import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class BackgroundPanel extends JPanel {
  private Image backgroundImage;

  public BackgroundPanel(String fileName) {
    try {
      backgroundImage = ImageIO.read(new File(fileName)); // Upload image
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("The image could not be uploaded! Check the file path.");
    }
    setLayout(null); // We set the layout to null so that we can manually position the components.
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
      g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
  }
}

public class DifficultyScreen extends JFrame {
  public DifficultyScreen() {
    setTitle("Difficulty Screen");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Create a background panel and add it to a JFrame.
    BackgroundPanel panel = new BackgroundPanel("spacebck.jpg");
    setContentPane(panel);

    // Add buttons
    JButton btnEasy = new JButton("EASY");
    JButton btnMedium = new JButton("MEDIUM");
    JButton btnHard = new JButton("HARD");
    JButton btnSpecial = new JButton("SPECIAL");

    // Adjust the button positions (because the layout is null)
    btnEasy.setBounds(50, 50, 120, 50);
    btnMedium.setBounds(200, 50, 120, 50);
    btnHard.setBounds(350, 50, 120, 50);
    btnSpecial.setBounds(500, 50, 120, 50);

    // Add buttons to the background panel
    panel.add(btnEasy);
    panel.add(btnMedium);
    panel.add(btnHard);
    panel.add(btnSpecial);

    setVisible(true);

    btnEasy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StartGame startGame = null;
        try {
          startGame = new StartGame("Space Game");
        } catch (HeadlessException ex) {
          Logger.getLogger(DifficultyScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(DifficultyScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        startGame.setVisible(true);
        DifficultyScreen.this.setVisible(false);

      }
    });

  }

  public static void main(String[] args) {
    new DifficultyScreen();

  }
}
