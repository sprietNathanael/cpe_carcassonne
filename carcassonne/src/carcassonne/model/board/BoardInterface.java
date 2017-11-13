/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.board;

import carcassonne.model.tile.AbstractTile;

/**
 *
 * @author Étienne
 */
public interface BoardInterface
{

    public static final int ROWS = 100, COLUMNS = 100;
    public static final int CENTER_ROW = ROWS / 2, CENTER_COLUMN = COLUMNS / 2;

    /**
     * Adds a tile into the requested location on the Board
     *
     * @param newTile The tile to be put
     * @param row The row at which the tile has to be put
     * @param column The column at which the tile has to be put
     * @exception ArrayIndexOutOfBoundsException if not in the grid
     * @exception Exception if there is already a tile in the location
     */
    public void addTile(AbstractTile newTile, int row, int column) throws Exception;

    /**
     * Gets the tile at the indexes asked
     *
     * @param row The row to look at
     * @param column The column to look at
     * @exception ArrayIndexOutOfBoundsException if not in the grid
     * @return AbstractTile
     * @throws java.lang.Exception
     */
    public AbstractTile getTile(int row, int column) throws Exception;
}
