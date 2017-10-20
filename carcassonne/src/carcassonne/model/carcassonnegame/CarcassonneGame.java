/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.board.Board;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomas
 */
public class CarcassonneGame implements CarcassonneGameInterface
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

    @Override
    public void putTile(int Row, int Column) throws Exception
    {
        try {
            board.addTile(tile, Row, Column);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
