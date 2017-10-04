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
public class Tuile 
{
    private int posX;
    private int posY;
    private EnumTuile nord;
    private EnumTuile sud;
    private EnumTuile est;
    private EnumTuile ouest;

    public Tuile(int posX, int posY, EnumTuile nord, EnumTuile sud, EnumTuile est, EnumTuile ouest) {
        this.posX = posX;
        this.posY = posY;
        this.nord = nord;
        this.sud = sud;
        this.est = est;
        this.ouest = ouest;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public EnumTuile getNord() {
        return nord;
    }

    public void setNord(EnumTuile nord) {
        this.nord = nord;
    }

    public EnumTuile getSud() {
        return sud;
    }

    public void setSud(EnumTuile sud) {
        this.sud = sud;
    }

    public EnumTuile getEst() {
        return est;
    }

    public void setEst(EnumTuile est) {
        this.est = est;
    }

    public EnumTuile getOuest() {
        return ouest;
    }

    public void setOuest(EnumTuile ouest) {
        this.ouest = ouest;
    }

    
    @Override
    public String toString() {
        //return "|\t" + nord + "\t     |\n|" +  ouest + " \t\t" + est + "|\n|\t" + sud + "\t     |";
        if (nord != null)
            return ouest.toString() +  nord.toString() + sud.toString() + est.toString();
        else
            return " " +  " " + " " + " ";
        
    }
     
}
