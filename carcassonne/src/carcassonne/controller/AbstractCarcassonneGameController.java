/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.controller;

import carcassonne.coord.Coord;
import carcassonne.model.board.Board;
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

    private final CarcassonneGame carcassonneGame;
    private AbstractTile currentTile;

    /**
     * Constructor for an AbstractCarcassonneGameController
     * @throws java.lang.Exception
     */
    public AbstractCarcassonneGameController() throws Exception
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
        this.currentTile = this.carcassonneGame.drawFromPile();
        return this.currentTile;
    }

    /**
     * puts the tile drawed on the board
     *
     * @param c
     * @throws Exception
     */
    public void putCurrentTile(Coord c) throws Exception
    {
        this.putTile(this.currentTile, c);
        //this.endTurn();
    }

    /**
     * Puts a tile on the board
     *
     * @param tile
     * @param c
     * @throws Exception
     */
    public void putTile(AbstractTile tile, Coord c) throws Exception
    {
        this.carcassonneGame.putTile(tile, c.row, c.col);
    }

    /**
     * Put the first tile on the board
     *
     * @throws Exception
     */
    public void putFirstTile() throws Exception
    {
        this.carcassonneGame.putTile(this.carcassonneGame.getFirstTile(), 0, 0);
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
        carcassonneGame.putMeeple(m, currentTile, m.getPlayer(), coordinates);
        m.setIsUsed(true);
    }

    public Board getBoard()
    {
        return carcassonneGame.getBoard();
    }

    /**
     * Begin a game
     * @throws java.lang.Exception
     */
    public void beginGame() throws Exception
    {
        this.putFirstTile();
        this.beginTurn();
    }

    /**
     * Begins a turn
     */
    public void beginTurn()
    {
        System.out.println("======================================================================================================");
        System.out.println("C'est au tour de "+this.carcassonneGame.getCurrentPlayer().getName());
        this.drawTile();
        System.out.println("La pièce piochée est : "+this.currentTile.getName());
        System.out.println(this.currentTile);
        this.carcassonneGame.notifyBoardChanged();
        this.carcassonneGame.refreshPlacements();
        this.carcassonneGame.notifyPlacementsReady();
    }

    /**
     * Ends the turn of a player
     *
     * @return the next player
     */
    public Player endTurn()
    {
        // TODO : compter les points
        Player player = this.carcassonneGame.nextPlayer();
        this.beginTurn();
        return player;
    }
    
    /**
     * Check if the tile can be placed here
     * @param coordinates
     * @param tile
     * @return 
     */
    public boolean checkTilePosition(Coord coordinates, AbstractTile tile)
    {
        return this.carcassonneGame.checkTilePosition(coordinates, tile);
    }
    
    /**
     * Check if the current tile can be placed here
     * @param coordinates
     * @return 
     */
    public boolean checkTilePosition(Coord coordinates)
    {
        return this.carcassonneGame.checkTilePosition(coordinates);
    }
    
    /**
     * Turn the current tile
     */
    public void turnRight()
    {
        this.currentTile.rotateRight();
    }
    
}
