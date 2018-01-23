/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

import java.io.Serializable;

/**
 *
 * Represents an abbay type
 */
public class AbbayType extends AbstractType implements Serializable
{

    /**
     * Constructor
     */
    public AbbayType()
    {
        super();
    }

    /**
     * Displays the abrevation of the type
     *
     * @return the type in string
     */
    @Override
    public String toString()
    {
        return "Ab";
    }
    
}
