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
                
        plateau.setElement(new Tuile(2, 3, EnumTuile.champ, EnumTuile.route, EnumTuile.route, EnumTuile.ville));
        plateau.setElement(new Tuile(5, 5, EnumTuile.champ, EnumTuile.route, EnumTuile.route, EnumTuile.ville));
        plateau.setElement(new Tuile(5, 4, EnumTuile.champ, EnumTuile.route, EnumTuile.ville, EnumTuile.route));
        plateau.setElement(new Tuile(4, 4, EnumTuile.route, EnumTuile.champ, EnumTuile.champ, EnumTuile.route));
        plateau.setElement(new Tuile(3, 4, EnumTuile.champ, EnumTuile.route, EnumTuile.champ, EnumTuile.route));
        plateau.setElement(new Tuile(2, 4, EnumTuile.route, EnumTuile.champ, EnumTuile.champ, EnumTuile.route));
        plateau.setElement(new Tuile(3, 3, EnumTuile.route, EnumTuile.ville, EnumTuile.route, EnumTuile.ville));
        
        System.out.println(plateau);
        
        frame = new CarcassonneGUI(plateau);
        GUIController controller = new GUIController();
        controller.addObserver((Observer) frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.pack();
        frame.setVisible(true);
        while (true)
        {
            plateau.PlacementJoueur();
            System.out.println(plateau);
            controller.notifyObservers();
        }
    }
        

        
        
        
    
}
