/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Étienne
 */
public abstract class AbstractAggregate
{

    protected Map<Coord, AbstractTile> aggregatedTiles;
    protected Map<AbstractTile, Set<String>> aggregatedPositionTypes;
    protected Map<Player, Set<Meeple>> players;
    protected boolean isCompleted;

    /**
     * Construct a new aggregate
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes Set of all the new tile types that compose the
     * aggregation
     */
    protected AbstractAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        this.aggregatedTiles = new HashMap();
        this.aggregatedTiles.put(new Coord(col, row), firstTile);

        this.aggregatedPositionTypes = new HashMap();
        this.aggregatedPositionTypes.put(firstTile, locationTypes);

        this.players = new HashMap();

        this.isCompleted = false;
    }

    /**
     * Construct a new aggregate, affected to a new player using the meeple he
     * just placed
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes Set of all the new tile types that compose the
     * aggregation
     * @param player
     * @param meeple
     */
    protected AbstractAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes, Player player, Meeple meeple)
    {
        this.aggregatedTiles = new HashMap();
        this.aggregatedTiles.put(new Coord(col, row), firstTile);

        this.aggregatedPositionTypes = new HashMap();
        this.aggregatedPositionTypes.put(firstTile, locationTypes);

        Set<Meeple> meeples = new HashSet();
        meeples.add(meeple);
        this.players = new HashMap();
        this.players.put(player, meeples);

        this.isCompleted = false;
    }

    /**
     * Add a Meeple to this aggregate. If this aggregate is already affected to
     * another player, the player can't add its own meeple on this aggregate
     *
     * @param player
     * @param meeple number of the new meeple value
     * @return true if a meeple has been added
     */
    public boolean addMeeple(Player player, Meeple meeple)
    {
        boolean result = false;

        if (players.isEmpty()) {
            Set<Meeple> meeples = new HashSet();
            meeples.add(meeple);
            players.put(player, meeples);
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
    public Set<Coord> getNeighboredCoordinates(int col, int row)
    {
        /**
         * Coordinates of the current aggregate tiles that are neighbored with
         * the requested emplacement
         */
        Set<Coord> neighboredTilesLocation;
        neighboredTilesLocation = new HashSet<>();

        /**
         * We test all the possible neighbored locations, and add every
         * locations where this current aggregation is present
         */
        if (aggregatedTiles.containsKey(new Coord(col - 1, row))) {
            neighboredTilesLocation.add(new Coord(col - 1, row));
        }
        if (aggregatedTiles.containsKey(new Coord(col + 1, row))) {
            neighboredTilesLocation.add(new Coord(col + 1, row));
        }
        if (aggregatedTiles.containsKey(new Coord(col, row - 1))) {
            neighboredTilesLocation.add(new Coord(col, row - 1));
        }
        if (aggregatedTiles.containsKey(new Coord(col, row + 1))) {
            neighboredTilesLocation.add(new Coord(col, row + 1));
        }

        return neighboredTilesLocation;
    }

    /**
     * Add a new tile to the aggregate
     *
     * @param row
     * @param col
     * @param newTile
     * @param locationTypes
     */
    protected void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes)
    {
        aggregatedTiles.put(new Coord(col, row), newTile);
        aggregatedPositionTypes.put(newTile, locationTypes);
    }

    public Map<Coord, AbstractTile> getAggregatedTiles()
    {
        return aggregatedTiles;
    }

    public Map<AbstractTile, Set<String>> getAggregatedTypes()
    {
        return aggregatedPositionTypes;
    }

    public Map<Player, Set<Meeple>> getPlayers()
    {
        return players;
    }

    /**
     * Merge two aggregations and update the meeples and players informations
     *
     * @param neighborAggregate
     */
    protected void merge(AbstractAggregate neighborAggregate)
    {
        //First we add the new tiles to the aggregation
        aggregatedTiles.putAll(neighborAggregate.getAggregatedTiles());

        Map<AbstractTile, Set<String>> newPositionTypes = mergeLocationTypesSet(aggregatedPositionTypes, neighborAggregate.getAggregatedTypes());
        aggregatedPositionTypes.putAll(newPositionTypes);

        //Get the common players of these two aggregates
        players = mergeMeeplesSet(players, neighborAggregate.getPlayers());
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
        Set<Player> winningPlayers = new HashSet<>();

        winningPlayers = new HashSet<>();
        for (Player player1 : iterator1) {
            //Test if we can add the player 1 in the other collection, if not that means it is already present in it
            if (!playersSet2.add(player1)) {
                winningPlayers.add(player1);
            }
        }

        return winningPlayers;
    }

    protected static Map<Player, Set<Meeple>> mergeMeeplesSet(Map<Player, Set<Meeple>> map1, Map<Player, Set<Meeple>> map2)
    {
        map1.forEach((key1, value1) -> {
            map2.merge(key1, value1, (key2, value2) -> key2).addAll(value1);
        });

        return map2;
    }

    protected static Map<AbstractTile, Set<String>> mergeLocationTypesSet(Map<AbstractTile, Set<String>> map1, Map<AbstractTile, Set<String>> map2)
    {
        map1.forEach((key1, value1) -> {
            map2.merge(key1, value1, (key2, value2) -> key2).addAll(value1);
        });

        return map2;
    }

    /**
     * Set the aggregation to "completed" if the aggregation is closed
     *
     * @return true if it is completed
     */
    public abstract boolean checkIsCompleted();

    @Override
    public String toString()
    {
        return "AbstractAggregate{" + "aggregatedTiles=" + aggregatedTiles + ", aggregatedPositionTypes=" + aggregatedPositionTypes + ", players=" + players + ", isCompleted=" + isCompleted + '}';
    }

    public int getBiggestPoints()
    {
        int max = 0, current;
        for (Map.Entry player : players.entrySet()) {
            current = Player.countPoints((Set<Meeple>) player.getValue());
            if (current > max) {
                max = current;
            }
        }

        return max;
    }

    public Set<Player> getWinningPlayers()
    {
        Set<Player> winningPlayers = new HashSet();
        int maxNumber = this.getBiggestPoints(), currentNumber;

        for (Map.Entry player : players.entrySet()) {
            System.out.println(player.getKey());
            currentNumber = Player.countPoints((Set<Meeple>) player.getValue());
            if (currentNumber == maxNumber) {
                winningPlayers.add((Player) player.getKey());
            }
        }

        return winningPlayers;
    }
;
}
