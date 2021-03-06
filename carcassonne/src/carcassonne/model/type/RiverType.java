/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import carcassonne.model.player.Meeple;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a river type
 */
public class RiverType extends AbstractType implements Serializable
{

    /**
     * Constructor
     */
    public RiverType()
    {
        super();
        this.isMeepable = false;
    }

    /**
     * Displays the abrevation of the type
     *
     * @return the type in string
     */
    @Override
    public String toString()
    {
        return "Ri";
    }

    /**
     * Allows to put a meeple
     * @param m 
     */
    @Override
    public void setMeeple(Meeple m)
    {
        try {
            throw new Exception("Impossible to put !");
        } catch (Exception ex) {
            Logger.getLogger(CrossType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
