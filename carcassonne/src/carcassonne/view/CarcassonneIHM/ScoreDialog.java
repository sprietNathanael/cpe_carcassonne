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
    
    private JLabel roadPin;
    private JLabel abbayPin;
    private JLabel cityPin;
    
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
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //Add custom cursor
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setCursor(tk.createCustomCursor(new ImageIcon(getClass().getResource("/images/curseur.png")).getImage(), new Point(0, 0), "nameCursor"));
        
        //Add Picture
        roadPin = new JLabel(new ImageIcon("resources/pins/roadPin.png"));
        roadPin.setBounds(20, 20, 120, 120);
        this.add(roadPin);
        abbayPin = new JLabel(new ImageIcon("resources/pins/abbayPin.png"));
        abbayPin.setBounds(20, 160, 120, 120);
        this.add(abbayPin);
        cityPin = new JLabel(new ImageIcon("resources/pins/cityPin.png"));
        cityPin.setBounds(20, 300, 120, 120);
        this.add(cityPin);
        
    }
    
    public static void main(String[] args){
        // Displays the game menu
        ScoreDialog scoreDialog = new ScoreDialog();
        
        scoreDialog.setVisible(true);
    }
}
