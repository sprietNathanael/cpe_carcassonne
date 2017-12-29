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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

/**
 *
 * @author thomas
 */
public class Settings extends JFrame

{

    private final String back = "resources/settingsMenuconfig.png";
    private final BtGame bt1 = new BtGame("resources/bt_I.PNG");
    private final BtGame bt2 = new BtGame("resources/bt_II.PNG");
    private final BtGame bt3 = new BtGame("resources/bt_III.PNG");
    private final BtGame bt4 = new BtGame("resources/bt_IV.PNG");
    private final BtGame bt5 = new BtGame("resources/bt_V.PNG");
    private final BtGame bt6 = new BtGame("resources/bt_VI.PNG");
    private int nbPlayers = 0;

    public Settings() throws IOException
    {
        this.setTitle("Settings");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(1100, 950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setContentPane(new Background(back));
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(),new Point(0,0),"nameCursor"));

        this.setLayout(null);

        this.add(bt1);
        bt1.setBounds(510, 250, 24, 50);
        bt1.setBorderPainted(false);

        bt1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bt2.setEnabled(false);
                bt3.setEnabled(false);
                bt4.setEnabled(false);
                bt5.setEnabled(false);
                bt6.setEnabled(false);
                nbPlayers = 1;
                bt1.setBorderPainted(true);
                bt1.setBorder(new BevelBorder(1, Color.black, Color.black, Color.black, Color.black));

            }
        });

        this.add(bt2);
        bt2.setBounds(550, 252, 41, 49);
        bt2.setBorderPainted(false);

        bt2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bt1.setEnabled(false);
                bt3.setEnabled(false);
                bt4.setEnabled(false);
                bt5.setEnabled(false);
                bt6.setEnabled(false);
                nbPlayers = 2;
                bt2.setBorderPainted(true);
                bt2.setBorder(new BevelBorder(1, Color.black, Color.black, Color.black, Color.black));
            }
        });

        this.add(bt3);
        bt3.setBounds(600, 252, 57, 49);
        bt3.setBorderPainted(false);

        bt3.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                bt1.setEnabled(false);
                bt2.setEnabled(false);
                bt4.setEnabled(false);
                bt5.setEnabled(false);
                bt6.setEnabled(false);
                nbPlayers = 3;
                bt3.setBorderPainted(true);
                bt3.setBorder(new BevelBorder(1, Color.black, Color.black, Color.black, Color.black));
            }
        });

        this.add(bt4);
        bt4.setBounds(670, 252, 74, 50);
        bt4.setBorderPainted(false);

        bt4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bt1.setEnabled(false);
                bt2.setEnabled(false);
                bt3.setEnabled(false);
                bt5.setEnabled(false);
                bt6.setEnabled(false);
                nbPlayers = 4;
                bt4.setBorderPainted(true);
                bt4.setBorder(new BevelBorder(1, Color.black, Color.black, Color.black, Color.black));

            }
        });

        this.add(bt5);
        bt5.setBounds(753, 252, 52, 49);
        bt5.setBorderPainted(false);

        bt5.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bt1.setEnabled(false);
                bt2.setEnabled(false);
                bt3.setEnabled(false);
                bt4.setEnabled(false);
                bt6.setEnabled(false);
                nbPlayers = 5;
                bt5.setBorderPainted(true);
                bt5.setBorder(new BevelBorder(1, Color.black, Color.black, Color.black, Color.black));

            }
        });

        this.add(bt6);
        bt6.setBounds(820, 252, 75, 48);
        bt6.setOpaque(false);
        bt6.setContentAreaFilled(false);
        bt6.setBorderPainted(false);

        bt6.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bt1.setEnabled(false);
                bt2.setEnabled(false);
                bt3.setEnabled(false);
                bt4.setEnabled(false);
                bt5.setEnabled(false);
                nbPlayers = 6;
                bt6.setBorderPainted(true);
                bt6.setBorder(new BevelBorder(1, Color.black, Color.black, Color.black, Color.black));

            }
        });

        this.setVisible(true);

    }

    public int getNbPlayers()
    {
        return nbPlayers;
    }

}
