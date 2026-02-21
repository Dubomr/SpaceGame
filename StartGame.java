import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JFrame;

public class StartGame extends JFrame {

  public StartGame(String title) throws HeadlessException, IOException {
    super(title);
    this.setSize(1000, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setFocusable(false);

    Game game = new Game();

    game.requestFocus();
    game.addKeyListener(game);
    game.setFocusable(true);
    game.setFocusTraversalKeysEnabled(false);

    this.add(game);

  }

  public static void main(String[] args) throws IOException {
    // To skip the login screen, open the 2 codes below and comment out the 2 codes
    // at the very bottom.
    // StartGame screen = new StartGame("Space Game");
    // screen.setVisible(true);

    LoginScreen loginScreen = new LoginScreen();
    loginScreen.setVisible(true);

  }

}
