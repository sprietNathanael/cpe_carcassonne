/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

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
     * @param row
     * @param col
     * @param firstTile
     * @param locationTypes
     */
    public CityAggregate(int row, int col, AbstractTile firstTile, Set<String> locationTypes)
    {
        super(row, col, firstTile, locationTypes);
    }

    /**
     * Construct a city aggregation
     *
     * @param row
     * @param col
     * @param firstTile
     * @param locationTypes
     * @param player
     * @param meepleValue
     */
    public CityAggregate(int row, int col, AbstractTile firstTile, Set<String> locationTypes, Player player, int meepleValue)
    {
        super(row, col, firstTile, locationTypes, player, meepleValue);
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