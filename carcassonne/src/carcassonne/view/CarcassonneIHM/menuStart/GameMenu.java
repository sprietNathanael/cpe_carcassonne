/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.menuStart;

/**
 *
 * @author thomas & bertrand
 */
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameMenu extends JFrame
{
    private final BtGame btInstructions = new BtGame("resources/btInstructions.png");
    private final BtGame btPlay = new BtGame("resources/btPlay.png");
    private final BtGame btExit = new BtGame("resources/btExit.png");
    private final String musicMenu = "resources/music/musicMenu.mp3";
    private Settings settings;

    public GameMenu() throws IOException, Exception
    {
        JFXPanel jfxPanel = new javafx.embed.swing.JFXPanel();
        initComponent();   
    }
    
    private void initComponent() throws IOException
    {
        //Add title
        this.setTitle("Carcassonne");
        
        //Set Icon
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        
        //Set window properties
        this.setSize(1000, 850);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        //Add a background to our window
        this.setContentPane(new Background("resources/Back.png"));        
        this.setLayout(null);
        
        //Add a custom cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));
        
        //Create and play the music
        String uriString = new File(musicMenu).toURI().toString();
        MediaPlayer menuMediaPlayer = new MediaPlayer(new Media(uriString));
        menuMediaPlayer.play();
        
        //Add buttons
        this.add(btInstructions);
        btInstructions.setBounds(830, 660, 135, 155);
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
            }
        });
 
        GameMenu self = this;
        
        this.add(btPlay);
        btPlay.setContentAreaFilled(false);
        btPlay.setBorderPainted(false);
        btPlay.setBounds(400, 330, 207, 92);
        
        btPlay.addActionListener(new ActionListener()
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
                self.setVisible(false);
            }
        });

        this.add(btExit);
        btExit.setBounds(395, 460, 207, 92);
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
    }

}
