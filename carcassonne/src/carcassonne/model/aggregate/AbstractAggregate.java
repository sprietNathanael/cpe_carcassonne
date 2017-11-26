/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.type.AbstractType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Étienne
 */
public abstract class AbstractAggregate
{

    protected Map<Coord, AbstractTile> aggregatedTiles;
    protected Map<AbstractTile, List<String>> aggregatedPositionTypes;
    protected Set<Player> players;
    protected int meepleNumber;
    protected boolean isCompleted;

    /**
     * Construct a new aggregate
     *
     * @param row
     * @param col
     * @param firstTile
     * @param types List of all the new tile types that compose the aggregation
     */
    public AbstractAggregate(int row, int col, AbstractTile firstTile, List<String> types)
    {
        this.aggregatedTiles = new HashMap();
        this.aggregatedTiles.put(new Coord(row, col), firstTile);

        this.aggregatedPositionTypes = new HashMap();
        this.aggregatedPositionTypes.put(firstTile, types);

        this.players = new HashSet();
        this.meepleNumber = 0;

        this.isCompleted = false;
    }

    /**
     * Construct a new aggregate, affected to a new player using the meeple he
     * just placed
     *
     * @param row
     * @param col
     * @param firstTile
     * @param types List of all the new tile types that compose the aggregation
     * @param player
     * @param meepleValue
     */
    public AbstractAggregate(int row, int col, AbstractTile firstTile, List<String> types, Player player, int meepleValue)
    {
        this.aggregatedTiles = new HashMap();
        this.aggregatedTiles.put(new Coord(row, col), firstTile);

        this.aggregatedPositionTypes = new HashMap();
        this.aggregatedPositionTypes.put(firstTile, types);

        this.players = new HashSet();
        this.players.add(player);

        this.meepleNumber = meepleValue;
        this.isCompleted = false;
    }

    /**
     * Add a Meeple to this aggregate. If this aggregate is already affected to
     * another player, the player can't add its own meeple on this aggregate
     *
     * @param number number of the new meeple value
     * @param player
     * @return true if a meeple has been added
     */
    public boolean addMeeple(int number, Player player)
    {
        boolean result = false;

        if (player == null) {
            this.players.add(player);
            this.meepleNumber += number;
            result = true;
        }
        return result;
    }

    /**
     * Get the tile emplacements that are neighbored with those coordinates. Use
     * this method to test every aggregate, to check which aggregate we have to
     * enlarge
     *
     * @param row of the new emplacement to test
     * @param col of the new emplacement to test
     * @return List the coordinates of the neighbored emplacement, return
     * [-1;-1] if the aggregate dosen't
     */
    public List<Coord> getNeighboredCoordinates(int row, int col)
    {
        /**
         * Coordinates of the current aggregate tiles that are neighbored with
         * the requested emplacement
         */
        List<Coord> neighboredTilesLocation;
        neighboredTilesLocation = new ArrayList<>();

        /**
         * We test all the possible neighbored locations, and add every
         * locations where this current aggregation is present
         */
        if (aggregatedTiles.containsKey(new Coord(row - 1, col))) {
            neighboredTilesLocation.add(new Coord(row - 1, col));
        }
        if (aggregatedTiles.containsKey(new Coord(row + 1, col))) {
            neighboredTilesLocation.add(new Coord(row + 1, col));
        }
        if (aggregatedTiles.containsKey(new Coord(row, col - 1))) {
            neighboredTilesLocation.add(new Coord(row, col - 1));
        }
        if (aggregatedTiles.containsKey(new Coord(row, col + 1))) {
            neighboredTilesLocation.add(new Coord(row, col + 1));
        }

        return neighboredTilesLocation;
    }

    /**
     * Add a new tile to the aggregate
     *
     * @param row
     * @param col
     * @param newTile
     * @param types
     */
    public void enlargeAggregate(int row, int col, AbstractTile newTile, List<String> types)
    {
        aggregatedTiles.put(new Coord(row, col), newTile);
        aggregatedPositionTypes.put(newTile, types);
    }

    public Map<Coord, AbstractTile> getAggregatedTiles()
    {
        return aggregatedTiles;
    }

    public Map<AbstractTile, List<String>> getAggregatedTypes()
    {
        return aggregatedPositionTypes;
    }

    public Set<Player> getPlayers()
    {
        return players;
    }

    public int getMeepleNumber()
    {
        return meepleNumber;
    }

    /**
     * Merge two aggregations and update the meeples and players informations
     *
     * @param neighborAggregate
     */
    public void merge(AbstractAggregate neighborAggregate)
    {
        //First we add the new tiles to the aggregation
        aggregatedTiles.putAll(neighborAggregate.getAggregatedTiles());
        aggregatedPositionTypes.putAll(neighborAggregate.getAggregatedTypes());

        //Get the common players of these two aggregates
        Set<Player> winningPlayers = getCommonPlayers(this.players, neighborAggregate.getPlayers());

        //Case if there are common player(s) in these two aggregate
        if (winningPlayers.isEmpty() == false) {
            //Meeples number added up using the neighbor aggregate
            this.meepleNumber += neighborAggregate.getMeepleNumber();
            //Players updated using the winning players of these two aggregates
            this.players = winningPlayers;
        }
        //Case if the players of the neighbor aggregate have more meeples than the players of the current one
        else if (this.meepleNumber < neighborAggregate.getMeepleNumber()) {
            //The neighbor aggregate players are now the winning players
            this.players = neighborAggregate.getPlayers();
            //The meeplenumber corresponds to the winning players, e.i. the neighbor aggregate
            this.meepleNumber = neighborAggregate.getMeepleNumber();
        }
    }

    /**
     * Get the similar players of two Set of players, used to get the winning
     * players when there is a fusion of two aggregations
     *
     * @param playersSet1
     * @param playersSet2
     * @return winnigPlayers
     */
    protected static Set<Player> getCommonPlayers(Set<Player> playersSet1, Set<Player> playersSet2)
    {
        Iterable<Player> iterator1 = playersSet1;
        Iterable<Player> iterator2 = playersSet2;
        HashSet<Player> winningPlayers;

        winningPlayers = new HashSet<>();
        for (Player player1 : iterator1) {
            for (Player player2 : iterator2) {
                if (player1 == player2) {
                    winningPlayers.add(player1);
                }
            }
        }

        return winningPlayers;
    }

    /**
     * Set the aggregation to "completed" if the aggregation is closed
     *
     * @return true if it is completed
     */
    public abstract boolean checkIsCompleted();
}