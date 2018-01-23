/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import java.io.Serializable;

/**
 * Represents a field type
 */
public class FieldType extends AbstractType implements Serializable
{

    public final boolean hasInn;

    /**
     * Constructor
     */
    public FieldType()
    {
        super();
        hasInn = false;
    }

    public FieldType(Building building)
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
        return "Fi";
    }

}
