/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import carcassonne.model.player.Meeple;
import java.io.Serializable;

/**
 * Abstract class that represents a generic type
 */
public abstract class AbstractType implements Serializable
{

    protected Meeple meeple;
    protected boolean isMeepable;

    /**
     * Constructor
     */
    public AbstractType()
    {
        this.meeple = null;
        this.isMeepable = true;
    }

    /**
     * Gets the points of a type
     *
     * @return number of points
     */
    public int getPoints()
    {
        return 0;
    }

    /**
     * Displays a default value
     *
     * @return default value in string
     */
    @Override
    public String toString()
    {
        return "00";
    }

    /**
     * Gets the Meeple of the type
     *
     * @return Meeple The current meeple of the type, null otherwise
     */
    public Meeple getMeeple()
    {
        return this.meeple;
    }
    
    /**
     * Allows to put a meeple for the differents types
     * @param m 
     */
    public void setMeeple(Meeple m)
    {
        this.meeple = m;
    }

    public void removeMeeple()
    {
        this.meeple = null;
    }

}
