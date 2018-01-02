/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

/**
 *
 * @author thomas
 */
public class Instructions extends JFrame
{

    private final BtGame exit = new BtGame("resources/Exit.png");

    public Instructions() throws IOException
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
        exit.setBounds(750, 910, 57, 43);
        exit.setBorderPainted(false);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                setVisible(false);
            }
        });

    }
}
