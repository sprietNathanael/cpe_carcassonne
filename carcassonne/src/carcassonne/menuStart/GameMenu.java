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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JFrame
{

    private final JLabel image = new JLabel(new ImageIcon("resources/Fond parchemin.png"));
    private final BtGame play = new BtGame("resources/BtPlay.PNG");
    private final BtGame exit = new BtGame("resources/BtExit.PNG");
    private final BtGame help = new BtGame("resources/BtSettings.PNG");

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

        this.add(exit);
        exit.setBounds(400, 400, 160, 85);

        this.add(help);
        help.setBounds(400, 500, 160, 85);

        this.setVisible(true);

    }
}
