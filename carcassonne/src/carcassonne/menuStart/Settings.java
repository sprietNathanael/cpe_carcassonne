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

    public Settings()
    {
        this.setTitle("Settings");
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(button);
        this.getContentPane().add(button2);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                ZDialog zd = new ZDialog(null, "Settings", true);
                ZDialogInfo zInfo = zd.showZDialog();
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, zInfo.toString(), "Informations personnage", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        });

        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                JOptionPane jop = new JOptionPane();
                jop.showMessageDialog(null, "La patience est la clé du bien-être.\n" + "Mahomet - Prophète, Religieux (570 - 632))", "En cours de développement", JOptionPane.ERROR_MESSAGE);

            }
        });
        this.setVisible(true);
    }

}
