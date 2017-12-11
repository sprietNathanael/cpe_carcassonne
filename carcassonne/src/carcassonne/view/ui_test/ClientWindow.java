/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * The Main client window class
 */
public class ClientWindow extends JFrame
{

    /**
     * Window constructor
     */
    public ClientWindow()
    {
        super("Carcassonne");
        cleanContentPane();
        GameView gameView = new GameView();
        gameView.show(getContentPane());
        
        // Maximize the window
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        getContentPane().setVisible(true);
        
    }
    
    /**
     * Clean the window
     */
    public void cleanContentPane() {
        Container pane = getContentPane();
        pane.setVisible(false);
        pane.removeAll();
    }
    
    
}
