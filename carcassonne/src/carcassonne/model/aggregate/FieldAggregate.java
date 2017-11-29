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
 *
 * @author Étienne
 */
public class FieldAggregate extends AbstractAggregate
{

    /**
     * Construct a field aggregation
     *
     * @param row
     * @param col
     * @param firstTile
     * @param locationtypes
     */
    public FieldAggregate(int row, int col, AbstractTile firstTile, Set<String> locationtypes)
    {
        super(row, col, firstTile, locationtypes);
    }

    /**
     * Construct a field aggregation
     *
     * @param row
     * @param col
     * @param firstTile
     * @param locationtypes
     * @param player
     * @param meepleValue
     */
    public FieldAggregate(int row, int col, AbstractTile firstTile, Set<String> locationtypes, Player player, int meepleValue)
    {
        super(row, col, firstTile, locationtypes, player, meepleValue);
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