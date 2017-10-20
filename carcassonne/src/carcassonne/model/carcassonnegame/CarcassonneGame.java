/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.board.Board;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;

/**
 * Allows to play 
 */
public abstract class CarcassonneGame implements CarcassonneGameInterface
{
    AbstractTile tile;
    Player[] player;
    Board board;
    Player current;
    AbstractTile[] pile;
    
    public CarcassonneGame()
    {
        this.board = new Board();
        
    }
    
    public void pileTile()
    {
        //A FAIRE AVEC LE BOULOT DE NATH
    }



       /** 
     * Allows to put a Tile on the board
     * @param tile
     * @param Row
     * @param Column
     * @throws Exception 
     */
    public void putTile(AbstractTile tile,int Row, int Column) throws Exception
    {
        try {
            board.addTile(tile, Row, Column);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
