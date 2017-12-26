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
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GameMenu extends JFrame 
{
    private final BtGame play = new BtGame("resources/BtPlay.PNG");
    private final BtGame exit = new BtGame("resources/BtExit.PNG");
    private final BtGame Btsettings = new BtGame("resources/BtSettings.PNG");

    public GameMenu() throws IOException
    {
        this.setTitle("Carcassonne");
        this.setSize(1000, 850);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(new Background());

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
            }
        });

        this.add(exit);
        exit.setBounds(400, 400, 160, 85);
        
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        this.add(Btsettings);
        Btsettings.setBounds(400, 500, 160, 85);
        Btsettings.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               Settings settings = new Settings();
                settings.setVisible(true);
                settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        this.setVisible(true);

    }
}
