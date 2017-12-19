/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

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
    public static void main(String[] args) {
                
        ClientWindow clientWindow = new ClientWindow();
        
        clientWindow.setVisible(true);
        clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
