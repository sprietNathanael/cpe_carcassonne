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

        Plateau p = new Plateau(10, 10);
        p.setElement(new Tuile(2, 2, EnumTuile.route, EnumTuile.route, EnumTuile.route, EnumTuile.route));
        System.out.println(p);

    }
    
}
