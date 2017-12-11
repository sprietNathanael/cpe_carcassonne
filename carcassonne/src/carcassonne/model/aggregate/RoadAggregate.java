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

        //Manage the extremities of the road
        if (isAnExtremity(newTile)) {
            //If the new tile is a cross road with two edges, that means this tile is a loop and the road is completed
            if (newTile.isCrossRoad() && locationTypes.size() == 2) {
                roadExtremities = 2;
            }
            //If it isn't, it is just a regular extremity:
            else {
                roadExtremities++;
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
