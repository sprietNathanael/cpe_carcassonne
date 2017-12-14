/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.controller.AbstractCarcassonneGameController;
import carcassonne.controller.CarcassonneGameControllerSolo;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import javax.swing.JFrame;

/**
 * The Main UI class
 */
public class GUILauncher
{

    /**
     * Main methode
     */
    public static void main(String[] args) {
                
        ClientWindow clientWindow = new ClientWindow();
        
        clientWindow.setVisible(true);
        clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
}
