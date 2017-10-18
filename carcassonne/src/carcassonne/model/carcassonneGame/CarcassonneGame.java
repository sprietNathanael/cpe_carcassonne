/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonneGame;

import carcassonne.model.board.Board;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;

/**
 *
 * @author thomas
 */
public class CarcassonneGame
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
    
    public void putTile(int Row, int Column)
    {
        //A FAIRE AVEC LE BOULOT D ETIENNE
    }
    
    public void pileTile()
    {
        //A FAIRE AVEC LE BOULOT DE NATH
    }
    
}
