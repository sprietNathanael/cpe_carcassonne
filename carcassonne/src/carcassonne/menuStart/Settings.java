/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.menuStart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author thomas
 */
public class Settings extends JFrame

{
    private JButton button = new JButton("Mode local");
    private JButton button2 = new JButton("Mode en ligne");
    private Parameters parameters;

    public Settings() throws IOException
    {
        this.setTitle("Settings");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(851, 851);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new Background("resources/Choixlocalmulti.png"));
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(button);
        this.getContentPane().add(button2);
        this.setLayout(null);
        button.setBounds(320, 250, 250, 50);
        button2.setBounds(320, 350, 250, 50);

        Settings self = this;
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                self.parameters = new Parameters(null, "Settings", true);
                //ZDialogInfo zInfo = par.showZDialog();
                //JOptionPane jop = new JOptionPane();
                //jop.showMessageDialog(null, zInfo.toString(), "Informations personnage", JOptionPane.INFORMATION_MESSAGE);
                self.parameters.setVisible(true);
                //self.parameters.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
                setVisible(false);

            }
        });

        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, "\"La patience est la clé du bien-être.\"\n" + "Mahomet - Prophète, Religieux (570 - 632)", "En cours de développement", JOptionPane.ERROR_MESSAGE);

            }
        });
        this.setVisible(true);
    }
    
    public List<ParamPlayers> getPlayers()
    {
        return(this.parameters.getDataPlayers());
    }

}
