/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.CarcassonneIHM;

import carcassonne.view.CarcassonneIHM.menuStart.GameMenu;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * The Main UI class
 */
public class GUILauncher
{
    /**
     * Main methode
     * @param args Strings[]
     */
    public static void main(String[] args) throws IOException, Exception {
                
        // Displays the game menu
        GameMenu gameMenu = new GameMenu();
        
        gameMenu.setVisible(true);
        gameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
