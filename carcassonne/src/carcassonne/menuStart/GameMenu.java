/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

/**
 *
 * @author thomas
 */
import carcassonne.view.ui_test.ClientWindow;
import carcassonne.view.ui_test.GUILauncher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameMenu extends JFrame
{

    private final BtGame play = new BtGame("resources/BtPlay.PNG");
    private final BtGame exit = new BtGame("resources/BtExit.PNG");
    private final BtGame Btsettings = new BtGame("resources/BtSettings.PNG");
    private String path = "resources/musicMenu.mp3";
    private final Sounds music = new Sounds(path);

    public GameMenu() throws IOException, Exception
    {
        this.setTitle("Carcassonne");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(1000, 850);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(new Background("resources/Back.png"));

        this.setLayout(null);

        this.add(play);
        play.setBounds(400, 300, 160, 85);

        play.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ClientWindow clientWindow = new ClientWindow();
                clientWindow.setVisible(true);
                clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try {
                    music.stop();
                } catch (Exception ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.add(Btsettings);
        Btsettings.setBounds(400, 400, 160, 85);
        Btsettings.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Settings settings = null;
                try {
                    settings = new Settings();
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                settings.setVisible(true);
                settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }

        });

        this.add(exit);
        exit.setBounds(400, 500, 160, 85);

        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        this.setVisible(true);

        music.play();

    }
}
