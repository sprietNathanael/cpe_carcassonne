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

    /**
     * constructor
     */
    public CityType()
    {
        super();
    }
    
    /**
     * Displays the abrevation of the type
     * @return the type in string
     */
    @Override
    public String toString()
    {
        return "Ci";
    }

}
