/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

/**
 *
 * @author Thomas
 */
public class Plateau {
    private Tuile[][] plateau;

    public Plateau(Tuile[][] plateau) {
        this.plateau = plateau;
    }

    public Tuile[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Tuile[][] plateau) {
        this.plateau = plateau;
    }
    
    
    
}
