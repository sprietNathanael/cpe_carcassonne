/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

/**
 *
 * @author thomas & bertrand
 */
import carcassonne.view.ui_test.ClientWindow;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javazoom.jl.player.Player;

public class GameMenu extends JFrame
{

    private final BtGame btPlay = new BtGame("resources/btPlay.png");
    private final BtGame btSettings = new BtGame("resources/btSettings.png");
    private final BtGame btInstructions = new BtGame("resources/btInstructions.png");
    private final BtGame btExit = new BtGame("resources/btExit.png");
    private final Player musicPlayer = new Player(new BufferedInputStream(new FileInputStream("resources/music/musicMenu.mp3")));
    private Settings settings;

    public GameMenu() throws IOException, Exception
    {
        this.setTitle("Carcassonne");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(1000, 850);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(new Background("resources/Back.png"));
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(),new Point(0,0),"nameCursor"));

        this.setLayout(null);
        
        GameMenu self = this;

        this.add(btInstructions);
        btInstructions.setBounds(830, 660, 135, 155);
        btInstructions.setOpaque(false);
        btInstructions.setContentAreaFilled(false);
        btInstructions.setBorderPainted(false);

        btInstructions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Instructions instructions = null;
                try {
                    instructions = new Instructions();
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                instructions.setVisible(true);
                instructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            }
        });
        
        this.add(btPlay);
        btPlay.setOpaque(false);
        btPlay.setContentAreaFilled(false);
        btPlay.setBorderPainted(false);
        btPlay.setBounds(400, 300, 207, 92);
        //btPlay.setBorderPainted(false);
        btPlay.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                musicPlayer.close();
                ClientWindow clientWindow;
                if(self.settings != null)
                {
                    clientWindow = new ClientWindow(self.settings.getPlayers());
                }
                else
                {
                    clientWindow = new ClientWindow();
                }
                clientWindow.setVisible(true);
                clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
            }
        });

        this.add(btSettings);
        btSettings.setBounds(400, 400, 207, 92);
        btSettings.setOpaque(false);
        btSettings.setContentAreaFilled(false);
        btSettings.setBorderPainted(false);
        
        btSettings.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    self.settings = new Settings();
                } catch (IOException ex) {
                    Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                self.settings.setVisible(true);
                self.settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }

        });

        this.add(btExit);
        btExit.setBounds(400, 500, 207, 92);
        btExit.setOpaque(false);
        btExit.setContentAreaFilled(false);
        btExit.setBorderPainted(false);
        btExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        this.setVisible(true);

        musicPlayer.play();
    }
}
