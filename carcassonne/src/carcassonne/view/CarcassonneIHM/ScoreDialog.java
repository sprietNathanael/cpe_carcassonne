/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import carcassonne.model.player.Player;
import carcassonne.view.CarcassonneIHM.menuStart.Background;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
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
    
    private JLabel playerName;

    private final JLabel roadPin = new JLabel(new ImageIcon("resources/pins/roadPin.png"));;
    private final JLabel abbayPin = new JLabel(new ImageIcon("resources/pins/abbayPin.png"));
    private final JLabel cityPin = new JLabel(new ImageIcon("resources/pins/cityPin.png"));
    private final JLabel fieldPin = new JLabel(new ImageIcon("resources/pins/fieldPin.png"));

    private JLabel roadScore;
    private JLabel abbayScore;
    private JLabel cityScore;
    private JLabel fieldScore;
    private final JLabel fin = new JLabel();
    
    public ScoreDialog() throws IOException{
        initComponent();
    }
    
    private void initComponent() throws IOException
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
        
        //Add Background
        this.setContentPane(new Background("resources/score_background.png"));
        this.setLayout(null);
        
        // For Test before import
        player = new Player("Nathanaël", "bleu", "player");
        
        //Add Pictures and Labels
        playerName = new JLabel (player.getName());
        playerName.setFont(new Font("Serif", Font.PLAIN, 40));
        playerName.setBounds(0, 0, 200, 100);
        playerName.setLocation((this.getWidth()-playerName.getWidth())/2, 20);
        this.add(playerName);
        
        roadPin.setBounds(150, 160, 100, 100);
        this.add(roadPin);
        
        roadScore = new JLabel (Integer.toString(player.getRoadPoints()));
        roadScore.setBounds(300, 180, 120, 60);
        roadScore.setFont(new Font("Serif", Font.PLAIN, 60));
        this.add(roadScore);        
        
        abbayPin.setBounds(150, 300, 100, 100);
        this.add(abbayPin);
        
        abbayScore = new JLabel (Integer.toString(player.getAbbayePoints()));
        abbayScore.setBounds(300, 320, 120, 60);
        abbayScore.setFont(new Font("Serif", Font.PLAIN, 60));
        this.add(abbayScore);
        
        cityPin.setBounds(150, 440, 100, 100);
        this.add(cityPin);
        
        cityScore = new JLabel (Integer.toString(player.getCityPoints()));
        cityScore.setBounds(300, 460, 120, 60);
        cityScore.setFont(new Font("Serif", Font.PLAIN, 60));
        this.add(cityScore);
        
        fieldPin.setBounds(150, 580, 100, 100);
        this.add(fieldPin);
        
        fieldScore = new JLabel (Integer.toString(player.getFieldPoints()));
        fieldScore.setBounds(300, 600, 120, 60);
        fieldScore.setFont(new Font("Serif", Font.PLAIN, 60));
        this.add(fieldScore);
        
        this.add(fin);
        
        this.setVisible(true);
    }
    
    public static void main(String[] args) throws IOException{
        // Displays the game menu
        new ScoreDialog();
    }
}
