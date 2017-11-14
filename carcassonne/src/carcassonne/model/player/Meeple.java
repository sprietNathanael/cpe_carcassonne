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
     * Creates a new meeple and precise its size.
     * A new meeple is not yet used when created,
     * so isUsed is set to false by default
     *
     * @param isBig
     */
    public Meeple(boolean isBig)
    {
        this.isBig = isBig;
        this.isUsed = false;
    }

    /**
     * Creates a default meeple
     */
    public Meeple()
    {
        this.isBig = false;
        this.isUsed = false;
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

}
