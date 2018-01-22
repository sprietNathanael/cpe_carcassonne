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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Deals with the River
 *
 * @author Étienne
 *
 */
public class RiverAggregate extends AbstractAggregate
{

    public RiverAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(col, row, firstTile, locationTypes);
    }

    @Override
    public void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes)
    {
        super.enlargeAggregate(col, row, newTile, locationTypes);
        //If the tile is the "RE" one, that means the player has closed the river
        if (newTile.getId().equals("RE")) {
            isCompleted = true;
        }
    }

    @Override
    public boolean checkIsCompleted()
    {
        return isCompleted;
    }

    public Coord getNextPositionTile()
    {
        Coord result = null, currentCoord;

        //Parcours de toutes les tuiles
        for (Map.Entry<Coord, AbstractTile> entry : this.aggregatedTiles.entrySet()) {
            Coord neighbordCoord = new Coord(entry.getKey().col, entry.getKey().row);
            AbstractTile tile = entry.getValue();

            //Récupère les locations des bords de la tuile 
            Set<String> locations = filterEdgeLocations(this.aggregatedPositionTypes.get(tile));

            //Parcours de ces locations
            for (String location : locations) {
                //Coord corespondant à cette location
                currentCoord = getCoordFromLocation(location, neighbordCoord);
                if (!this.aggregatedTiles.containsKey(currentCoord)) {
                    result = currentCoord;
                    break;
                }
            }
            if (result != null) {
                break;
            }
        }

        return result;
    }

    @Override
    public Set<Player> getWinningPlayers()
    {
        return null;
    }

    @Override
    public int getBiggestPoints()
    {
        return -1;
    }

    @Override
    protected void merge(AbstractAggregate neighborAggregate
    )
    {

    }

    @Override
    public Map<Player, Set<Meeple>> getPlayers()
    {
        return null;
    }

    @Override
    public boolean addMeeple(Player player, Meeple meeple)
    {
        return false;
    }

    @Override
    public int countPoints()
    {
        return -1;
    }

    @Override
    public String toString()
    {
        return "River{" + "aggregatedTiles=" + aggregatedTiles + ", aggregatedPositionTypes=" + aggregatedPositionTypes + ", players=" + players + ", isCompleted=" + isCompleted + "\n}";
    }

    /**
     * Get only the locations of the river that is at an edge of a tile
     * @param locations
     * @return 
     */
    private static Set<String> filterEdgeLocations(Set<String> locations)
    {
        Set<String> result = new HashSet<>();

        for (String location : locations) {
            if (location.equals("N")
                    || location.equals("E")
                    || location.equals("S")
                    || location.equals("W")) {
                result.add(location);
            }
        }

        return result;
    }

    /**
     * Get neighbor coord, using current coord and location
     * @param location
     * @param neighborCoord
     * @return 
     */
    private static Coord getCoordFromLocation(String location, Coord neighborCoord)
    {
        Coord coord = new Coord(neighborCoord.col, neighborCoord.row);
        switch (location) {
            case "N":
                coord.row++;
                break;
            case "S":
                coord.row--;
                break;
            case "E":
                coord.col++;
                break;
            case "W":
                coord.col--;
                break;
        }

        return coord;
    }

}
