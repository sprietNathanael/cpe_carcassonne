/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Container;

/**
 *
 * @author nathanael
 */
public class GameView
{

    public GameView()
    {
    }
    
    public void show(Container pane)
    {
        MainPanel mainPanel = new MainPanel();
        
        pane.add(mainPanel);
    }
    
}
