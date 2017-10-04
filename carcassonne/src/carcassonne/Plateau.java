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

    public Plateau() {
        this.plateau = new Tuile[10][10];        
    }

    public Tuile[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Tuile[][] plateau) {
        this.plateau = plateau;
    }
    
    public void AfficherConsole()
    {
        
    }
    
    
}
