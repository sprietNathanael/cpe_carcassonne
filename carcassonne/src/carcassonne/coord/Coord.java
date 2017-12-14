/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.coord;

/**
 *
 * @author Étienne
 */
public class Coord
{

    public int row, col;

    public Coord(int col, int row)
    {
        this.row = row;
        this.col = col;
    }
    
    public Coord(Coord coord)
    {
        this.row = coord.row;
        this.col = coord.col;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 89 * hash + this.row;
        hash = 89 * hash + this.col;
        return hash;
    }

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
        final Coord other = (Coord) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Coord{" + "row=" + row + ", col=" + col + '}';
    }
}
