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

    public int getTailleX() {
        return tailleX;
    }

    public void setTailleX(int tailleX) {
        this.tailleX = tailleX;
    }

    public int getTailleY() {
        return tailleY;
    }

    public void setTailleY(int tailleY) {
        this.tailleY = tailleY;
    }
    
    

    @Override
    public String toString() {
        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                AffichageTuile(plateau[i][j]);
            }
        }
        return "";
    }
     
    public String AffichageTuile(Tuile t){
        return "";
    }

}
