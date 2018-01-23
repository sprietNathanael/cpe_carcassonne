/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import RessourcesGlobalVariables.PlayerTypes;
import static carcassonne.model.aggregate.AggregatesEnum.ROAD;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.RoadType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Étienne
 */
public class RoadAggregate extends AbstractAggregate implements Serializable
{

    private boolean hasInn;
    private Map<Coord, Set<RoadEdgeEnum>> roadEdges;

    public Map<Coord, Set<RoadEdgeEnum>> getCityEdges()
    {
        return roadEdges;
    }

    /**
     * Get the edge enum from a string of a border location
     *
     * @param locationTypes
     * @return
     */
    private static Set<RoadEdgeEnum> getRoadEdges(Set<String> locationTypes)
    {
        Set<RoadEdgeEnum> roadEdges = new HashSet<>();

        if (locationTypes.contains("N")) {
            roadEdges.add(RoadEdgeEnum.NORTH);
        }
        if (locationTypes.contains("E")) {
            roadEdges.add(RoadEdgeEnum.EAST);
        }
        if (locationTypes.contains("S")) {
            roadEdges.add(RoadEdgeEnum.SOUTH);
        }
        if (locationTypes.contains("W")) {
            roadEdges.add(RoadEdgeEnum.WEST);
        }

        return roadEdges;
    }

    /**
     * Construct a road aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes
     */
    public RoadAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(col, row, firstTile, locationTypes);
        this.type = ROAD;
        roadEdges = new HashMap<>();
        //We add the edge(s) that will need to be closed to complete the aggregate
        roadEdges.put(new Coord(col, row), getRoadEdges(locationTypes));
        hasInn = aggregateHasInn(firstTile, locationTypes);
    }

    /**
     * Construct a road aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes
     * @param player
     * @param meeple
     */
    public RoadAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes, Player player, Meeple meeple)
    {
        super(col, row, firstTile, locationTypes, player, meeple);
        roadEdges = new HashMap<>();
        //We add the edge(s) that will need to be closed to complete the aggregate
        roadEdges.put(new Coord(col, row), getRoadEdges(locationTypes));
        hasInn = aggregateHasInn(firstTile, locationTypes);
    }

    /**
     * Add a new tile to the aggregate, we test if there is an loop or an
     * extremity road
     *
     * @param row
     * @param col
     * @param newTile
     * @param locationTypes
     */
    @Override
    public void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes)
    {
        hasInn = hasInn || aggregateHasInn(newTile, locationTypes);
        //We get the road edges of this new tile; using the list of location's tile composing the aggregate
        Set<RoadEdgeEnum> currentTileEdges = getRoadEdges(locationTypes);
        List<RoadEdgeEnum> completedEdges = new ArrayList<>();
        //For each edges, we set the coord of its neighbor
        currentTileEdges.forEach((RoadEdgeEnum roadEdge) -> {
            int neighborCol = col, neighborRow = row;
            switch (roadEdge) {
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
            Set<RoadEdgeEnum> neighborTileEdges = roadEdges.get(new Coord(neighborCol, neighborRow));
            RoadEdgeEnum neighborTileEdge = RoadEdgeEnum.getOpposite(roadEdge);
            //If the neighbor has an incompleted edge that match the current edge, this edge is now completed
            if (neighborTileEdges != null) {
                if (neighborTileEdges.contains(neighborTileEdge)) {
                    //Update the list of the edges that have been completed
                    completedEdges.add(roadEdge);
                    //Delete the needed edge of the neighbor tile
                    neighborTileEdges.remove(neighborTileEdge);
                    if (neighborTileEdges.isEmpty()) {
                        //Remove the set if there is no more edges for this neighbor tile
                        roadEdges.remove(new Coord(neighborCol, neighborRow));
                    }
                    else {
                        //Update the set if there is still edges for this neighbor tile
                        roadEdges.put(new Coord(neighborCol, neighborRow), neighborTileEdges);
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
            roadEdges.put(new Coord(col, row), currentTileEdges);
        }

        super.enlargeAggregate(col, row, newTile, locationTypes);
    }

    /**
     * Merge two aggregations and update the meeples and players informations
     *
     * @param neighborAggregate
     */
    public void merge(RoadAggregate neighborAggregate)
    {
        super.merge(neighborAggregate);
        this.roadEdges = mergeCityEdgesSet(neighborAggregate.getCityEdges(), this.roadEdges);
        cleanRoadEdgesMap();
        hasInn = (this.hasInn || neighborAggregate.hasInn);
    }

    /**
     * If there is two extremities in the road, that means this one is complete
     *
     * @return True if the aggregation is completed
     */
    @Override
    public boolean checkIsCompleted()
    {
        if (!isCompleted && roadEdges.isEmpty()) {
            isCompleted = true;
        }

        return isCompleted;
    }

    /**
     * Merge a specific map
     *
     * @param map1
     * @param map2
     * @return
     */
    public static Map<Coord, Set<RoadEdgeEnum>> mergeCityEdgesSet(Map<Coord, Set<RoadEdgeEnum>> map1, Map<Coord, Set<RoadEdgeEnum>> map2)
    {
        map1.forEach((key1, value1) -> {
            map2.merge(key1, value1, (key2, value2) -> key2).addAll(value1);
        });

        return map2;
    }

    /**
     * Manage the cases where a map of roadedges references already completed
     * edges
     */
    @SuppressWarnings("unchecked")
    public void cleanRoadEdgesMap()
    {
        Coord currentCoord, neighborCoord;
        Set<RoadEdgeEnum> currentEdges;
        RoadEdgeEnum neighborEdge;

        Map<Coord, Set<RoadEdgeEnum>> updatedCityEdges;
        updatedCityEdges = new HashMap<>();
        Set<RoadEdgeEnum> updatedCurrentEdges;

        for (Map.Entry currentLocalisation : roadEdges.entrySet()) {
            //Get the current data
            currentCoord = (Coord) currentLocalisation.getKey();
            currentEdges = (Set<RoadEdgeEnum>) currentLocalisation.getValue();
            //By default the neighbor are similar to the current coord, the enum will change it
            neighborCoord = new Coord(currentCoord.col, currentCoord.row);
            updatedCurrentEdges = new HashSet<>();
            //For each edge, test if the corresponding edge exists
            for (RoadEdgeEnum edge : currentEdges) {
                neighborEdge = RoadEdgeEnum.getOpposite(edge);
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
                if (!roadEdges.containsKey(neighborCoord)
                        || !roadEdges.get(neighborCoord).contains(neighborEdge)) {
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
                        && (getRoadEdges(neighborTypes).contains(neighborEdge))
                        && updatedCurrentEdges.contains(edge)) {
                    updatedCurrentEdges.remove(edge);
                }
            }
            if (!updatedCurrentEdges.isEmpty()) {
                updatedCityEdges.put(currentCoord, updatedCurrentEdges);
            }
        }
        roadEdges = updatedCityEdges;
    }

    public static void main(String str[]) throws Exception
    {
        //Démarrer jeu
        CarcassonneGame game = new CarcassonneGame();
        //Piocher tuile
        AbstractTile firstTile = game.drawFromPile();

        //Poser la tuile à un index ...
        //Parcourir les emplacements de la tuile (locationType)
        Set<String> locationTypes = new HashSet<>();
        locationTypes.add("S");
        RoadAggregate aggregate = new RoadAggregate(0, 0, firstTile, locationTypes);

        //Fin du tour
        //Tour suivant, avec la pioche d'une pièce compatible, on pose la pièce à 0,-1
        Player player2 = new Player("C'est moi", "yellow", PlayerTypes.player);
        AbstractTile nextTile = game.drawFromPileIndex(9);
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("N");
        locationTypes.add("CNW");
        locationTypes.add("CSW");

        aggregate.enlargeAggregate(0, -1, nextTile, locationTypes);
        if (player2.checkMeepleAvailable()) {
            aggregate.addMeeple(player2, player2.getFirstMeepleAvailable());
        }
    }

    @Override
    public int countPoints()
    {
        int result = 0;
        
        //If this is a road with a meeple, we count 2 points per Tiles if the road is completed, 0 if not
        if (this.hasInn) {
            if (this.checkIsCompleted()) {
                result = aggregatedTiles.size() * 2;
            }
        } else {
            result = aggregatedTiles.size();
        }

        return result;
    }

    @Override
    public String toString()
    {
        return "Road{" + "Tuiles=" + aggregatedTiles.keySet() + "Types" + aggregatedPositionTypes.values() + " Player: " + players + " IsInn: " + hasInn + "}\n";
    }

    private boolean aggregateHasInn(AbstractTile tile, Set<String> locations)
    {
        for (Map.Entry<String, AbstractType> item : tile.getTypes().entrySet()) {
            String key = item.getKey();
            AbstractType value = item.getValue();

            if (value instanceof RoadType && locations.contains(key)) {
                RoadType road = (RoadType) value;
                if (road.hasInn) {
                    return true;
                }
            }
        }

        return false;
    }
}
