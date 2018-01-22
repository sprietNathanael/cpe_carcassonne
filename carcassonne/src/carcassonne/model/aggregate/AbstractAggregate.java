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

    /**
     * List of the tiles composing the aggregate
     */
    protected Map<Coord, AbstractTile> aggregatedTiles;
    /**
     * Set of the locations type of each tile
     */
    protected Map<AbstractTile, Set<String>> aggregatedPositionTypes;
    /**
     * List of the players and their meeples
     */
    protected Map<Player, Set<Meeple>> players;
    /**
     * State of the aggregate, can be updated using "checkIsCompleted"
     */
    protected boolean isCompleted;

    public boolean isCompleted()
    {
        return isCompleted;
    }

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
        this.aggregatedTiles = new HashMap<>();
        this.aggregatedTiles.put(new Coord(col, row), firstTile);

        this.aggregatedPositionTypes = new HashMap<>();
        this.aggregatedPositionTypes.put(firstTile, locationTypes);

        this.players = new HashMap<>();

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
        this.aggregatedTiles = new HashMap<>();
        this.aggregatedTiles.put(new Coord(col, row), firstTile);

        this.aggregatedPositionTypes = new HashMap<>();
        this.aggregatedPositionTypes.put(firstTile, locationTypes);

        Set<Meeple> meeples = new HashSet<>();
        meeples.add(meeple);
        this.players = new HashMap<>();
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
            Set<Meeple> meeples = new HashSet<>();
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
        Set<Coord> result = new HashSet<>();

        if (aggregatedTiles.containsKey(new Coord(col - 1, row))) {
            result.add(new Coord(-1, 0));
        }
        if (aggregatedTiles.containsKey(new Coord(col + 1, row))) {
            result.add(new Coord(1, 0));
        }
        if (aggregatedTiles.containsKey(new Coord(col, row - 1))) {
            result.add(new Coord(0, -1));
        }
        if (aggregatedTiles.containsKey(new Coord(col, row + 1))) {
            result.add(new Coord(0, 1));
        }

        return result;
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
        Set<String> updatedLocations = new HashSet<>();
        if (aggregatedPositionTypes.containsKey(newTile)) {
            Set<String> alreadyLocations = aggregatedPositionTypes.get(newTile);

            for (String location : locationTypes) {
                if (alreadyLocations.contains(location)) {
                    updatedLocations.remove(location);
                }
            }
            locationTypes.addAll(alreadyLocations);
        }
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

    /**
     * Gets the types of the tile placed on col, row passed in parameters
     *
     * @param col
     * @param row
     * @return
     */
    public Set<String> getAggregatedTypesByCoord(int col, int row)
    {
        AbstractTile tile = aggregatedTiles.get(new Coord(col, row));

        return aggregatedPositionTypes.get(tile);
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
        for (Player player1 : iterator1) {
            //Test if we can add the player 1 in the other collection, if not that means it is already present in it
            if (!playersSet2.add(player1)) {
                winningPlayers.add(player1);
            }
        }

        return winningPlayers;
    }

    /**
     * Merge specific Map
     *
     * @param map1
     * @param map2
     * @return
     */
    protected static Map<Player, Set<Meeple>> mergeMeeplesSet(Map<Player, Set<Meeple>> map1, Map<Player, Set<Meeple>> map2)
    {
        map1.forEach((key1, value1) -> {
            map2.merge(key1, value1, (key2, value2) -> key2).addAll(value1);
        });

        return map2;
    }

    /**
     * Merge specific Map
     *
     * @param map1
     * @param map2
     * @return
     */
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

    /**
     * Get the biggest number of meeples a player has in this aggregate
     *
     * @return int
     */
    @SuppressWarnings("unchecked")
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

    /**
     * Get the list of the players that are winning the aggregate
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public Set<Player> getWinningPlayers()
    {
        Set<Player> winningPlayers = new HashSet<>();
        int maxNumber = this.getBiggestPoints(), currentNumber;

        for (Map.Entry player : players.entrySet()) {
            currentNumber = Player.countPoints((Set<Meeple>) player.getValue());
            if (currentNumber == maxNumber) {
                winningPlayers.add((Player) player.getKey());
            }
        }

        return winningPlayers;
    }

    /**
     * Count the points of the aggregates
     *
     * @return points
     */
    public abstract int countPoints();

    public boolean isPresentOnTile(int col, int row)
    {
        return aggregatedTiles.containsKey(new Coord(col, row));
    }

    /**
     * Get the locations of the aggregates for given coordinates. Null if there
     * is no locations in those coordinates
     *
     * @param col
     * @param row
     * @return
     */
    public Set<String> getTileLocations(int col, int row)
    {
        Set<String> result = null;

        if (this.isPresentOnTile(col, row)) {
            AbstractTile tile = aggregatedTiles.get(new Coord(col, row));
            result = aggregatedPositionTypes.get(tile);
        }

        return result;
    }

    /**
     * Return true if the aggregate contains the tile
     *
     * @param tile
     * @return
     */
    public boolean contain(AbstractTile tile)
    {
        for (Map.Entry<Coord, AbstractTile> entry : aggregatedTiles.entrySet()) {
            if (entry.getValue() == tile) {
                return true;
            }
        }
        return false;
    }
}
