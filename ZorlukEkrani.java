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
      backgroundImage = ImageIO.read(new File(fileName)); // Resmi yükleme
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Resim yüklenemedi! Dosya yolunu kontrol edin.");
    }
    setLayout(null); // Bileşenleri manuel konumlandırabilmek için layout'u null yaptık
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
      g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
  }
}

public class ZorlukEkrani extends JFrame {
  public ZorlukEkrani() {
    setTitle("Zorluk Ekranı");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Arka plan paneli oluştur ve JFrame'e ekle
    BackgroundPanel panel = new BackgroundPanel("uzaybck.jpg");
    setContentPane(panel);

    // Butonları ekleme
    JButton btnKolay = new JButton("KOLAY");
    JButton btnOrta = new JButton("ORTA");
    JButton btnZor = new JButton("ZOR");
    JButton btnOzel = new JButton("ÖZEL");

    // Butonların konumlarını ayarla (çünkü layout null)
    btnKolay.setBounds(50, 50, 120, 50);
    btnOrta.setBounds(200, 50, 120, 50);
    btnZor.setBounds(350, 50, 120, 50);
    btnOzel.setBounds(500, 50, 120, 50);

    // Butonları arka plan paneline ekle
    panel.add(btnKolay);
    panel.add(btnOrta);
    panel.add(btnZor);
    panel.add(btnOzel);

    setVisible(true);

    btnKolay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        AnaEkran anaEkran = null;
        try {
          anaEkran = new AnaEkran("Uzay Oyunu");
        } catch (HeadlessException ex) {
          Logger.getLogger(ZorlukEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(ZorlukEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }

        anaEkran.setVisible(true);
        ZorlukEkrani.this.setVisible(false);

      }
    });

  }

  public static void main(String[] args) {
    new ZorlukEkrani();

  }
}
