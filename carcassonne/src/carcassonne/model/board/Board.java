/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.board;

import carcassonne.model.tile.AbstractTile;

/**
 * Manage the tiles on the board
 *
 * @author Étienne
 */
public class Board implements BoardInterface
{

    private AbstractTile[][] grid;

    /**
     * Initialize an empty board
     *
     */
    public Board()
    {
        grid = new AbstractTile[ROWS][COLUMNS];
    }

    /**
     * Initialize the board using the baseTile as the first tile of the game
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

}
