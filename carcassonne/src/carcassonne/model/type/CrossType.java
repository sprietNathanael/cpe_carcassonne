/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import carcassonne.model.player.Meeple;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a cross type
 */
public class CrossType extends AbstractType
{

    /**
     * Constructor
     *
     */
    public CrossType()
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
        return "Cr";
    }

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
