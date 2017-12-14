/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.board;

import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.coord.Coord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the tiles on the board
 *
 * @author Étienne
 */
public class Board implements BoardInterface
{

    // private AbstractTile[][] grid;
    private HashMap<Coord, AbstractTile> grid;

    /**
     * Initializes an empty board
     *
     */
    public Board()
    {
        //grid = new AbstractTile[ROWS][COLUMNS];
        grid = new HashMap();
    }
    
    /**
     * adds a new tile on the board at the coordinate passed passed by parameters
     * @param newTile
     * @param row
     * @param column
     * @throws Exception 
     */
    @Override
    public void addTile(AbstractTile newTile, int row, int column) throws Exception
    {
        try {
            if (!grid.containsKey(new Coord(column, row))) {
                grid.put(new Coord(column, row), newTile);
            }
            else {
                throw new Exception("There is already a tile in the location ["
                        + row + ":" + column + "]");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("The new tile has indexes that are out of the grid range");
        }
    }

    /**
     * gets the tile from the board on the coordinate passed by parameters
     * @param row
     * @param column
     * @return
     * @throws Exception 
     */
    @Override
    public AbstractTile getTile(int row, int column) throws Exception
    {
        try {
            return grid.get(new Coord(column, row));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("The position asked is out of the grid range");
        }
    }
    
    /**
     * Compare north side of tile 1 to south side of tile 2
     * @param tile1
     * @param tile2
     * @return true if match
     */
    public boolean compareTileNorth (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getNNW().getClass() == tile2.getSSW().getClass() 
                && tile1.getN().getClass() == tile2.getS().getClass() 
                && tile1.getNNE().getClass() == tile2.getSSE().getClass();
    }
    
    /**
     * Compare south side of tile 1 to north side of tile 2
     * @param tile1
     * @param tile2
     * @return true if match
     */
    public boolean compareTileSouth (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getSSW().getClass() == tile2.getNNW().getClass() 
                && tile1.getS().getClass() == tile2.getN().getClass() 
                && tile1.getSSE().getClass() == tile2.getNNE().getClass();
    }
    
    /**
     * Compare west side of tile 1 to east side of tile 2
     * @param tile1
     * @param tile2
     * @return true if match
     */
    public boolean compareTileWest (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getNWW().getClass() == tile2.getNEE().getClass() 
                && tile1.getW().getClass() == tile2.getE().getClass() 
                && tile1.getSWW().getClass() == tile2.getSEE().getClass();
    }
    
    /**
     * Compare east side of tile 1 to west side of tile 2
     * @param tile1
     * @param tile2
     * @return true if match
     */
    public boolean compareTileEast (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getNEE().getClass() == tile2.getNWW().getClass() 
                && tile1.getE().getClass() == tile2.getW().getClass() 
                && tile1.getSEE().getClass() == tile2.getSWW().getClass();
    }
}
