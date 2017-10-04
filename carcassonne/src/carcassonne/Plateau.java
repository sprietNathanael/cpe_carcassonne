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
    private int tailleX;
    private int tailleY;

    public Plateau(int tailleX, int tailleY) {
        this.plateau = new Tuile[tailleX][tailleY];
        this.tailleX = 10;
        this.tailleY = 10;
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
