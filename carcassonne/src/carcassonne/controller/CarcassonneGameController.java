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
public class CarcassonneGameController extends Observable implements CarcassonneGameControllerInterface, java.util.Observer
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
    public CarcassonneGameController() throws Exception
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
    public CarcassonneGameController(CarcassonneGameInterface modelInterface)
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

    public void putTile(AbstractTile tile, int col, int row) throws Exception
    {
        this.carcassonneGameInterface.putTile(tile, row, col);
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
            if (m == null) {
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
        if (this.useBigMeeple) {
            m = getCurrentPlayerBigMeepleAvailable();
            this.useBigMeeple = false;

        }
        if (m == null) {
            m = getCurrentPlayerMeepleAvailable();

        }

        //aggregats
        carcassonneGameInterface.putMeeple(m, currentTile, m.getPlayer(), coordinates);
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
    public void beginGame()
    {

        this.carcassonneGameInterface.beginGame();
    }

    /**
     * Begins a turn
     */
    public void beginTurn()
    {
        System.out.println("======================================================================================================");
        System.out.println("C'est au tour de " + this.carcassonneGame.getCurrentPlayer().getName());
    }

    /**
     * Manages IA
     *
     * @throws Exception
     */
    private void ManageIA()
    {
        if (this.carcassonneGame.getCurrentPlayer().getPlayerType().equals(PlayerTypes.basicIA)) {
            try {
                // Basic IA
                Coord tileCoordinates = this.putTileBasicIA();
                try {
                    this.putMeepleBasicIA(tileCoordinates);
                } catch (Exception e) {
                    System.out.println(e);
                }

                this.endTurn();
            } catch (Exception ex) {
                Logger.getLogger(CarcassonneGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
        //Random emplacement pour la tuile parmis ceux possible
        int index = (int) (Math.random() * placements.size());
        /*@TODO: 
            Chercher parmi les ville libre voisine de ces coord, filtrer celles qui sont ouvertes dessus: prendre la plus grosse
                carcaGame.getBiggestCityAvailableInCoords(placements);
                 -> Retourne les Coord du placement intéressant
                 -> null sinon, comportement normal, renvoyer null si un joueur adverse a une ville qui ouvre sur cet emplacement
                this.biggestCityFound = true;
         */
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
                /*@TODO:
                    If biggestCity found:
                        A l'emplacement, récupérer la ville la plus grosse
                            Récupérer la plus grosse ville libre de cette tuile
                            coordinates = carcaGame.getLocationOfBiggestCity(c); 
                             -> renvoyer un type la composant pour poser un meeple dessus
                             -> Si null habituel
                            this.putMeeple(coordinates);
                 */
                Set<Set<String>> freeAgg = this.carcassonneGame.getFreeAggregatesInTile(c.col, c.row);
                /*@TODO:
                    Si Abbaye ou Cathédrale ou Auberge présente, renvoyer la location correspondante
                    coordinates = this.getSpecialBonusTypesInTile(freeAgg, c.col, c.row);
                 */
                String coordinates = this.getRandomAggregateLocation(freeAgg);
                //tuile
                this.putMeeple(coordinates);
            }
            /*@TODO:
                tester si une route libre va se terminer si oui on pose dessus
             */
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
     */
    public void endTurn()
    {
        this.carcassonneGameInterface.endTurn();
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
        //System.out.println("Turn right "+this.currentTile);
        //this.carcassonneGameInterface.rotateCurrentTileRight();
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
        ObserverMessage obsMessage = (ObserverMessage) arg;
        this.carcassonneGame = obsMessage.game;
        this.currentTile = this.carcassonneGame.getCurrentTile();
        String message = obsMessage.messageType;
        System.out.println("________________ Controller Update : " + message + " tile size " + carcassonneGame.getBoard().getAllTiles().size());
        if (message.equals("newTurn")) {
            this.beginTurn();
        }
        else if (message.equals("placementsReady")) {
            this.ManageIA();
        }
        this.observerMessage = new ObserverMessage(message, this.carcassonneGame);
        this.notifyObservers();

    }

}
