/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

/**
 * Represents a city type
 */
public class CityType extends AbstractType
{

    private boolean hasShield;

    /**
     * constructor
     *
     */
    public CityType()
    {
        super();
        this.hasShield = false;
    }

    /**
     * constructor
     *
     * @param hasShield
     */
    public CityType(boolean hasShield)
    {
        this.hasShield = hasShield;
    }

    /**
     * Displays the abrevation of the type
     *
     * @return the type in string
     */
    @Override
    public String toString()
    {
        return "Ci";
    }

    /**
     * Tells if the city has a shield in it
     *
     * @return boolean true if it has a shield, false otherwise
     */
    public boolean isShielded()
    {
        return hasShield;
    }

}
