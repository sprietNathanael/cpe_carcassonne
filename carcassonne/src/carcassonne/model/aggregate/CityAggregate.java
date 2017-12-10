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
 * @author Étienne
 */
public class CityAggregate extends AbstractAggregate
{

    private Map<Coord, Set<CityEdgeEnum>> cityEdges;

    public Map<Coord, Set<CityEdgeEnum>> getCityEdges()
    {
        return cityEdges;
    }

    /**
     * Construct a city aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes
     */
    public CityAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(col, row, firstTile, locationTypes);
        cityEdges = new HashMap<>();
        //We add the edge(s) that will need to be closed to complete the aggregate
        cityEdges.put(new Coord(col, row), getCityEdges(locationTypes));
    }

    /**
     * Construct a city aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes
     * @param player
     * @param meeple
     */
    public CityAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes, Player player, Meeple meeple)
    {
        super(col, row, firstTile, locationTypes, player, meeple);
        cityEdges = new HashMap<>();
        //We add the edge(s) that will need to be closed to complete the aggregate
        cityEdges.put(new Coord(col, row), getCityEdges(locationTypes));
    }

    /**
     * Enlarge the aggregate and update the edges that have to be closed
     *
     * @param col
     * @param row
     * @param newTile
     * @param locationTypes
     */
    @Override
    public void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes)
    {
        //We get the city edges of this new tile; using the list of location's tile composing the aggregate
        Set<CityEdgeEnum> currentTileEdges = getCityEdges(locationTypes);
        //For each edges, we set the coord of its neighbor
        currentTileEdges.forEach((CityEdgeEnum cityEdge) -> {
            int neighborCol = col, neighborRow = row;
            switch (cityEdge) {
                case NORTH:
                    neighborRow = row + 1;
                    break;
                case EAST:
                    neighborCol = col + 1;
                    break;
                case SOUTH:
                    neighborRow = row - 1;
                    break;
                case WEST:
                    neighborCol = col - 1;
                    break;
            }
            //We then get the current neighbor
            Set<CityEdgeEnum> neighborTileEdges = cityEdges.get(new Coord(neighborCol, neighborRow));
            CityEdgeEnum neighborTileEdge = CityEdgeEnum.getOpposite(cityEdge);

            //If the neighbor has an edge incomplete, we delete it 
            if (neighborTileEdges != null && neighborTileEdges.contains(neighborTileEdge)) {
                //Delete the needed edge of the current tile
                currentTileEdges.remove(cityEdge);
                //Delete the needed edge of the neighbor tile
                neighborTileEdges.remove(neighborTileEdge);
                if (neighborTileEdges.isEmpty()) {
                    //Remove the set if there is no more edges for this neighbor tile
                    cityEdges.remove(new Coord(neighborCol, neighborRow));
                }
                else {
                    //Update the set if there is still edges for this neighbor tile
                    cityEdges.put(new Coord(neighborCol, neighborRow), neighborTileEdges);
                }
            }
            if (!currentTileEdges.isEmpty()) {
                //If there is still edges incomplete on this current tile, we put them
                cityEdges.put(new Coord(col, row), currentTileEdges);
            }
        });

        super.enlargeAggregate(col, row, newTile, locationTypes);
    }

    public void merge(CityAggregate neighborAggregate)
    {
        //mergeSet(neighborAggregate.getCityEdges(), cityEdges);
    }

    @Override
    public boolean checkIsCompleted()
    {
        boolean result = false;

        if (cityEdges.isEmpty()) {
            result = true;
        }

        return result;
    }

    private static Set<CityEdgeEnum> getCityEdges(Set<String> locationTypes)
    {
        Set<CityEdgeEnum> cityEdges = new HashSet<>();

        if (locationTypes.contains("N")) {
            cityEdges.add(CityEdgeEnum.NORTH);
        }
        if (locationTypes.contains("E")) {
            cityEdges.add(CityEdgeEnum.EAST);
        }
        if (locationTypes.contains("S")) {
            cityEdges.add(CityEdgeEnum.SOUTH);
        }
        if (locationTypes.contains("W")) {
            cityEdges.add(CityEdgeEnum.WEST);
        }

        return cityEdges;
    }

    public static Map<Coord, Set<CityEdgeEnum>> mergeCityEdgesSet(Map<Coord, Set<CityEdgeEnum>> map1, Map<Coord, Set<CityEdgeEnum>> map2)
    {
        map1.forEach((key1, value1) -> {
            map2.merge(key1, value1, (key2, value2) -> key2).addAll(value1);
        });

        return map2;
    }
}
