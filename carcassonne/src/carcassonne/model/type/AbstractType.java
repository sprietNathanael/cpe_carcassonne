/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import carcassonne.model.player.Meeple;

/**
 * Abstract class that represents a generic type
 */
public abstract class AbstractType
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
     * Get points of a type
     *
     * @return number of points
     */
    public int getPoints()
    {
        return 0;
    }

}
