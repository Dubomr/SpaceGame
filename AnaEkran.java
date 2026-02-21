import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JFrame;

public class AnaEkran extends JFrame {

  public AnaEkran(String title) throws HeadlessException, IOException {
    super(title);
    this.setSize(1000, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setFocusable(false);

    Oyun oyun = new Oyun();

    oyun.requestFocus();
    oyun.addKeyListener(oyun);
    oyun.setFocusable(true);
    oyun.setFocusTraversalKeysEnabled(false);

    this.add(oyun);

  }

  public static void main(String[] args) throws IOException {
    // Giriş ekranını atlamak için aşağıdaki 2 kodu açıp en alttaki 2 kodu yorum
    // yapın.
    // AnaEkran ekran = new AnaEkran("Uzay Oyun");
    // ekran.setVisible(true);

    GirisEkrani girisEkrani = new GirisEkrani();
    girisEkrani.setVisible(true);

  }

}
