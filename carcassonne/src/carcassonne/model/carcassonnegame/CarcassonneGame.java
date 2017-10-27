/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.board.Board;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;
import carcassonne.model.set.BasicSet;
import carcassonne.model.set.SetInterface;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a carcassonne game, which aggregates all the model entities
 */
public class CarcassonneGame implements CarcassonneGameInterface
{

    private List<AbstractTile> players;
    private Board board;
    private Player current;
    private List<AbstractTile> pile;

    public CarcassonneGame()
    {
        this.board = new Board();

        //Call the basic extension to get the basic tiles into the pile
        SetInterface basicSet = new BasicSet();
        this.pile = basicSet.getSet();
        this.players = new ArrayList<>();
    }

    /**
     * @Étienne Add a new set of tiles to the current pile (Used to add a new
     * extension to the game)
     *
     * @param newSet
     * @author Étienne
     */
    public void addSetToPile(SetInterface newSet)
    {
        pile.addAll(newSet.getSet());
    }

    @Override
    public void pileTile()
    {
        //Étienne: "- à quoi elle sert cette méthode ?"
    }

    /**
     * Puts a Tile on the Board
     * @param tile The tile to put
     * @param row The row at which the tile has to be put
     * @param column The column at which the tile has to be put
     * @throws Exception 
     */
    @Override
    public void putTile(AbstractTile tile, int row, int column) throws Exception
    {
        try {
            board.addTile(tile, row, column);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
