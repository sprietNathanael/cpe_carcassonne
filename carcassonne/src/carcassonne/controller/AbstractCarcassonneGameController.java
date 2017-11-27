/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;

/**
 * Abstract class which represents a generic class
 *
 * @author Thomas
 */
public class AbstractCarcassonneGameController implements CarcassonneGameControllerInterface
{

    private CarcassonneGame carcassonneGame;
    private AbstractTile currentTile;

    /**
     * Constructor for an AbstractCarcassonneGameController
     */
    public AbstractCarcassonneGameController()
    {
        this.carcassonneGame = new CarcassonneGame();
    }

    /**
     * Constructor for an AbstractCarcassonneGameController from an existing
     * model
     *
     * @param model an existing CarcassonneGame
     */
    public AbstractCarcassonneGameController(CarcassonneGame model)
    {
        this.carcassonneGame = model;
    }

    /**
     * Gets the current player
     *
     * @return Player the current player
     */
    public Player getCurrentPlayer()
    {
        return (this.carcassonneGame.getCurrentPlayer());
    }

    /**
     * Draws the first tile of the pile
     *
     * @return AbstractTile the first tile of the pile
     */
    public AbstractTile drawTile()
    {
        currentTile = this.carcassonneGame.drawFromPile();
        return currentTile;
    }

    /**
     * puts the tile drawed on the board
     *
     * @param c
     * @throws Exception
     */
    public void putTile(Coord c) throws Exception
    {
        carcassonneGame.putTile(currentTile, c.row, c.col);
    }

    /**
     * gets the first meeple available of the current player
     *
     * @return
     * @throws Exception
     */
    private Meeple getCurrentPlayerMeepleAvailable() throws Exception
    {
        Meeple m = carcassonneGame.getCurrentPlayer().getFirstMeepleAvailable();
        if (m == null) {
            throw new Exception("Plus de pion disponible");
        }
        return m;
    }

    /**
     * puts the first meeple available, of the current player, on a type of the
     * current tile
     *
     * @param coordinates
     * @throws Exception
     */
    public void putMeeple(String coordinates) throws Exception
    {
        Meeple m = getCurrentPlayerMeepleAvailable();
        currentTile.putMeeple(coordinates, m);
    }

    /**
     * Ends the turn of a player
     *
     * @return the next player
     */
    public Player endTurn()
    {
        // TODO : compter les points
        return carcassonneGame.nextPlayer();
    }

}
