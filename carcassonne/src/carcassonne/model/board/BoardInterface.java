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
     * Add a tile into the requested location on the Board
     *
     * @param newTile
     * @param row
     * @param column
     * @exception ArrayIndexOutOfBoundsException if not in the grid
     * @exception Exception if there is already a tile in the location
     */
    public void addTile(AbstractTile newTile, int row, int column) throws Exception;

    /**
     * Get the tile at the indexes asked
     *
     * @param row
     * @param column
     * @exception ArrayIndexOutOfBoundsException if not in the grid
     * @return AbstractTile
     * @throws java.lang.Exception
     */
    public AbstractTile getTile(int row, int column) throws Exception;
}
