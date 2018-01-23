/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import java.io.Serializable;

/**
 * Represents a city type
 */
public class CityType extends AbstractType implements Serializable
{

    private final boolean hasShield;
    public final boolean hasCathedral;

    /**
     * constructor
     *
     */
    public CityType()
    {
        super();
        this.hasShield = false;
        this.hasCathedral = false;
    }

    /**
     * constructor
     *
     * @param hasShield
     */
    public CityType(boolean hasShield)
    {
        super();
        this.hasShield = hasShield;
        this.hasCathedral = false;
    }

    public CityType(Building building)
    {
        super();
        this.hasShield = false;
        this.hasCathedral = building.equals(Building.cathedral);
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
