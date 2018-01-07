/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.menuStart;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author thomas
 */
public class Instructions extends JFrame
{

    private final BtGame exit = new BtGame("resources/Exit.png");

    public Instructions() throws IOException
    {
        initComponent();
        
        exit.setBounds(750, 935, 57, 43);
        exit.setBorderPainted(false);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener((ActionEvent e) -> {
            setVisible(false);
        });

    }
    
    /**
     * Allows to init the differents components for the game
     */
    private void initComponent() throws IOException
    {
        this.setTitle("Instructions");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(860, 1030);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new Background("resources/instructionsMenu.png"));
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        this.setVisible(true);
        this.setLayout(null);

        this.add(exit);
    }
}
