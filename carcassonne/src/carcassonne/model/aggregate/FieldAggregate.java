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
    private Set<CityAggregate> boundedCities;

    public Set<CityAggregate> getBoundedCities()
    {
        return boundedCities;
    }

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
        this.addBoundedCities(currentTileCities, firstTile, locationTypes);
    }

    /**
     * Construct a field aggregation
     *
     * @param col
     * @param row
     * @param firstTile
     * @param locationTypes
     * @param player
     * @param meeple
     * @param currentTileCities
     */
    public FieldAggregate(int col, int row, AbstractTile firstTile, Set<String> locationTypes, Player player, Meeple meeple, Set<CityAggregate> currentTileCities)
    {
        super(col, row, firstTile, locationTypes, player, meeple);
        this.addBoundedCities(currentTileCities, firstTile, locationTypes);
    }

    /**
     * @return false in any cases, because a field is never completed
     */
    @Override
    public boolean checkIsCompleted()
    {
        return isCompleted;
    }

    protected void merge(FieldAggregate neighborAggregate)
    {
        super.merge(neighborAggregate);
        boundedCities.addAll(neighborAggregate.getBoundedCities());
    }

    protected void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes, Set<CityAggregate> currentTileCities)
    {
        super.enlargeAggregate(col, row, newTile, locationTypes);
        this.addBoundedCities(currentTileCities, newTile, locationTypes);
    }

    /**
     * Add the city aggregates that are linked to this aggregate
     *
     * @param currentTileCities
     * @param tile
     * @param locationTypes
     */
    private void addBoundedCities(Set<CityAggregate> currentTileCities, AbstractTile tile, Set<String> locationTypes)
    {
        Set<String> cityLocations;
        boundedCities = new HashSet();

        //Browse the city aggregates
        for (CityAggregate city : currentTileCities) {
            //test if the city is already there? if yes there is nothing to do
            if (!boundedCities.contains(city)) {
                //We get the emplacement in the current tile where the aggregate is located
                cityLocations = city.aggregatedPositionTypes.get(tile);
                //Test if the locations match the boundings of this current field aggregate
                if (CasualTile.locationsAreBounded(cityLocations, locationTypes)) {
                    boundedCities.add(city);
                }
            }
        }
    }

    @Override
    public int countPoints()
    {
        int result = 0;

        for (CityAggregate city : boundedCities) {
            if (city.checkIsCompleted()) {
                result += 2;
            }
        }

        return result;
    }

    @Override
    public String toString()
    {
        return "FieldAggregate{" + "aggregatedTiles=" + aggregatedTiles + ", aggregatedPositionTypes=" + aggregatedPositionTypes + ", players=" + players + ", isCompleted=" + isCompleted + '}';
    }
}
