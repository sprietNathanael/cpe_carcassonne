/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

import carcassonne.model.type.AbstractType;

/**
 * Represents a "Meeple", the follower or pawn of a player
 */
public class Meeple
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
     */
    public Meeple(Player player)
    {
        this.isBig = false;
        this.isUsed = false;
        this.player = player;
        this.currentType = null;
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
    
    

}
