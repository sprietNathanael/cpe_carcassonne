/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

import carcassonne.model.type.AbstractType;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a "Meeple", the follower or pawn of a player
 */
public class Meeple implements Cloneable, Serializable
{

    private boolean isUsed;
    private final boolean isBig;
    private Player player;
    private AbstractType currentType;

    /**
     * Creates a new meeple and precise its size.
     * A new meeple is not yet used when created,
     * so isUsed is set to false by default
     *
     * @param isBig
     * @param player
     */
    public Meeple(boolean isBig, Player player)
    {
        this.isBig = isBig;
        this.isUsed = false;
        this.player = player;
        this.currentType = null;
    }

    /**
     * Creates a default meeple
     * @param player
     */
    public Meeple(Player player)
    {
        this.isBig = false;
        this.isUsed = false;
        this.player = player;
        this.currentType = null;
    }
    
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
     * Get the status of the meeple
     *
     * @return true if the meeple is used, false otherwise
     */
    public boolean getIsUsed()
    {
        return isUsed;
    }

    /**
     * Set the status of the meeple
     *
     * @param isUsed
     */
    public void setIsUsed(boolean isUsed)
    {
        this.isUsed = isUsed;
    }

    /**
     * Get the size of the meeple
     *
     * @return true if the meeple is a big one, false otherwise
     */
    public boolean getIsBig()
    {
        return isBig;
    }
    
    public Player getPlayer()
    {
        return this.player;
    }

    public AbstractType getCurrentType()
    {
        return currentType;
    }

    public void setCurrentType(AbstractType currentType)
    {
        this.currentType = currentType;
    }
    
    public void removeMeepleFromType(){
        this.currentType.removeMeeple();
        this.currentType = null;
    }
    
    @Override
    public Object clone()
    {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Meeple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    @Override
    public String toString()
    {
        return "[Meeple] isBig = "+this.isBig+" isUsed = "+this.isUsed; //To change body of generated methods, choose Tools | Templates.
    }
    
        
    

}
