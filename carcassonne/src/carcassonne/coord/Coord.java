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

    public Coord(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    //@TODO: fonctionnement des deux override ?
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + this.row;
        hash = 71 * hash + this.col;
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

}
