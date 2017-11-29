/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.carcassonnegame.CarcassonneGame;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Étienne
 */
public class RoadAggregate extends AbstractAggregate
{

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
    }

    /**
     * Complete the road in case of a loop into a crossroad: when the beginning
     * and the ending of a road is in the same tile
     *
     * @param loopTile the similar type tile that just added into the aggregate
     * @param locationType location of the last segment road ("S", "N", "E" or
     * "W")
     * @return true if the road is now complete
     */
    public boolean completeRoad(AbstractTile loopTile, String locationType)
    {
        boolean result = false;

        if (aggregatedPositionTypes.containsKey(loopTile)) {
            Set<String> locationsTypes = this.aggregatedPositionTypes.get(loopTile);
            locationsTypes.add(locationType);
            this.aggregatedPositionTypes.put(loopTile, locationsTypes);
            this.isCompleted = true;
            result = true;
        }

        return result;
    }

    /**
     * TODO: Gérer les tests pour savoir si une route est terminée
     *
     * @return True if the aggregation is completed
     */
    @Override
    public boolean checkIsCompleted()
    {
        boolean result = isCompleted;

        if (!result) {
            this.isCompleted = false;

        }

        return result;
    }
    
    public static void main(String str[]){
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
        if (player2.checkMeepleAvailable()){
            aggregate.addMeeple(player2, player2.getFirstMeepleAvailable());
        }
        System.out.println(aggregate.players);
    }
}
