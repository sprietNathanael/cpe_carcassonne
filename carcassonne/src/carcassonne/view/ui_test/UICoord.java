/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.view.ui_test;

import carcassonne.coord.Coord;

/**
 * A mere coordinates class
 */
public class UICoord
{
    protected int x;
    protected int y;

    /**
     * Constructs a coordinate
     * @param x
     * @param y 
     */
    public UICoord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public UICoord(Coord coord)
    {
        this.x = coord.col;
        this.y = coord.row;
    }

    /**
     * Get the x component
     * @return 
     */
    public int getX()
    {
        return x;
    }

    /**
     * Get the y component
     * @return 
     */
    public int getY()
    {
        return y;
    }

    /**
     * Returns the textual representation of a coordinate
     * @return 
     */
    @Override
    public String toString()
    {
        return "x="+this.x+",y="+this.y;
    }

    /**
     * Test the equality between two coordinates
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UICoord other = (UICoord) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }
}
