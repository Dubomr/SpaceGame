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

class Ates {
  private int x;
  private int y;

  public Ates(int x, int y) {
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

class Yildiz {
  private int x;
  private int y;

  public Yildiz(int x, int y) {
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

public class Oyun extends JPanel implements KeyListener, ActionListener {
  private ArrayList<Ates> atesler = new ArrayList<Ates>();
  private ArrayList<Yildiz> yildizlar = new ArrayList<Yildiz>();

  Timer timer = new Timer(3, this);

  private boolean leftPressed = false; // Sol tuşu basılı mı?
  private boolean rightPressed = false; // Sağ tuşu basılı mı?
  private boolean controlPressed = false; // Control tuşu basılı mı?

  private Image bck;

  private Random random = new Random();

  private int kalan_hak;
  private long start_time;
  private int harcanan_ates = 0;

  private BufferedImage yildizimage;
  private BufferedImage UzayGemisiimage;
  private BufferedImage tasimage;

  private int uzayGemisiX = 0;
  private int diruzayGemisiX = 3;

  private int dirAtesY = 3;

  private int yildizX;
  private int yildizY = 0;
  private int dirYildizY = 3;

  private int topX = 0;
  private int dirTopX = 2;

  private long lastStarTime = 0;
  private final int STAR_DELAY = 500;

  public boolean kontrolEt() {
    for (Ates ates : atesler) {
      if (new Rectangle(ates.getX(), ates.getY(), 10, 20).intersects(new Rectangle(topX, 0, 45, 45))) {
        return true;
      }
    }
    return false;
  }

  public boolean kontorlEtYildiz() {
    for (Yildiz yildiz : yildizlar) {
      if (new Rectangle(yildiz.getX(), yildiz.getY(), yildizimage.getWidth() / 14, yildizimage.getHeight() - 285)
          .intersects(
              new Rectangle(uzayGemisiX, 647, UzayGemisiimage.getWidth() / 19, UzayGemisiimage.getHeight() / 15))) {
        return true;
      }
    }
    return false;
  }

  public Oyun() throws IOException {

    ImageIcon obj = new ImageIcon("uzaybck.jpg");
    bck = obj.getImage();

    UzayGemisiimage = ImageIO.read(new FileImageInputStream(new File("UzayGemisi.png")));
    tasimage = ImageIO.read(new FileImageInputStream(new File("tas.png")));
    yildizimage = ImageIO.read(new FileImageInputStream(new File("yildiz.png")));

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
    double gecen_sure = (current_time - start_time) / 1000.0;

    g.drawImage(UzayGemisiimage, uzayGemisiX, 665, UzayGemisiimage.getWidth() / 12, UzayGemisiimage.getHeight() / 12,
        this);
    g.setColor(Color.red);

    g.drawImage(tasimage, topX, 0, 50, 50, this);

    g.setColor(Color.BLUE);
    Iterator<Ates> iterator = atesler.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getY() < 0) {
        iterator.remove();
      }
    }
    for (Ates ates : atesler) {
      g.fillRect(ates.getX(), ates.getY(), 10, 20); // Önce ateş tuşunu yaptık , sonra actionperf. da y kord.
                                                    // değiştirdik sonra repaint çağırıldı o da painti çağırdı ve en son
                                                    // paint'de ateşi çizdik.
    }

    for (int i = 0; i < yildizlar.size(); i++) {
      Yildiz yildiz = yildizlar.get(i);
      if (yildiz.getY() > getHeight()) {
        yildizlar.remove(i);
        i--;
      }
    }

    if (leftPressed) {
      if (uzayGemisiX <= 0) {
        uzayGemisiX = 0;
      } else {
        uzayGemisiX -= diruzayGemisiX;
      }
    }

    if (rightPressed) {
      if (uzayGemisiX >= 930) {
        uzayGemisiX = 930;
      } else {
        uzayGemisiX += diruzayGemisiX;
      }
    }

    for (Yildiz yildiz : yildizlar) {
      g.drawImage(yildizimage, yildiz.getX(), yildiz.getY(), yildizimage.getWidth() / 12, yildizimage.getHeight() / 12,
          this);

    }

    if (kontorlEtYildiz()) {
      timer.stop();
      String message = "Kaybettiniz.\n" + "Harcanan Ateş: " + harcanan_ates + "\n" + "Geçen Süre: " + gecen_sure;
      JOptionPane.showMessageDialog(this, message);
      System.exit(0);
    }

    if (kontrolEt()) {
      timer.stop();
      String message = "Kazandınız.\n" + "Harcanan Ateş: " + harcanan_ates + "\n" + "Geçen Süre: " + gecen_sure;
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
      leftPressed = true; // Sol tuşuna basıldığında
    }
    if (keyCode == KeyEvent.VK_RIGHT) {
      rightPressed = true; // Sağ tuşuna basıldığında
    }
    if (keyCode == KeyEvent.VK_CONTROL) {
      Ates ates = new Ates(uzayGemisiX + 24, 647);
      atesler.add(ates);
      harcanan_ates++;

    }

    if (keyCode == KeyEvent.VK_SPACE) {
      Ates ates = new Ates(uzayGemisiX + 24, 647);
      atesler.add(ates);
      harcanan_ates++;

    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();

    if (keyCode == KeyEvent.VK_LEFT) {
      leftPressed = false; // Sol tuşu bırakıldığında
    }
    if (keyCode == KeyEvent.VK_RIGHT) {
      rightPressed = false; // Sağ tuşu bırakıldığında
    }
    if (keyCode == KeyEvent.VK_CONTROL) {
      controlPressed = false; // Control tuşu bırakıldığında

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

    for (Ates ates : atesler) {
      ates.setY(ates.getY() - dirAtesY);
    }

    for (Yildiz yildiz : yildizlar) {
      yildiz.setY(yildiz.getY() + dirYildizY);

    }

    long currentTime = System.currentTimeMillis();
    if (currentTime - lastStarTime > STAR_DELAY) {
      int xPos = random.nextInt(900);
      yildizlar.add(new Yildiz(xPos, 0));
      lastStarTime = currentTime;
    }

    repaint();
  }

}
