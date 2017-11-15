/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
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
        return this.carcassonneGame.drawFromPile();
    }

    public void putTile(Coord c, AbstractTile tile )
    {
        
    }
    
    
    
    
}
