/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

import java.util.Scanner;

/**
 *
 * @author nathanael
 */
public class Carcassonne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Plateau p = new Plateau(10, 10);
        p.setElement(new Tuile(2, 2, EnumTuile.route, EnumTuile.route, EnumTuile.route, EnumTuile.route));
        Tuile t = new Tuile(4,2, EnumTuile.route, EnumTuile.route, EnumTuile.route, EnumTuile.route);
        
        if(p.verifPos(t)==true)
        {
         p.setElement(t);
        }
        
        p.setElement(new Tuile(2, 3, EnumTuile.champ, EnumTuile.route, EnumTuile.route, EnumTuile.ville));
        p.setElement(new Tuile(5, 5, EnumTuile.champ, EnumTuile.route, EnumTuile.route, EnumTuile.ville));
        p.setElement(new Tuile(5, 4, EnumTuile.champ, EnumTuile.route, EnumTuile.ville, EnumTuile.route));
        p.setElement(new Tuile(4, 4, EnumTuile.route, EnumTuile.champ, EnumTuile.champ, EnumTuile.route));
        p.setElement(new Tuile(3, 4, EnumTuile.champ, EnumTuile.route, EnumTuile.champ, EnumTuile.route));
        p.setElement(new Tuile(2, 4, EnumTuile.route, EnumTuile.champ, EnumTuile.champ, EnumTuile.route));
        p.setElement(new Tuile(3, 3, EnumTuile.route, EnumTuile.ville, EnumTuile.route, EnumTuile.ville));
        System.out.println(p);

        
        
        while (true)
        {
            p.PlacementJoueur();
            System.out.println(p);
        }
        
        
        
        
        

    }
    
}
