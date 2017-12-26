/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view;

/**
 *
 * @author thomas
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame
{

    private final int height = 1100;
    private final int width = 650;
    private final JPanel background = new Pannel();
    private final JPanel parchment = new Parchment();
    private final JButton play = new ButtonLaunch();

    public MainWindow()
    {
        JFrame window = new JFrame("Carcasonne");

        //window.setLocationRelativeTo(null);
        window.setLayout(null);

        parchment.setPreferredSize(new Dimension(500, 550));
        play.setPreferredSize(new Dimension(180, 50));

        //Définition de l'action du bouton
        play.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("SALUT MA POULE");
            }
        });

        background.add(play, BorderLayout.CENTER);
        background.add(parchment, BorderLayout.CENTER);

        window.setContentPane(background);

        // quitter le programme lorsqu'on ferme la fenêtre
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // dimensionnement en affichage de la fenêtr
        window.setSize(height, width);
        window.setVisible(true);

     

    }
}
