/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

/**
 * Represents a river type
 */
public class RiverType extends AbstractType
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
}
