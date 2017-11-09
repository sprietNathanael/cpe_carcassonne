/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author nathanael
 */
public class Client extends JFrame
{

    public Client()
    {
        super("test");
        cleanContentPane();
        GameView gameView = new GameView();
        gameView.show(getContentPane());
        getContentPane().setVisible(true);
        
    }
    
    public void cleanContentPane() {
        Container pane = getContentPane();
        pane.setVisible(false);
        pane.removeAll();
    }
    
    
}
