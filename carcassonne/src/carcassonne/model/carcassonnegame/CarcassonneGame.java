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
 * Allows to play
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
     * Allows to put a Tile on the board
     *
     * @param tile
     * @param Row
     * @param Column
     * @throws Exception
     */
    @Override
    public void putTile(AbstractTile tile, int Row, int Column) throws Exception
    {
        try {
            board.addTile(tile, Row, Column);
        } catch (Exception ex) {
            throw ex;
        }
    }

}
