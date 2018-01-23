/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import static carcassonne.model.aggregate.AggregatesEnum.FIELD;
import carcassonne.model.player.Meeple;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import carcassonne.model.tile.CasualTile;
import carcassonne.model.type.AbstractType;
import carcassonne.model.type.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Étienne
 */
public class FieldAggregate extends AbstractAggregate implements Serializable
{

    private boolean hasInn;
    /**
     * Set of the cities that are linked to the fiedl
     */
    private Set<CityAggregate> boundedCities;

    public Set<CityAggregate> getBoundedCities()
    {
        return boundedCities;
    }

    public void deleteBoundedCities(Set<CityAggregate> cities)
    {
        boundedCities.removeAll(cities);
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
        this.type = FIELD;
        boundedCities = new HashSet<>();
        this.addBoundedCities(currentTileCities, firstTile, locationTypes);
        hasInn = aggregateHasInn(firstTile, locationTypes);
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
        boundedCities = new HashSet<>();
        this.addBoundedCities(currentTileCities, firstTile, locationTypes);
        hasInn = aggregateHasInn(firstTile, locationTypes);
    }

    /**
     * @return false in any cases, because a field is never completed
     */
    @Override
    public boolean checkIsCompleted()
    {
        return isCompleted;
    }

    public void merge(FieldAggregate neighborAggregate)
    {
        super.merge(neighborAggregate);
        boundedCities.addAll(neighborAggregate.getBoundedCities());
        hasInn = (this.hasInn || neighborAggregate.hasInn);
    }

    public void enlargeAggregate(int col, int row, AbstractTile newTile, Set<String> locationTypes, Set<CityAggregate> currentTileCities)
    {
        super.enlargeAggregate(col, row, newTile, locationTypes);
        this.addBoundedCities(currentTileCities, newTile, locationTypes);
        hasInn = aggregateHasInn(newTile, locationTypes);
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
                result += 3;
            }
        }

        return result;
    }

    @Override
    public String toString()
    {
        return "Field{" + "Tuiles=" + aggregatedTiles.keySet() + "Types" + aggregatedPositionTypes.values() + "Villes: " + boundedCities + " Player: " + players + "}\n";
    }

    private boolean aggregateHasInn(AbstractTile tile, Set<String> locations)
    {
        for (Map.Entry<String, AbstractType> item : tile.getTypes().entrySet()) {
            String key = item.getKey();
            AbstractType value = item.getValue();

            if (value instanceof FieldType && locations.contains(key)) {
                FieldType field = (FieldType) value;
                if (field.hasInn) {
                    return true;
                }
            }
        }

        return false;
    }
}

