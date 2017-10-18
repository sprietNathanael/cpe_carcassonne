/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.player;

/**
 * Shows a meeeple
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

    public boolean getIsUsed()
    {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed)
    {
        this.isUsed = isUsed;
    }

    public boolean getIsBig()
    {
        return isBig;
    }

    public void setIsBig(boolean isBig)
    {
        this.isBig = isBig;
    }

}
