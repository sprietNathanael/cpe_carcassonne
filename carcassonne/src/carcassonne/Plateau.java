/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carcassonne;

import java.util.Scanner;

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
    
    public boolean verifPos(Tuile t)
    {
        boolean pos = false;
        // Case de droite
         if(this.getElement(t.getPosX()+1, t.getPosY()).getNord()!= null)
        {
            pos = true;
        }
         
        // Case de gauche
          if(this.getElement(t.getPosX()-1, t.getPosY()).getNord()!= null)
        {
            pos = true;
        }
        
        // Case du Haut
        if(this.getElement(t.getPosX(), t.getPosY()+1).getNord()!= null)
        {
            pos = true;
        }
        
        // Case du Bas
         if(this.getElement(t.getPosX(), t.getPosY()-1).getNord()!= null)
        {
            pos = true;
        }
        
        if(pos == false)
        {
            System.out.println("Action impossible \n");
        }
        
        return pos;
    }
    
    public void PlacerTuile(int x, int y, String nord, String sud, String est, String ouest)
    {
        Tuile t = new Tuile(x, y, EnumTuile.toEnum(nord), EnumTuile.toEnum(sud), EnumTuile.toEnum(est), EnumTuile.toEnum(ouest) );
        setElement(t);    
    }

    public void PlacementJoueur()
    {        
        int x = 0;
        int y = 0;
        String nord;
        String sud;
        String ouest;
        String est;
            
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir les informations de la tuile :");
        System.out.println("X : ");
        x = sc.nextInt();
        System.out.println("Y : ");
        y = sc.nextInt();
        System.out.println("Nord : ");
        nord = sc.next();
        System.out.println("Sud : ");
        sud = sc.next();
        System.out.println("Est : ");
        est = sc.next();
        System.out.println("Ouest : ");
        ouest = sc.next();
        PlacerTuile(x, y, nord, sud, est, ouest);
    }
}
