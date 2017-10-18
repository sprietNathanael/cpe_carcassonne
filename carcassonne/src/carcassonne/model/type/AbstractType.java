/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import carcassonne.model.player.Meeple;

/**
 *
 * @author nathanael
 */
public abstract class AbstractType
{
    protected Meeple meeple;
    protected boolean isMeepable;

    public AbstractType()
    {
        this.meeple = null;
        this.isMeepable = true;
    }
    
    public int getPoints()
    {
        return 0;
    }
    
}
