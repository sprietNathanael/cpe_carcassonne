/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.board;

import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;

/**
 * Manages the tiles on the board
 *
 * @author Étienne
 */
public class Board implements BoardInterface
{

    private AbstractTile[][] grid;

    /**
     * Initializes an empty board
     *
     */
    public Board()
    {
        grid = new AbstractTile[ROWS][COLUMNS];
    }

    /**
     * Initializes the board using the baseTile as the first tile of the game
     *
     * @param baseTile
     */
    public Board(AbstractTile baseTile)
    {
        grid = new AbstractTile[ROWS][COLUMNS];

        //Put the first tile in the center of the Board
        grid[CENTER_ROW][CENTER_COLUMN] = baseTile;
    }

    @Override
    public void addTile(AbstractTile newTile, int row, int column) throws Exception
    {
        try {
            if (grid[row][column] == null) {
                grid[row][column] = newTile;
            }
            else {
                throw new Exception("There is already a tile in the location ["
                        + row + ":" + column + "]");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("The new tile has indexes that are out of the grid range");
        }
    }

    @Override
    public AbstractTile getTile(int row, int column) throws Exception
    {
        try {
            return grid[row][column];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new Exception("The position asked is out of the grid range");
        }
    }
    
    //Compare la face nord de la tuile 1 à la face sud de la tuile 2
    public boolean compareTileNorth (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getNNW() == tile2.getSSW() && tile1.getN() == tile2.getS() && tile1.getNNE() == tile2.getSSE();
    }
    
    //Compare la face sud de la tuile 1 à la face nord de la tuile 2
    public boolean compareTileSouth (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getSSW() == tile2.getNNW() && tile1.getS() == tile2.getN() && tile1.getSSE() == tile2.getNNE();
    }
    
    //Compare la face ouest de la tuile 1 à la face est de la tuile 2
    public boolean compareTileWest (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getNWW() == tile2.getNEE() && tile1.getW() == tile2.getE() && tile1.getSWW() == tile2.getSEE();
    }
    
    //Compare la face est de la tuile 1 à la face ouest de la tuile 2
    public boolean compareTileEast (CasualTile tile1, CasualTile tile2)
    {
        return tile1.getNEE() == tile2.getNWW() && tile1.getE() == tile2.getW() && tile1.getSEE() == tile2.getSWW();
    }
}
