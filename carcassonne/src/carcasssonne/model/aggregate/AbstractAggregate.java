/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcasssonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Étienne
 */
public abstract class AbstractAggregate
{

    protected HashMap<Coord, AbstractTile> aggregatedTiles;
    protected Set<Player> players;
    protected int meepleNumber;

    /**
     * Construct a new aggregate
     *
     * @param row
     * @param col
     * @param firstTile
     */
    public AbstractAggregate(int row, int col, AbstractTile firstTile)
    {
        this.aggregatedTiles = new HashMap();
        this.aggregatedTiles.put(new Coord(row, col), firstTile);
        this.players = new HashSet();
    }

    /**
     * Construct a new aggregate, affected to a new player using a meeple
     *
     * @param row
     * @param col
     * @param firstTile
     * @param player
     * @param meeples
     */
    public AbstractAggregate(int row, int col, AbstractTile firstTile, Player player, int meeples)
    {
        this.aggregatedTiles = new HashMap();
        this.aggregatedTiles.put(new Coord(row, col), firstTile);
        this.players.add(player);
        this.meepleNumber = meeples;
    }

    /**
     * Add a Meeple to this aggregate. If this aggregate is already affected to
     * another player, the player can't add its own meeple on this aggregate
     *
     * @param number number of meeple added
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
     * Test if the tile at the given coordinates has a neighbor with this
     * aggregate.
     *
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isNeighbor(int row, int col)
    {
        boolean result = false;
        if (aggregatedTiles.containsKey(new Coord(row - 1, col))
                || aggregatedTiles.containsKey(new Coord(row + 1, col))
                || aggregatedTiles.containsKey(new Coord(row, col - 1))
                || aggregatedTiles.containsKey(new Coord(row, col + 1))) {
            result = true;
        }
        return result;
    }

    /**
     * Add a new tile to the aggregate
     *
     * @param row
     * @param col
     * @param newTile null if the new tile coordinates was not already in the
     * aggregate
     * @return true if the aggregate has been successfully enlarged
     */
    public boolean enlargeAggregate(int row, int col, AbstractTile newTile)
    {
        boolean result = false;

        if (this.isNeighbor(row, col)) {
            aggregatedTiles.put(new Coord(row, col), newTile);
            result = true;
        }

        return result;
    }

    /**
     * Check if an aggregate neighbor has the same type and can be merged
     *
     * @param row
     * @param col
     * @param neighborAggregate
     * @return boolean
     */
    public abstract boolean areSameType(int row, int col, AbstractAggregate neighborAggregate);

    public HashMap<Coord, AbstractTile> getAggregatedTiles()
    {
        return aggregatedTiles;
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

        //Get the common players of these two aggregates
        HashSet<Player> winningPlayers = getCommonPlayers(this.players, neighborAggregate.getPlayers());

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
    protected static HashSet<Player> getCommonPlayers(Set<Player> playersSet1, Set<Player> playersSet2)
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
}
