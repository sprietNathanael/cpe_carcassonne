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
public class Settings extends JFrame

{
    private final BtGame btModeLocal = new BtGame("resources/btModeLocal.png");
    private final BtGame btModeOnline = new BtGame("resources/btModeOnline.png");

    private Local local;
    private Online online;

    public Settings() throws IOException
    {
        initComponent();
    }

    private void initComponent() throws IOException
    {
        //Add title
        this.setTitle("Settings");
        
        //Set icon
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        
        //Set window properties
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Add a background
        this.setContentPane(new Background("resources/Choixlocalmulti.png"));
        this.setLayout(null);
        
        //Add cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));

        //Ajout des boutons
        this.add(btModeLocal);        
        btModeLocal.setBounds(270, 269, 270, 110);
        btModeLocal.setContentAreaFilled(false);
        btModeLocal.setBorderPainted(false);
        
        this.add(btModeOnline);
        btModeOnline.setBounds(270, 360, 270, 110);
        btModeOnline.setContentAreaFilled(false);
        btModeOnline.setBorderPainted(false);
        
        Settings self = this;
        
        btModeLocal.addActionListener((ActionEvent arg0) -> {
            
            self.local = new Local();
            self.local.setVisible(true);
            self.setVisible(false);
            
        });

        btModeOnline.addActionListener((ActionEvent arg0) -> {
            //JOptionPane.showMessageDialog(null, "\"La patience est la clé du bien-être.\"\n" + "Mahomet - Prophète, Religieux (570 - 632)", "En cours de développement", JOptionPane.ERROR_MESSAGE);
            self.online = new Online();
            self.online.setVisible(true);
            self.setVisible(false);
        });
        
        this.setVisible(true);
    }

}
