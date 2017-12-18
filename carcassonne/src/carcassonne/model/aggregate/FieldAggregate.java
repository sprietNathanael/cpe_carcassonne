/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Étienne
 */
public class FieldAggregate extends AbstractAggregate
{

    /**
     * Set of the cities that are linked to the fiedl
     */
    public Set<CityAggregate> neighborCities;

    /**
     * Construct a field aggregation, we have to put all the cityAggregates of
     * this tile
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes
     * @param currentTileCities
     */
    public FieldAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes, Set<CityAggregate> currentTileCities)
    {
        super(col, row, firstTile, locationTypes);
        Set<String> cityLocations;
        neighborCities = new HashSet();

        //Browse the city aggregates
        for (CityAggregate city : currentTileCities) {
            //We get the emplacement in the current tile where the aggregate is located
            cityLocations = city.aggregatedPositionTypes.get(firstTile);
            //Browse the aggregate tile locations
            if (CasualTile.locationsAreBonded(cityLocations, locationTypes)) {
                neighborCities.add(city);
            }
        }
    }

    /**
     * Construct a field aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationtypes
     * @param player
     * @param meeple
     */
    public FieldAggregate(int col, int row, AbstractTile firstTile, Set<String> locationtypes, Player player, Meeple meeple)
    {
        super(col, row, firstTile, locationtypes, player, meeple);
    }

    /**
     * @return false in any case, because a field is never completed
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
