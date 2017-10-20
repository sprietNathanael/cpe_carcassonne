/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.model.board.Board;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;
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
        this.pile = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    /**
     * Allows to take a new card in the pile
     *
     * @return
     */
    @Override
    public AbstractTile pileTile()
    {
        AbstractTile tile;
        int Nbrand;
        int Min = 1;
        int Max = pile.size(); //Numbers maximum of cards 

        Nbrand = (int) (Math.random() * (Max - Min));
        tile = pile.get(Nbrand);
        pile.remove(Nbrand);

        return tile;
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
