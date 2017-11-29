/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.util.Set;

/**
 * @author Étienne
 */
public class CityAggregate extends AbstractAggregate
{

    /**
     * Construct a road aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes
     */
    public CityAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(col, row, firstTile, locationTypes);
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
    }

    /**
     * TODO: Gérer les tests pour obtenir une ville complétée
     *
     * @return true if the city is completed
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

}