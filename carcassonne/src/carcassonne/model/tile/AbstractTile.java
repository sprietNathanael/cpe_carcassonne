/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.tile;

import carcassonne.model.player.Meeple;
import carcassonne.model.type.AbstractType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class to represent a Tile
 */
public abstract class AbstractTile
{
    protected String id;
    protected String name;
    private int rotation;

    /**
     * Constructor
     *
     * @param name
     */
    public AbstractTile(String name)
    {
        this.name = name;
        this.rotation = 0;
    }

    /**
     * Get the id of the tile
     *
     * @return the id of the tile
     */
    public String getId()
    {
        return this.id;
    }

    public int getRotation()
    {
        return this.rotation;
    }

    /**
     * Get the name of the Tile
     *
     * @return
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Rotates the tile by 90° from east to west
     *
     * @return true if the tile has been correctly rotated
     */
    public boolean rotateLeft()
    {
        this.rotation -= 90;
        if (this.rotation < 0) {
            this.rotation += 360;
        }
        return true;
    }

    /**
     * Rotates the tile by 90° from west to east
     *
     * @return true if the tile has been correctly rotated
     */
    public boolean rotateRight()
    {
        this.rotation += 90;
        if (this.rotation >= 360) {
            this.rotation -= 360;
        }
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

    /**
     * Gets the type of the tile from coordinates
     *
     * @param coordinates
     * @return
     */
    public abstract AbstractType getType(String coordinates);

    /**
     * Quickly check if the tile is composed of a cross road
     *
     * @return boolean
     */
    public abstract boolean isCrossRoad();

    /**
     * puts the meeple on the tile following the coordinate
     *
     * @param coordinates
     * @param m
     */
    public void putMeeple(String coordinates, Meeple m)
    {
        AbstractType type = getType(coordinates);
        type.setMeeple(m);
    }

    /**
     * Compare north side of the tile with the south side of another tile
     *
     * @param tile
     * @return true if match
     */
    public abstract boolean compareTileNorth(AbstractTile tile);

    /**
     * Compare south side of the tile with the north tile of another tile
     *
     * @param tile
     * @return true if match
     */
    public abstract boolean compareTileSouth(AbstractTile tile);

    /**
     * Compare east side of the tile with the west side of another tile
     *
     * @param tile
     * @return true if match
     */
    public abstract boolean compareTileEast(AbstractTile tile);

    /**
     * Compare west side of the tile with the east side of another tile
     *
     * @param tile
     * @return true if match
     */
    public abstract boolean compareTileWest(AbstractTile tile);

}
