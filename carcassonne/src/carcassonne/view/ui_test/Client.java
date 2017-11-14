/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
        //setLocation("L".equals(windowSize) ? 0 : dw/2, 0);*/
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        getContentPane().setVisible(true);
        
    }
    
    public void cleanContentPane() {
        Container pane = getContentPane();
        pane.setVisible(false);
        pane.removeAll();
    }
    
    
}
