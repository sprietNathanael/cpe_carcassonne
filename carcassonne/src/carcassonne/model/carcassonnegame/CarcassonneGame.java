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

    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private List<AbstractTile> pile;

    public CarcassonneGame()
    {
        this.board = new Board();

        //Call the basic extension to get the basic tiles into the pile
        SetInterface basicSet = new BasicSet();
        this.pile = basicSet.getSet();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }

    /**
     * Constructor for CarcassonneGame from an existing list of players
     *
     * @param players existing list of players
     */
    public CarcassonneGame(ArrayList<Player> players)
    {
        this.board = new Board();

        //Call the basic extension to get the basic tiles into the pile
        SetInterface basicSet = new BasicSet();
        this.pile = basicSet.getSet();
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    /**
     * Gets the current Player
     *
     * @return the current player
     */
    public Player getCurrentPlayer()
    {
        return (this.players.get(this.currentPlayerIndex));
    }

    /**
     * Switches the turn to the next player
     *
     * @return the next Player
     */
    public Player nextPlayer()
    {
        this.currentPlayerIndex++;

        /**
         * If this is the end of the array, go back to the begining
         */
        if (this.currentPlayerIndex >= this.players.size()) {
            this.currentPlayerIndex = 0;
        }
        return this.getCurrentPlayer();
    }

    /**
     * Adds a new set of tiles to the current pile (Used to add a new extension
     * to the game)
     *
     * @param newSet
     * @author Étienne
     */
    public void addSetToPile(SetInterface newSet)
    {
        pile.addAll(newSet.getSet());
    }
    
    /**
     * Draws the first tile of the pile. Removes it from the pile and then
     * returns it
     *
     * @return AbstractTile The first tile of the pile
     */
    public AbstractTile drawFromPile()
    {
        return this.pile.remove(0);

    }

    /**
     * Puts a Tile on the Board
     *
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

