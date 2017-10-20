/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.tile;

/**
 * Abstract class to represent a Tile
 */
public abstract class AbstractTile
{

    /**
     * Constructor
     */
    public AbstractTile()
    {
    }

    /**
     * Rotates the tile by 90° from east to west
     *
     * @return true if the tile has been correctly rotated
     */
    public boolean rotateLeft()
    {
        return true;
    }

    /**
     * Rotates the tile by 90° from west to east
     *
     * @return true if the tile has been correctly rotated
     */
    public boolean rotateRight()
    {
        return true;
    }

    /**
     * Can a meeple be put on the tile
     *
     * @return true if a meeple can be put on the tile
     */
    public boolean isMeepable()
    {
        return true;
    }

    @Override
    public abstract String toString();
    
    
}
