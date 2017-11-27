/*
 * Carcassonne Project - 2017 - 2018
 * Created by Bertrand Challet, Thomas Cordier, Étienne Durousset, Thomas Mollaret and Nathanaël Spriet
 * CPE 4th year project
 */
package carcassonne.model.aggregate;

import carcassonne.coord.Coord;
import carcassonne.model.player.Player;
import carcassonne.model.tile.AbstractTile;
import java.util.List;

/**
 * @author Étienne
 */
public class RoadAggregate extends AbstractAggregate
{

    /**
     * Construct a road aggregation
     *
     * @param row
     * @param col
     * @param firstTile
     * @param types
     */
    public RoadAggregate(int row, int col, AbstractTile firstTile, List<String> types)
    {
        super(row, col, firstTile, types);
    }

    /**
     * Construct a road aggregation
     *
     * @param row
     * @param col
     * @param firstTile
     * @param types
     * @param player
     * @param meepleValue
     */
    public RoadAggregate(int row, int col, AbstractTile firstTile, List<String> types, Player player, int meepleValue)
    {
        super(row, col, firstTile, types, player, meepleValue);
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
            List<String> locationsTypes = this.aggregatedPositionTypes.get(loopTile);
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
}
