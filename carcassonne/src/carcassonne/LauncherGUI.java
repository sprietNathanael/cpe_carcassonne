/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author nathanael
 */
public class LauncherGUI{

    public static void main(String[] args) {

        JFrame frame;
        Plateau plateau = new Plateau(10, 10);
        frame = new CarcassonneGUI(plateau);
        GUIController controller = new GUIController();
        controller.addObserver((Observer) frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.pack();
        frame.setVisible(true);
        controller.notifyObservers();;
    }

    
}
