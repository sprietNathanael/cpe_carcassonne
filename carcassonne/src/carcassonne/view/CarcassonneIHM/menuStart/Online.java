/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.menuStart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author thomas
 */
public class Online extends JDialog
{

    private JLabel icon;
    private JRadioButton serveur, client;

    public Online(JFrame parent, String title, boolean modal)
    {
        super(parent, title, modal);
        this.initComponent();
    }

    private void initComponent()
    {
        //Set Icon
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());

        //Dimension Window
        this.setSize(950, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //Add custom cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        //Image de gauche
        icon = new JLabel(new ImageIcon("resources/logo online.jpg"));
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.white);
        panIcon.setLayout(new BorderLayout());
        panIcon.add(icon);

        //Button back
        JPanel control = new JPanel();
        JButton okBouton = new JButton("BACK");

        okBouton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
            }
        });

        control.add(okBouton);
        control.setBackground(Color.white);

        //Panel settings
        JPanel panSettings = new JPanel();
        panSettings.setBackground(Color.white);
        panSettings.setBorder(BorderFactory.createTitledBorder("Mode online"));
        panSettings.setPreferredSize(new Dimension(440, 60));
        /* choix client/serveur */
        client = new JRadioButton("Client");
        serveur = new JRadioButton("Serveur");
        //client.setSelected(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(client);
        bg.add(serveur);
        panSettings.add(client);
        panSettings.add(serveur);

        if (client.isSelected() == true) {
            /*  choix du port */
            JTextField port = new JTextField("Port to join a game ?");
            port.setPreferredSize(new Dimension(130, 30));
            panSettings.add(port);

        }
        else {
            /*  choix du port */
            JTextField port = new JTextField("Port for the game ?");
            port.setPreferredSize(new Dimension(130, 30));
            panSettings.add(port);

        }

        this.getContentPane().add(panSettings, BorderLayout.CENTER);
        this.getContentPane().add(panIcon, BorderLayout.WEST);
        this.getContentPane().add(control, BorderLayout.SOUTH);
    }
}
