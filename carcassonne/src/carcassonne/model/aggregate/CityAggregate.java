/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import static carcassonne.model.aggregate.AggregatesEnum.CITY;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.CityType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Étienne
 */
public class CityAggregate extends AbstractAggregate implements Serializable
{

    private boolean hasCathedral;

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
        this.type = CITY;
        cityEdges = new HashMap<>();
        //We add the edge(s) that will need to be closed to complete the aggregate
        cityEdges.put(new Coord(col, row), getCityEdges(locationTypes));
        hasCathedral = aggregateHasCathedral(firstTile, locationTypes);
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
        hasCathedral = aggregateHasCathedral(firstTile, locationTypes);
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
        hasCathedral = aggregateHasCathedral(newTile, locationTypes);
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
//                        System.out.println("Suppression edge: " + neighborCol + ";" + neighborRow + " : " + cityEdges.get(new Coord(neighborCol, neighborCol)));
                        cityEdges.remove(new Coord(neighborCol, neighborCol));
                    }
                    else {
                        //Update the set if there is still edges for this neighbor tile
//                        System.out.println("Ajout edge: " + neighborCol + ";" + neighborRow + " : " + neighborTileEdges);
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
//            System.out.println("Ajout edge: " + col + ";" + row + " : " + currentTileEdges);
            cityEdges.put(new Coord(col, row), currentTileEdges);
        }

        super.enlargeAggregate(col, row, newTile, locationTypes);
    }

    public void merge(CityAggregate neighborAggregate)
    {
        super.merge(neighborAggregate);
        this.cityEdges = mergeCityEdgesSet(neighborAggregate.getCityEdges(), this.cityEdges);
//        System.out.println("Merge city edges: " + cityEdges);
        cleanCityEdgesMap();
        hasCathedral = (this.hasCathedral || neighborAggregate.hasCathedral);
    }

    @Override
    public boolean checkIsCompleted()
    {
        if (!isCompleted && cityEdges.isEmpty()) {
            isCompleted = true;
        }

        return isCompleted;
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
    @SuppressWarnings("unchecked")
    public void cleanCityEdgesMap()
    {
        Coord currentCoord, neighborCoord;
        Set<CityEdgeEnum> currentEdges;
        CityEdgeEnum neighborEdge;

        Map<Coord, Set<CityEdgeEnum>> updatedCityEdges;
        updatedCityEdges = new HashMap<>();
        Set<CityEdgeEnum> updatedCurrentEdges;

        for (Map.Entry currentLocalisation : cityEdges.entrySet()) {
            //Get the current data
            currentCoord = (Coord) currentLocalisation.getKey();
            currentEdges = (Set<CityEdgeEnum>) currentLocalisation.getValue();
            //By default the neighbor are similar to the current coord, the enum will change it
            neighborCoord = new Coord(currentCoord.col, currentCoord.row);
            updatedCurrentEdges = new HashSet<>();
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
                    updatedCurrentEdges.add(edge);
                }
                /**
                 * Pour tous les voisins incomplets des coordonnés parcourues,
                 * on teste manuellement s'il y a bien un vide après le bord, ou
                 * si ce vide est complété par un city type. Si c'est un
                 * CityType, l'edge est bien complété donc on ne l'ajoute pas
                 */
                Set<String> neighborTypes = this.getAggregatedTypesByCoord(neighborCoord.col, neighborCoord.row);
                if ((neighborTypes != null)
                        && (getCityEdges(neighborTypes).contains(neighborEdge))
                        && updatedCurrentEdges.contains(edge)) {
                    updatedCurrentEdges.remove(edge);
                }
            }
            if (!updatedCurrentEdges.isEmpty()) {
                updatedCityEdges.put(currentCoord, updatedCurrentEdges);
            }
        }
        cityEdges = updatedCityEdges;
    }

    private boolean tileHasShield(AbstractTile tile, Set<String> locations)
    {
        boolean result = false;
        String location;
        Iterator iter = locations.iterator();

        while (iter.hasNext()) {
            location = (String) iter.next();
            AbstractType type = tile.getType(location);
            if (type instanceof CityType) {
                CityType cityType = (CityType) type;
                if (cityType.isShielded()) {
                    return true;
                }
            }

        }

        return result;
    }

    @Override
    public int countPoints()
    {
        int result = 0;
        if (this.checkIsCompleted()) {
            for (AbstractTile tile : aggregatedTiles.values()) {
                if (tileHasShield(tile, aggregatedPositionTypes.get(tile))) {
                    result += 4;
                }
                else {
                    result += 2;
                }
            }
        }
        else {
            result = aggregatedTiles.size();
        }

        return result;
    }

    @Override
    public String toString()
    {
        return "City{" + "Tuiles=" + aggregatedTiles.keySet() + "Types" + aggregatedPositionTypes.values() + " \nEdges: " + this.cityEdges + " IsCath: " + hasCathedral + "}\n";
    }

    public static boolean aggregateHasCathedral(AbstractTile tile, Set<String> locations)
    {
        for (Map.Entry<String, AbstractType> item : tile.getTypes().entrySet()) {
            String key = item.getKey();
            AbstractType value = item.getValue();

            if (value instanceof CityType && locations.contains(key)) {
                CityType city = (CityType) value;
                if (city.hasCathedral) {
                    return true;
                }
            }
        }

        return false;
    }
}
