/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import java.io.Serializable;

/**
 * Represents a road type
 */
public class RoadType extends AbstractType implements Serializable
{

    public final boolean hasInn;

    /**
     * Constructor
     */
    public RoadType()
    {
        super();
        hasInn = false;
    }

    public RoadType(Building building)
    {
        super();
        hasInn = building.equals(Building.inn);
    }

    /**
     * Displays the abrevation of the type
     *
     * @return the type in string
     */
    @Override
    public String toString()
    {
        return "Ro";
    }
}
