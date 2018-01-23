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
import RessourcesGlobalVariables.PlayerTypes;
import carcassonne.model.carcassonnegame.CarcassonneGameInterface;
import carcassonne.notifyMessage.ObserverMessage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class which represents a generic class
 *
 * @author Thomas
 */
public class AbstractCarcassonneGameController extends Observable implements CarcassonneGameControllerInterface, java.util.Observer
{

    protected CarcassonneGame carcassonneGame;
    protected final CarcassonneGameInterface carcassonneGameInterface;
    private AbstractTile currentTile;
    private boolean useBigMeeple;
    private ObserverMessage observerMessage;
    

    /**
     * Constructor for an AbstractCarcassonneGameController
     *
     * @throws java.lang.Exception
     */
    public AbstractCarcassonneGameController() throws Exception
    {
        this.carcassonneGameInterface = new CarcassonneGame();
        this.useBigMeeple = false;
    }

    /**
     * Constructor for an AbstractCarcassonneGameController from an existing
     * model
     *
     * @param modelInterface
     */
    public AbstractCarcassonneGameController(CarcassonneGameInterface modelInterface)
    {
        this.carcassonneGameInterface = modelInterface;
        this.useBigMeeple = false;
    }
    
    public void setUseBigMeeple(boolean useBigMeeple)
    {
        this.useBigMeeple = useBigMeeple;
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
        this.putTile(tile, c.col, c.row);
    }
    
    public void putTile(AbstractTile tile,int col,int row) throws Exception
    {
        this.carcassonneGameInterface.setPreviousPlayer(this.getCurrentPlayer());
        this.carcassonneGameInterface.putTile(tile, row, col);
    }

    /**
     * Put the first tile on the board
     *
     * @throws Exception
     */
    public void putFirstTile() throws Exception
    {
        this.putTile(this.carcassonneGame.getFirstTile(), 0, 0);
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
            m = carcassonneGame.getCurrentPlayer().getBigMeepleAvailable();
            if(m == null)
            {
                throw new Exception("Plus de pion disponible");                
            }
        }
        return m;
    }
    
    /**
     * gets the big meeple available of the current player
     *
     * @return
     * @throws Exception
     */
    private Meeple getCurrentPlayerBigMeepleAvailable() throws Exception
    {
        Meeple m = carcassonneGame.getCurrentPlayer().getBigMeepleAvailable();
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
        Meeple m = null;
        if(this.useBigMeeple)
        {
            m = getCurrentPlayerBigMeepleAvailable();
            this.useBigMeeple = false;
            
        }
        if(m == null)
        {
            m = getCurrentPlayerMeepleAvailable();
            
        }
        //tuile
        currentTile.putMeeple(coordinates, m);
        m.setCurrentType(currentTile.getType(coordinates));
        //aggregats
        carcassonneGameInterface.putMeeple(m, currentTile, m.getPlayer(), coordinates);
        m.setIsUsed(true);
    }

    /**
     * Get the board
     *
     * @return
     */
    public Board getBoard()
    {
        return carcassonneGame.getBoard();
    }

    /**
     * Begin a game
     *
     * @throws java.lang.Exception
     */
    public void beginGame() throws Exception
    {
        /*boolean extensionRiver = true;
        boolean extensionIC = true;

        //-- Here, we will test if the Inns and Cathedrals extension is activated --//
        if (extensionIC == true) {
            this.carcassonneGame.useInnsAndCathedralsExtension();
        }

        //-- Here, we will test if the river extension is activated --//
        if (extensionRiver == true) {
            this.carcassonneGame.useRiverExtension();
        }*/
        this.putFirstTile();
        this.beginTurn();
    }

    /**
     * Begins a turn
     */
    public void beginTurn()
    {
        System.out.println("======================================================================================================");
        System.out.println("C'est au tour de " + this.carcassonneGame.getCurrentPlayer().getName());
        this.processNextTile();
        if (this.carcassonneGame.getCurrentPlayer().getPlayerType().equals(PlayerTypes.basicIA)) {
            try {
                this.ManageIA();
            } catch (Exception ex) {
                Logger.getLogger(AbstractCarcassonneGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Process the next Tile
     */
    private void processNextTile()
    {
        this.drawTile();
        if (this.currentTile != null) {
            System.out.println("La pièce piochée est : " + this.currentTile.getName());
            System.out.println(this.currentTile);
            this.carcassonneGameInterface.notifyBoardChanged();
            // If the current tile can be placed
            if (this.carcassonneGame.refreshPlacements()) {
                this.carcassonneGameInterface.notifyPlacementsReady();
            }
            // Else put back the current tile and draw another one
            else {
                this.carcassonneGameInterface.putBackCurrentTile();
                this.processNextTile();
            }
        }
        else {
            System.out.println("Fin de partie");
            this.carcassonneGameInterface.notifyGameEnds();
        }
    }

    /**
     * Manages IA
     *
     * @throws Exception
     */
    private void ManageIA() throws Exception
    {
        // Basic IA
        Coord tileCoordinates = this.putTileBasicIA();
        try {
            this.putMeepleBasicIA(tileCoordinates);
        } catch (Exception e) {
            System.out.println(e);
        }

        endTurn();
    }

    /**
     * Puts current tiles on random coord
     *
     * @param tile
     * @throws Exception
     */
    private Coord putTileBasicIA() throws Exception
    {
        ArrayList<Coord> placements = this.carcassonneGame.getPlacements();
        int index = (int) (Math.random() * placements.size());
        Coord c = new Coord(placements.get(index).col, placements.get(index).row);
        while (this.checkTilePosition(c) == false) {
            this.currentTile.rotateRight();
        }
        this.putCurrentTile(placements.get(index));
        return (c);
    }

    /**
     * Puts random meeple or no meeple
     *
     * @param c
     */
    public void putMeepleBasicIA(Coord c) throws Exception
    {
        if (this.carcassonneGame.playerMeepleBePutOnCurrentTile()) {
            int putMeepleOrNot = (int) (Math.random() * 2);
            if (putMeepleOrNot == 1) {
                Set<Set<String>> freeAgg = this.carcassonneGame.getFreeAggregatesInTile(c.col, c.row);
                String coordinates = this.getRandomAggregateLocation(freeAgg);
                //tuile
                this.putMeeple(coordinates);
            }
        }
    }

    /**
     * Gets alea coord from availables aggregates
     *
     * @param freeAgg
     * @return
     */
    private String getRandomAggregateLocation(Set<Set<String>> freeAgg)
    {
        int random = (int) (Math.random() * freeAgg.size());
        int i = 0, j = 0;

        for (Set<String> agg : freeAgg) {
            if (i == random) {
                random = (int) (Math.random() * agg.size());
                for (String co : agg) {
                    if (j == random) {
                        return co;
                    }
                    j++;
                }
            }
            i++;
        }
        return "";
    }

    /**
     * Ends the turn of a player
     *
     * @return the next player
     */
    public Player endTurn()
    {
        carcassonneGameInterface.manageCompletedAggregates();
        System.out.println("===========\nPoints des joueurs :" + this.carcassonneGame.getPlayers());
        Player player = this.carcassonneGame.nextPlayer();
        this.beginTurn();
        return player;
    }

    /**
     * Check if the tile can be placed here
     *
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
     *
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
    
    /**
     * Add an observer
     *
     * @param o
     */
    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }
    
    /**
     * Notifies the observers with the current notify message
     */
    @Override
    public void notifyObservers()
    {
        super.setChanged();
        super.notifyObservers(this.observerMessage);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        this.carcassonneGame = (CarcassonneGame)o;
        this.observerMessage = new ObserverMessage((String)arg, this.carcassonneGame);
        this.notifyObservers();
        
    }

}
