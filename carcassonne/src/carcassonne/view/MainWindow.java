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
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame
{

    private final int height = 900;
    private final int width = 600;
    JLabel label = new JLabel();

    public MainWindow()
    {

        this.setTitle("Carcassonne");
        this.setSize(height, width);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(new Pannel());

        this.setLayout(new BorderLayout());
        //On ajoute le bouton au content pane de la JFrame
        //Au centre
        //this.getContentPane().add(new ButtonLaunch());
       
        //this.getContentPane().add(label);
          
        this.setVisible(true);

    }
}
