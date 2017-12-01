/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import java.awt.Container;

/**
 * The Main game view class
 */
public class GameView
{

    /**
     * Game view constructor
     */
    public GameView()
    {
    }
    
    /**
     * Shows the view
     * @param pane Container that receives the main panel
     */
    public void show(Container pane)
    {
        //Construct the main panel and adds it to the main container
        MainPanel mainPanel = new MainPanel();
        
        pane.add(mainPanel);
    }
    
}
