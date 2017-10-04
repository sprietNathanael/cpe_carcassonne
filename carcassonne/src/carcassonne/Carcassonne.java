/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

/**
 *
 * @author nathanael
 */
public class Carcassonne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tuile tuile = new Tuile(0, 0, EnumTuile.champ, EnumTuile.route, EnumTuile.route, EnumTuile.champ);
        
        System.out.println(tuile);
    }
    
}
