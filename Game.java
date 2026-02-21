import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Shoot {
  private int x;
  private int y;

  public Shoot(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}

class Star {
  private int x;
  private int y;

  public Star(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

}

public class Game extends JPanel implements KeyListener, ActionListener {
  private ArrayList<Shoot> shoots = new ArrayList<Shoot>();
  private ArrayList<Star> stars = new ArrayList<Star>();

  Timer timer = new Timer(3, this);

  private boolean leftPressed = false; // Is the left button pressed?
  private boolean rightPressed = false; // Is the right button pressed?
  private boolean controlPressed = false; // Is the Control key pressed?

  private Image bck;

  private Random random = new Random();

  private long start_time;
  private int spent_shoot = 0;

  private BufferedImage starimage;
  private BufferedImage SpaceShipimage;
  private BufferedImage stoneimage;

  private int spaceShipX = 0;
  private int dirspaceShipX = 3;

  private int dirShootY = 3;

  private int dirStarY = 3;

  private int topX = 0;
  private int dirTopX = 2;

  private long lastStarTime = 0;
  private final int STAR_DELAY = 500;

  public boolean check() {
    for (Shoot shoot : shoots) {
      if (new Rectangle(shoot.getX(), shoot.getY(), 10, 20).intersects(new Rectangle(topX, 0, 45, 45))) {
        return true;
      }
    }
    return false;
  }

  public boolean checkStar() {
    for (Star star : stars) {
      if (new Rectangle(star.getX(), star.getY(), starimage.getWidth() / 14, starimage.getHeight() - 285)
          .intersects(
              new Rectangle(spaceShipX, 647, SpaceShipimage.getWidth() / 19, SpaceShipimage.getHeight() / 15))) {
        return true;
      }
    }
    return false;
  }

  public Game() throws IOException {

    ImageIcon obj = new ImageIcon("spacebck.jpg");
    bck = obj.getImage();

    SpaceShipimage = ImageIO.read(new FileImageInputStream(new File("Spaceship.png")));
    stoneimage = ImageIO.read(new FileImageInputStream(new File("stone.png")));
    starimage = ImageIO.read(new FileImageInputStream(new File("star.png")));

    start_time = System.currentTimeMillis();
    timer.start();

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(bck, 0, 0, this);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    long current_time = System.currentTimeMillis();
    double time_spent = (current_time - start_time) / 1000.0;

    g.drawImage(SpaceShipimage, spaceShipX, 665, SpaceShipimage.getWidth() / 12, SpaceShipimage.getHeight() / 12,
        this);
    g.setColor(Color.red);

    g.drawImage(stoneimage, topX, 0, 50, 50, this);

    g.setColor(Color.BLUE);
    Iterator<Shoot> iterator = shoots.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getY() < 0) {
        iterator.remove();
      }
    }
    for (Shoot shoot : shoots) {
      g.fillRect(shoot.getX(), shoot.getY(), 10, 20);
    }

    for (int i = 0; i < stars.size(); i++) {
      Star star = stars.get(i);
      if (star.getY() > getHeight()) {
        stars.remove(i);
        i--;
      }
    }

    if (leftPressed) {
      if (spaceShipX <= 0) {
        spaceShipX = 0;
      } else {
        spaceShipX -= dirspaceShipX;
      }
    }

    if (rightPressed) {
      if (spaceShipX >= 930) {
        spaceShipX = 930;
      } else {
        spaceShipX += dirspaceShipX;
      }
    }

    for (Star star : stars) {
      g.drawImage(starimage, star.getX(), star.getY(), starimage.getWidth() / 12, starimage.getHeight() / 12,
          this);

    }

    if (checkStar()) {
      timer.stop();
      String message = "You Lost.\n" + "Spent Shoot: " + spent_shoot + "\n" + "Time Spent: " + time_spent;
      JOptionPane.showMessageDialog(this, message);
      System.exit(0);
    }

    if (check()) {
      timer.stop();
      String message = "You Win.\n" + "Spent Shoot: " + spent_shoot + "\n" + "Time Spent: " + time_spent;
      JOptionPane.showMessageDialog(this, message);
      System.exit(0);
    }

  }

  @Override
  public void repaint() {
    super.repaint();
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();

    if (keyCode == KeyEvent.VK_LEFT) {
      leftPressed = true; // When left button is pressed
    }
    if (keyCode == KeyEvent.VK_RIGHT) {
      rightPressed = true; // When right button is pressed
    }
    if (keyCode == KeyEvent.VK_CONTROL) {
      Shoot shoot = new Shoot(spaceShipX + 24, 647);
      shoots.add(shoot);
      spent_shoot++;

    }

    if (keyCode == KeyEvent.VK_SPACE) {
      Shoot shoot = new Shoot(spaceShipX + 24, 647);
      shoots.add(shoot);
      spent_shoot++;

    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();

    if (keyCode == KeyEvent.VK_LEFT) {
      leftPressed = false; // When left button is pressed
    }
    if (keyCode == KeyEvent.VK_RIGHT) {
      rightPressed = false; // When right button is pressed
    }
    if (keyCode == KeyEvent.VK_CONTROL) {
      controlPressed = false; // When control button is pressed

    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    topX += dirTopX;
    if (topX >= 937) {
      dirTopX = -dirTopX;
    }
    if (topX <= -2) {
      dirTopX = -dirTopX;
    }

    for (Shoot shoot : shoots) {
      shoot.setY(shoot.getY() - dirShootY);
    }

    for (Star star : stars) {
      star.setY(star.getY() + dirStarY);

    }

    long currentTime = System.currentTimeMillis();
    if (currentTime - lastStarTime > STAR_DELAY) {
      int xPos = random.nextInt(900);
      stars.add(new Star(xPos, 0));
      lastStarTime = currentTime;
    }

    repaint();
  }

}
