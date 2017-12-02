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
import carcassonne.model.type.AbbayType;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.CityType;
import carcassonne.model.type.CrossType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Étienne
 */
public class RoadAggregate extends AbstractAggregate
{

    private int roadExtremities;

    public int getRoadExtremities()
    {
        return roadExtremities;
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
        if (isAnExtremity(firstTile)) {
            roadExtremities = 1;
        }
        else {
            roadExtremities = 0;
        }
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
        if (isAnExtremity(firstTile)) {
            roadExtremities = 1;
        }
        else {
            roadExtremities = 0;
        }
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
        super.enlargeAggregate(col, row, newTile, locationTypes);

        if (isAnExtremity(newTile)) {
            roadExtremities++;
        }

        //To manage the loop possibilities, we call the corresponding method each time the aggregate has a neighbor
        for (String location : locationTypes) {
            //For each case, we check if the suposibly neighbor exists into this aggregate
            switch (location) {
                case "N":
                    this.manageLoopRoad(col, row + 1, "S");
                    break;
                case "E":
                    this.manageLoopRoad(col + 1, row, "W");
                    break;
                case "S":
                    this.manageLoopRoad(col, row - 1, "N");
                    break;
                case "W":
                    this.manageLoopRoad(col - 1, row, "E");
                    break;
            }
        }
    }

    /**
     * Merge two aggregations and update the meeples and players informations
     *
     * @param neighborAggregate
     */
    public void merge(RoadAggregate neighborAggregate)
    {
        super.merge(neighborAggregate);
        roadExtremities += neighborAggregate.getRoadExtremities();
    }

    /**
     * Complete the road in case of a loop into a crossroad: when the beginning
     * and the ending of a road is in the same tile
     *
     * @param col the possible x position of the loop tile
     * @param row the possible y position of the loop tile
     * @param locationType location of the last segment road ("S", "N", "E" or
     * "W")
     */
    public void manageLoopRoad(int col, int row, String locationType)
    {
        Coord coord = new Coord(col, row);

        //Check if the tile already exists in the aggregate
        if (aggregatedTiles.containsKey(coord)) {
            AbstractTile loopTile = aggregatedTiles.get(coord);
            if (isAnExtremity(loopTile)) {
                Set<String> locationsTypes = aggregatedPositionTypes.get(loopTile);
                //If the tile doesn't contain the type, it means it is a loop and we need to add it
                if (!locationsTypes.contains(locationType)) {
                    aggregatedPositionTypes.put(loopTile, locationsTypes);
                    roadExtremities++;
                }
            }
        }
    }

    /**
     * If there is two extremities in the road, that means this one is complete
     *
     * @return True if the aggregation is completed
     */
    @Override
    public boolean checkIsCompleted()
    {
        boolean result = false;

        if (roadExtremities == 2) {
            result = true;
        }

        return result;
    }

    public static void main(String str[])
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
        Player player2 = new Player("C'est moi", Color.yellow);
        AbstractTile nextTile = game.drawFromPileIndex(9);
        locationTypes = new HashSet<>();
        locationTypes.add("S");
        locationTypes.add("N");
        locationTypes.add("CNW");
        locationTypes.add("CSW");

        System.out.println("La nouvelle pièce est voisine de l'aggrégat de test avec cette liste de coordonnées : \n" + aggregate.getNeighboredCoordinates(0, -1));
        aggregate.enlargeAggregate(0, -1, nextTile, locationTypes);
        System.out.println("L'aggrégat est maintenant composé de deux tuiles :");
        System.out.println(aggregate.aggregatedTiles);
        if (player2.checkMeepleAvailable()) {
            aggregate.addMeeple(player2, player2.getFirstMeepleAvailable());
        }
        System.out.println(aggregate.players);
    }

    /**
     * Check if the tile given is an extremity of a road
     *
     * @param newTile
     * @return
     */
    private static boolean isAnExtremity(AbstractTile newTile)
    {
        boolean result = false;
        List<AbstractType> centerTypes = new ArrayList<>();

        //We get the 4 types of the center of the tile, which represent the end of a road
        centerTypes.add(newTile.getType("CNW"));
        centerTypes.add(newTile.getType("CNE"));
        centerTypes.add(newTile.getType("CSE"));
        centerTypes.add(newTile.getType("CSW"));

        for (AbstractType type : centerTypes) {
            if (type instanceof CrossType
                    || type instanceof CityType
                    || type instanceof AbbayType) {
                result = true;
                break;
            }
        }

        return result;
    }
}
