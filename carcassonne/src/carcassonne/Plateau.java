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
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        
        for (int i=0 ; i<tailleX ; i++){
            for (int j=0 ; j<tailleY ; j++)
            {
                plateau[i][j] = new Tuile(0, 0, null, null, null, null);
            }
                
        }
            
    }

    public Tuile getElement(int x, int y)
    {
        return plateau[x][y];
    }
    
    public void setElement(Tuile t){
        plateau[t.getPosX()][t.getPosY()] = t;
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
        String ret = "";
        for (int i = 0; i < tailleX; i++) {
            for (int j = 0; j < tailleY; j++) {
                ret += "[" + plateau[i][j] + "] ";
            }
            ret += "\n";
        }
        return ret;
    }

}
