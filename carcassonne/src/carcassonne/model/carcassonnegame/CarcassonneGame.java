/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.carcassonnegame;

import carcassonne.coord.Coord;
import carcassonne.model.aggregate.CityAggregate;
import carcassonne.model.aggregate.FieldAggregate;
import carcassonne.model.aggregate.RoadAggregate;
import carcassonne.model.board.Board;
import carcassonne.model.player.Meeple;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.player.Player;
import carcassonne.model.set.BasicSet;
import carcassonne.model.set.SetInterface;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbstractType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Represents a carcassonne game, which aggregates all the model entities
 */
public class CarcassonneGame extends Observable implements CarcassonneGameInterface
{

    /**
     * private Map<RoadAggregate, Set<Coord>> getNeighborRoads(int col, int row)
     * { Map<RoadAggregate, Set<Coord>> result = new HashMap<>();
     *
     * for (RoadAggregate currentRoad : roadAggregates) { if
     * (currentRoad.isNeighborOf(col, row)) { result.add(currentRoad); } }
     *
     * return result; }
     *
     * private List<CityAggregate> getNeighborCities(int col, int row) {
     * List<CityAggregate> result = new ArrayList<>();
     *
     * for (CityAggregate currentCity : cityAggregates) { if
     * (currentCity.isNeighborOf(col, row)) { result.add(currentCity); } }
     *
     * return result; }
     *
     * private List<FieldAggregate> getNeighborFields(int col, int row) {
     * List<FieldAggregate> result = new ArrayList<>();
     *
     * for (FieldAggregate currentField : fieldAggregates) { if
     * (currentField.isNeighborOf(col, row)) { result.add(currentField); } }
     *
     * return result; }
     */
    private ArrayList<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private List<AbstractTile> pile;
    private AbstractTile firstTile;
    private AbstractTile currentTile;
    private ArrayList<Coord> placements;
    private List<RoadAggregate> roadAggregates;
    private List<CityAggregate> cityAggregates;
    private List<FieldAggregate> fieldAggregates;
    private String notifyMessage;

    public CarcassonneGame() throws Exception
    {
        this(new ArrayList<Player>());
    }

    /**
     * Constructor for CarcassonneGame from an existing list of players
     *
     * @param players existing list of players
     */
    public CarcassonneGame(ArrayList<Player> players) throws Exception
    {
        this.board = new Board();

        //Call the basic extension to get the basic tiles into the pile
        SetInterface basicSet = new BasicSet();
        this.pile = basicSet.getSet();
        Collections.shuffle(this.pile);
        this.firstTile = basicSet.getFirstTile();
        this.currentPlayerIndex = 0;
        this.placements = new ArrayList<>();
        this.players = players;
        roadAggregates = new ArrayList<>();
        cityAggregates = new ArrayList<>();
        fieldAggregates = new ArrayList<>();
    }

    /**
     * Get the first tile of the game
     *
     * @return
     */
    public AbstractTile getFirstTile()
    {
        return this.firstTile;
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
        this.currentTile = this.pile.remove(0);
        this.refreshPlacements();
        return this.currentTile;

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
        board.addTile(tile, row, column);
        this.notifyBoardChanged();
        //this.manageNewTileAggregates(tile, row, column);
    }

    /**
     * Alloxs to put a meeple on a type
     *
     * @param meeple
     * @param tile
     * @param player
     * @param type
     * @throws Exception
     */
    public void putMeeple(Meeple meeple, AbstractTile tile, Player player, AbstractType type) throws Exception
    {
        if (player.checkMeepleAvailable() == false) {
            throw new Exception("no meeple");
        }
        else {
            /*Player has meeple*/

 /*Check if a meeple can be to put on this tile*/
            if (tile.isMeepable() == false) {
                throw new Exception("no meepable");
            }
            else {
                type.setMeeple(meeple);
                meeple.setIsUsed(true);
            }
        }

    }

    public Board getBoard()
    {
        return this.board;

    }

    //TODO
    // REMOVE !! Please laissez pour mes tests :'(
    public AbstractTile drawFromPileIndex(int i)
    {
        return this.pile.remove(i);
    }

    /**
     * Used to complete the actions of the tile that has been drawn
     *
     */
    private void refreshPlacements()
    {
        this.placements.clear();
        if (this.currentTile != null) {
            this.placements = this.board.getTilePossiblePlacements(this.currentTile);
        }
    }

    @Override
    public void notifyObservers()
    {
        super.setChanged();
        super.notifyObservers(this.notifyMessage);
    }

    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
        this.notifyBoardChanged();

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
        return this.board.canTileBePlacedHere(coordinates, tile);
    }

    /**
     * Check if the current tile can be placed here
     *
     * @param coordinates
     * @return
     */
    public boolean checkTilePosition(Coord coordinates)
    {
        return this.checkTilePosition(coordinates, this.currentTile);
    }

    /**
     * Notifies the observers when the board has changed with the right message
     */
    public void notifyBoardChanged()
    {
        this.notifyMessage = "boardChanged";
        this.notifyObservers();
    }

    public AbstractTile getCurrentTile()
    {
        return currentTile;
    }

    public ArrayList<Coord> getPlacements()
    {
        return placements;
    }

    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    public int getPileSize()
    {
        return this.pile.size();
    }

    private void manageNewTileAggregates(AbstractTile tile, int col, int row)
    {
        //Get all the different aggregates of the tile
        Set<Set<String>> roadAggregatesEmplacements = tile.getRoadAggregateEmplacements();
        Set<Set<String>> cityAggregatesEmplacements = tile.getCityAggregateEmplacements();
        Set<Set<String>> fieldAggregatesEmplacements = tile.getFieldAggregateEmplacements();

        /**
         * A la fin : *
         */
        for (Set<String> currentRoadEmplacement : roadAggregatesEmplacements) {
            roadAggregates.add(new RoadAggregate(col, row, tile, currentRoadEmplacement));
        }

        Set<CityAggregate> tileCities = new HashSet<>();
        for (Set<String> currentCityEmplacement : cityAggregatesEmplacements) {
            CityAggregate newCity = new CityAggregate(col, row, tile, currentCityEmplacement);
            cityAggregates.add(newCity);
            tileCities.add(newCity);
        }

        for (Set<String> currentFieldEmplacement : fieldAggregatesEmplacements) {
            FieldAggregate newField = new FieldAggregate(col, row, tile, currentFieldEmplacement, tileCities);
            fieldAggregates.add(newField);
        }
    }

}
