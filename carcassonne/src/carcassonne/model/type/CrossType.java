/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.type;

/**
 *
 * @author nathanael
 */
public class CrossType extends AbstractType
{
    
    private boolean isForest = true;

    /**
     * Get the value of isForest
     *
     * @return the value of isForest
     */
    public boolean isIsForest()
    {
        return isForest;
    }

    public CrossType(boolean isForest)
    {
        super();
        this.isMeepable = false;
        this.isForest = isForest;
    }

    
    
}
