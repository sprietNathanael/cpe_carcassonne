/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.tile.AbstractTile;

/**
 *
 * @author thomas
 */
public interface CarcassonneGameInterface
{
    /**
     * Puts a Tile on the Board
     * @param tile The tile to put
     * @param row The row at which the tile has to be put
     * @param column The column at which the tile has to be put
     * @throws Exception 
     */
    public void putTile(AbstractTile tile, int row, int column) throws Exception;
    
    /**
     * 
     */
    public void pileTile();
}
