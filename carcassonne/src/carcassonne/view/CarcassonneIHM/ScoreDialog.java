/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import carcassonne.model.player.Player;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Bertrand
 */
public class ScoreDialog extends JDialog
{
    private Player player;

    private final JLabel roadPin = new JLabel(new ImageIcon("resources/pins/roadPin.png"));;
    private final JLabel abbayPin = new JLabel(new ImageIcon("resources/pins/abbayPin.png"));
    private final JLabel cityPin = new JLabel(new ImageIcon("resources/pins/cityPin.png"));
    private final JLabel fieldPin = new JLabel(new ImageIcon("resources/pins/fieldPin.png"));
    private final JLabel equalsPin = new JLabel(new ImageIcon("resources/pins/equalsPin.png"));
    private final JLabel equalsPin2 = new JLabel(new ImageIcon("resources/pins/equalsPin.png"));
    private final JLabel equalsPin3 = new JLabel(new ImageIcon("resources/pins/equalsPin.png"));
    private final JLabel equalsPin4 = new JLabel(new ImageIcon("resources/pins/equalsPin.png"));
    private final JLabel fin = new JLabel();
    
    public ScoreDialog(){
        initComponent();
    }
    
    private void initComponent()
    {
        //Set Title
        this.setTitle("Score détaillé");
        
        //Set Icon
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icone carcassonne.jpg")).getImage());
        
        //Dimension Window
        this.setSize(500, 750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Add custom cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));
        
        //Add Picture
        this.add(roadPin);
        roadPin.setBounds(20, 160, 100, 100);
        this.add(equalsPin);
        equalsPin.setBounds(140, 180, 60, 60);
        
        this.add(abbayPin);
        abbayPin.setBounds(20, 300, 100, 100);
        this.add(equalsPin2);
        equalsPin2.setBounds(140, 320, 60, 60);
        
        this.add(cityPin);
        cityPin.setBounds(20, 440, 100, 100);
        this.add(equalsPin3);
        equalsPin3.setBounds(140, 460, 60, 60);
        
        this.add(fieldPin);
        fieldPin.setBounds(20, 580, 100, 100);
        this.add(equalsPin4);
        equalsPin4.setBounds(140, 600, 60, 60);
        
        this.add(fin);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args){
        // Displays the game menu
        ScoreDialog scoreDialog = new ScoreDialog();
    }
}
