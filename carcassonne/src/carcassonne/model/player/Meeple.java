/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

/**
 * Represents a "Meeple", the follower or pawn of a player
 */
public class Meeple
{

    private boolean isUsed;
    private boolean isBig;

    /**
     * Allows to create a new meeple
     *
     * @param isUsed
     */
    private Meeple(boolean isUsed)
    {
        this.isUsed = isUsed;
    }

    /**
     * Allows to create a new meeple with isUsed=false
     */
    public Meeple()
    {
        this(false);
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

    public void setIsBig(boolean isBig)
    {
        this.isBig = isBig;
    }

}
