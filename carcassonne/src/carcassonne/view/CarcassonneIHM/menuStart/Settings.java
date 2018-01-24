/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM.menuStart;

import java.awt.FlowLayout;
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
    private final BtGame btOnline = new BtGame("resources/online.png");

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
        this.setSize(851, 851);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Add a background
        this.setContentPane(new Background("resources/Choixlocalmulti.png"));
        
        //Add cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));
        
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(btModeLocal);
        this.getContentPane().add(btOnline);
        this.setLayout(null);
        
        //Ajout des boutons
        btModeLocal.setBounds(350, 310, 270, 110);
        btModeLocal.setOpaque(false);
        btModeLocal.setContentAreaFilled(false);
        btModeLocal.setBorderPainted(false);
        btOnline.setBounds(350, 410, 270, 110);
        btOnline.setOpaque(false);
        btOnline.setContentAreaFilled(false);
        btOnline.setBorderPainted(false);
        
        Settings self = this;
        btModeLocal.addActionListener((ActionEvent arg0) -> {
            
            self.local = new Local();
            self.local.setVisible(true);
            self.setVisible(false);
            
        });

        btOnline.addActionListener((ActionEvent arg0) -> {
            //JOptionPane.showMessageDialog(null, "\"La patience est la clé du bien-être.\"\n" + "Mahomet - Prophète, Religieux (570 - 632)", "En cours de développement", JOptionPane.ERROR_MESSAGE);
            self.online = new Online();
            self.online.setVisible(true);
            self.setVisible(false);
        });
        
        this.setVisible(true);
    }

}
