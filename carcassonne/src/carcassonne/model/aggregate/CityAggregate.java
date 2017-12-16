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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
        List<CityEdgeEnum> completedEdges = new ArrayList<>();
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

            //If the neighbor has an incompleted edge that match the current edge, this edge is now completed
            if (neighborTileEdges != null) {
                if (neighborTileEdges.contains(neighborTileEdge)) {
                    //Update the list of the edges that have been completed
                    completedEdges.add(cityEdge);
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
            }
        });
        //We delete each edges that have been completed
        completedEdges.forEach((completedEdge) -> {
            currentTileEdges.remove(completedEdge);
        });
        if (!currentTileEdges.isEmpty()) {
            //If there is still incompleted edges on this current tile, we put them
            cityEdges.put(new Coord(col, row), currentTileEdges);
        }

        super.enlargeAggregate(col, row, newTile, locationTypes);
    }

    public void merge(CityAggregate neighborAggregate)
    {
        super.merge(neighborAggregate);
        this.cityEdges = mergeCityEdgesSet(neighborAggregate.getCityEdges(), this.cityEdges);
        cleanCityEdgesMap();
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

    /**
     * Get the edge enum from a string of a border location
     *
     * @param locationTypes
     * @return
     */
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

    /**
     * Merge a specific map
     *
     * @param map1
     * @param map2
     * @return
     */
    public static Map<Coord, Set<CityEdgeEnum>> mergeCityEdgesSet(Map<Coord, Set<CityEdgeEnum>> map1, Map<Coord, Set<CityEdgeEnum>> map2)
    {
        map1.forEach((key1, value1) -> {
            map2.merge(key1, value1, (key2, value2) -> key2).addAll(value1);
        });

        return map2;
    }

    /**
     * Manage the cases where a map of cityedges references already completed
     * edges
     */
    public void cleanCityEdgesMap()
    {
        Coord currentCoord, neighborCoord;
        Set<CityEdgeEnum> currentEdges;
        CityEdgeEnum neighborEdge;

        Map<Coord, Set<CityEdgeEnum>> updatedCityEdges;
        updatedCityEdges = new HashMap();
        Set<CityEdgeEnum> updatedCurrentEdges;

        for (Map.Entry currentLocalisation : cityEdges.entrySet()) {
            //Get the current data
            currentCoord = (Coord) currentLocalisation.getKey();
            currentEdges = (Set<CityEdgeEnum>) currentLocalisation.getValue();
            //By default the neighbor are similar to the current coord, the enum will change it
            neighborCoord = new Coord(currentCoord.col, currentCoord.row);
            System.out.println("Test first: ");
            System.out.println("Coord: " + currentCoord);
            System.out.println("Edges" + currentEdges);
            updatedCurrentEdges = new HashSet();
            //For each edge, test if the corresponding edge exists
            for (CityEdgeEnum edge : currentEdges) {
                neighborEdge = CityEdgeEnum.getOpposite(edge);
                switch (edge) {
                    case NORTH:
                        neighborCoord.row++;
                        break;
                    case EAST:
                        neighborCoord.col++;
                        break;
                    case SOUTH:
                        neighborCoord.row--;
                        break;
                    case WEST:
                        neighborCoord.col--;
                        break;
                }
                if (!cityEdges.containsKey(neighborCoord)
                        || !cityEdges.get(neighborCoord).contains(neighborEdge)) {
                    System.out.println("Test loop: ");
                    System.out.println("Coord: " + currentCoord + "  Neighbor: " + neighborCoord);
                    updatedCurrentEdges.add(edge);
                }
            }
            if (!updatedCurrentEdges.isEmpty()) {
                updatedCityEdges.put(currentCoord, updatedCurrentEdges);
            }
        }
        cityEdges = updatedCityEdges;
    }
}
